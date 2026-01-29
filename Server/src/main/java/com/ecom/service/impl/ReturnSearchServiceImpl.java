package com.ecom.service.impl;

import com.ecom.common.exception.ExtensionNotCorrectException;
import com.ecom.common.utils.LocalFolderUtil;
import com.ecom.mapper.mysql.ReturnMapper;
import com.ecom.mapper.mysql.SCRMapper;
import com.ecom.mapper.vertica.VerticaMapper;
import com.ecom.pojo.dto.SearchByRDODTO;
import com.ecom.pojo.entity.*;
import com.ecom.pojo.vo.agedReturnDashboardVO;
import com.ecom.pojo.vo.agedReturnSummaryVO;
import com.ecom.service.ReturnSearchService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class ReturnSearchServiceImpl implements ReturnSearchService {

    @Autowired
    ReturnMapper returnMapper;
    @Autowired
    SCRMapper scrMapper;

    @Autowired
    VerticaMapper verticaMapper;

    @Autowired
    private LocalFolderUtil localFolderUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<Return> getByRDO(SearchByRDODTO searchByRDODTO) {

        returnMapper.createTempRDOTable();
        returnMapper.truncateTempRDOTable();
        returnMapper.insertrdoList(searchByRDODTO.getRdoList());
        List<Return> returnResult = returnMapper.searchByRDO(searchByRDODTO);

        return returnResult;
    }

    @Override
    public List<Return> getByPO(SearchByRDODTO searchByRDODTO) {
        returnMapper.createTempRDOTable();
        returnMapper.truncateTempRDOTable();
        returnMapper.insertrdoList(searchByRDODTO.getRdoList());
        List<Return> returnResult = returnMapper.searchByPO(searchByRDODTO);

        return returnResult;
    }

    @Override
    public void upload(MultipartFile file) {
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")) ;

        if(!extension.equals(".csv")) throw new ExtensionNotCorrectException("please upload correct file -- csv file is accepted");
        String filename =  "ageReturn.csv";
        String path = localFolderUtil.upload(file,filename);
        returnMapper.updateReturnMaster();
        returnMapper.loadDataInline(path);


    }

    @Override
    public List<ReturnSimple> getByRDOSimple(SearchByRDODTO searchByRDODTO) {
        // use temp table to hold the value
        returnMapper.createTempRDOTable();
        returnMapper.truncateTempRDOTable();
        returnMapper.insertrdoList(searchByRDODTO.getRdoList());
        List<ReturnSimple> returnResult = returnMapper.searchByRDOSimple(searchByRDODTO);

        return returnResult;
    }

    @Override
    public void uploadSimple(MultipartFile file) {
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")) ;

        if(!extension.equals(".csv")) throw new ExtensionNotCorrectException("please upload correct file -- csv file is accepted");
        String filename =  "ageReturnSimple.csv";
        String path = localFolderUtil.upload(file,filename);
        //update stgTable
        returnMapper.updateStgTable();
        returnMapper.loadDataInlineStg(path);
        //load into actual table
        returnMapper.loadDataReturnSearch();

    }

    @Override
    public boolean uploadAgedReturnNerp(){
        //prepare data
        String path = localFolderUtil.getPath();
        var paths = Paths.get(path,"agedReturn.txt");

        //load data here
        scrMapper.truncateTable("agedreturnnerp");

        scrMapper.updateStgTable(paths.toString(),9,"agedreturnnerp");
        return true;
    }

    @Override
    public List<agedReturnDashboardVO> getAgedReturnDashboard(boolean flagCache) throws NoSuchAlgorithmException {

        //query NERP data
        List<agedReturnNerp> nerp =  returnMapper.getAgedReturnNERP();

        //get rdo list - non-tiktok
        List<String> rdoList = new ArrayList<>();
        HashSet<String> rdoListTiktok = new HashSet<>();
        HashSet<String> poSet = new HashSet<>();
        nerp.stream().forEach(a -> {
            if(a.getSoldTo().substring(0,7).equals("7072362") && !rdoListTiktok.contains(a.getDO())) {
                rdoListTiktok.add(a.getDO());
                return;
            }
            if(!rdoList.contains(a.getDO())) {
                rdoList.add(a.getDO());
            }
            if(!poSet.contains(a.getPo().toLowerCase()));
                poSet.add(a.getPo().toLowerCase());
        } );
        List<String> listRDOTiktok = rdoListTiktok.stream().toList();
        List<String> poList = poSet.stream().toList();


        if(rdoList.size() == 0 && rdoListTiktok.size() == 0) return null;
        //change rdoList to hash
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        String key = Base64.getEncoder().encodeToString(md.digest(String.join(":",rdoList).getBytes()));
        //get rma from vertica for tiktok
        String keyTiktok = Base64.getEncoder().encodeToString(md.digest(String.join(":",rdoListTiktok).getBytes()));
        String keyPO = Base64.getEncoder().encodeToString(md.digest(String.join(":",poList).getBytes()));
        List<agedReturnVertica> vertica;
        List<agedReturnVerticaTikTok> verticaTikToks = null;
        List<ReturnTracking> verticaReturnTracking = null;
        //force to query?
        if(flagCache){
            vertica = verticaMapper.queryAgedReturnDashboard(rdoList);
            verticaTikToks = verticaMapper.queryAgedReturnDashboardTiktok(listRDOTiktok);
            verticaReturnTracking = verticaMapper.queryReturnTrackingOnly(poList);

        }
        else {
            if(redisTemplate.hasKey(key) && redisTemplate.hasKey(keyTiktok) && redisTemplate.hasKey(keyPO)){
                //check if key in Redis
                vertica = (List<agedReturnVertica>)redisTemplate.opsForValue().get(key);
                verticaReturnTracking = (List<ReturnTracking>) redisTemplate.opsForValue().get(keyPO);
                if(rdoListTiktok.size() != 0) {
                    verticaTikToks = (List<agedReturnVerticaTikTok>)redisTemplate.opsForValue().get(keyTiktok);
                }

            }

            else{
                //query Vertica DB
                vertica =  verticaMapper.queryAgedReturnDashboard(rdoList);
                verticaReturnTracking = verticaMapper.queryReturnTrackingOnly(poList);
                if(rdoListTiktok.size() != 0){
                    verticaTikToks = verticaMapper.queryAgedReturnDashboardTiktok(listRDOTiktok);
                    redisTemplate.opsForValue().set(keyTiktok,verticaTikToks,2,TimeUnit.HOURS);
                }
                //store in Redis and ttl = 2 hours
                redisTemplate.opsForValue().set(key,vertica,2, TimeUnit.HOURS);
                redisTemplate.opsForValue().set(keyPO,verticaReturnTracking,2, TimeUnit.HOURS);


            }
        }








        //getSummaryDashboard
        //put into a hashmap
        HashMap<String,agedReturnVertica> map = new HashMap<>();
        vertica.stream().forEach(a -> map.put(a.getRdo() + a.getSku(), a));
        HashMap<String,String> mapTiktok = new HashMap<>();
        if(verticaTikToks != null && verticaTikToks.size() > 0){
            verticaTikToks.forEach(a -> mapTiktok.put(a.getRdo(),a.getRma()));
        }
        /**
         * new logic of additional lookup of tracking id
         * in case something mess up in joins
         */
        HashMap<String,ReturnTracking> mapReturnTracking = new HashMap<>();
        verticaReturnTracking.forEach(a -> {
            if(a.getReturnTrackingId()==null || a.getReturnTrackingId().length() < 2) return;
            mapReturnTracking.put(a.getPoId(),a);
        });

        //create vo List
        List<agedReturnDashboardVO> result = new ArrayList<>();
        nerp.stream().forEach(
                a -> {
                    agedReturnDashboardVO cur = new agedReturnDashboardVO();
                    BeanUtils.copyProperties(a,cur);
                    agedReturnVertica verticaResult;
                    ReturnTracking verticaResultTracking;
                    if(map.containsKey(a.getDO() +  a.getMaterial().toLowerCase())){
                        verticaResult = map.getOrDefault(a.getDO() +  a.getMaterial().toLowerCase(), new agedReturnVertica());
                        if(verticaResult.getReturnTrackingId() == null || verticaResult.getReturnTrackingId().length() < 2){
                            verticaResultTracking = mapReturnTracking.getOrDefault(a.getPo().toLowerCase(),new ReturnTracking());
                            BeanUtils.copyProperties(verticaResultTracking,cur);
                        }
                        BeanUtils.copyProperties(verticaResult,cur);
                    }
                    else{
                        verticaResultTracking = mapReturnTracking.getOrDefault(a.getPo().toLowerCase(),new ReturnTracking());
                        BeanUtils.copyProperties(verticaResultTracking,cur);
                    }


                    String scanStatus = "";
                    //logic of determine scan status
                    if(a.getFlagRefusal().equals("Refusal")) {
                        scanStatus = "Refusal";
                        cur.setScanStatus(scanStatus);
                        result.add(cur);
                        return;
                    }

                    if(a.getSoldTo().substring(0,7).equals("7072362")) {
                        cur.setScanStatus("Tiktok Order");
                        cur.setRma(mapTiktok.getOrDefault(a.getDO(),""));
                        result.add(cur);
                        return;

                    }

                    if(cur.getReturnTrackingId() != ""){
                        if(cur.getStatusDetailTs() == null ) scanStatus = "No Scan";
                        else{
                            DateTimeFormatter formatter = new DateTimeFormatterBuilder().
                                    appendPattern("yyyy-MM-dd HH:mm:ss")
                                    .optionalStart()
                                    .appendFraction(ChronoField.MICRO_OF_SECOND,1,6,true)
                                    .optionalEnd()
                                    .toFormatter();
                            var dt = LocalDateTime.parse(cur.getStatusDetailTs() ,formatter);
                            //System.out.println(dt.toLocalDate().toString());
                            Long days = ChronoUnit.DAYS.between(dt,LocalDateTime.now());
                            if(cur.getStatusDetail().equals("dl")) scanStatus = "Delivered";
                            else if(days > 2) scanStatus = "No Scan in 48 hours";
                            else if(days <= 2) scanStatus = "In Transit";
                        }

                    }
                    else{
                        scanStatus = "No Scan";
                    }

                    cur.setScanStatus(scanStatus);
                    result.add(cur);
                }
        );

        /*
        //Query again for any missing Return Tracking
        List<String> missingTracking = new ArrayList<>();
        result.stream().forEach(a -> {
            if(a.getReturnTrackingId()==null) {
                missingTracking.add(a.getPo());
            }
        });

        //query fact_return only
        List<ReturnTracking> returnTracking= verticaMapper.queryReturnTrackingOnly(missingTracking);
        HashMap<String,String> mapReturnTracking = new HashMap<>();
        returnTracking.forEach(a -> {
            mapReturnTracking.put(a.getPo(),a.getTracking());
        });
        result.stream().forEach(a -> {

        }); */

        return result;
    }


    @Override
    public List<agedReturnSummaryVO> getAgedReturnSummary(List<agedReturnDashboardVO> raw){
        Map<agedReturnSummary,Integer> result = raw.stream().
                collect(Collectors.groupingBy(o ->
                        new agedReturnSummary(o.getFlagRefusal(),o.getAgedBucket(),o.getScanStatus()),
                        Collectors.mapping( agedReturnDashboardVO::getDO, Collectors.collectingAndThen( Collectors.toSet(), Set::size ) ) ));
        List<agedReturnSummaryVO> summary = new ArrayList<>();
        result.forEach((a,b)-> summary.add(new agedReturnSummaryVO(a,b)));
        //sort, stop java from inferring
        summary.sort(Comparator.comparing((agedReturnSummaryVO a)  -> a.getAgedReturnSummaryObj().getAgedBucket()
        ).thenComparing(
                (agedReturnSummaryVO b) -> b.getAgedReturnSummaryObj().getScanStatus()
        ).thenComparing(
                (agedReturnSummaryVO c) -> c.getAgedReturnSummaryObj().getFlagRefusal()));

        return summary;
    }
}
