package com.ecom.service.impl;


import com.ecom.common.enumeration.UploadStatus;
import com.ecom.common.exception.ExtensionNotCorrectException;
import com.ecom.common.utils.LocalFolderUtil;
import com.ecom.mapper.mysql.SCRMapper;
import com.ecom.pojo.dto.SKUSearchDTO;
import com.ecom.pojo.entity.*;
import com.ecom.pojo.vo.SKUSummaryByMvmTypeVO;
import com.ecom.service.SCRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class SCRServiceImpl implements SCRService {


    @Autowired
    private SCRMapper scrMapper;

    @Autowired
    private LocalFolderUtil localFolderUtil;

    @Override
    public List<SKUComparison> querySKUComparison(String sku, String start, String end, String sloc) {

        var result = scrMapper.querySKUComparison(sku,start,end,sloc);

        return result;
    }

    @Override
    public List<SKUActivityNERP> querySKUDetailNERPAdj(SKUSearchDTO skuSearchDTO) {

        List<SKUActivityNERP> result = scrMapper.postSKUDetailNERPAdj(skuSearchDTO.getSku(),skuSearchDTO.getStart(),skuSearchDTO.getEnd(),skuSearchDTO.getSloc());
        return result;
    }

    @Override
    public List<SKUActivitySynapse> querySKUDetailSynapseAdj(SKUSearchDTO skuSearchDTO) {

        List<SKUActivitySynapse> result = scrMapper.postSKUDetailSynapseAdj(skuSearchDTO.getSku(),skuSearchDTO.getStart(),skuSearchDTO.getEnd(),skuSearchDTO.getSloc());
        return result;
    }

    @Override
    public SKUSummaryByMvmTypeVO querySKUSummarySynapseAdj(SKUSearchDTO skuSearchDTO) {

        var result = scrMapper.postSKUSummarySynapseAdj(skuSearchDTO.getSku(),skuSearchDTO.getStart(),skuSearchDTO.getEnd(),skuSearchDTO.getSloc());
        int total = 0;
        for (var entry: result
             ) {
            total = (int) Double.parseDouble(entry.getQty())  + total;
        }

        SKUSummaryByMvmTypeVO skuSummaryByMvmTypeVO = new SKUSummaryByMvmTypeVO(result,total);
        return skuSummaryByMvmTypeVO;
    }

    @Override
    public SKUSummaryByMvmTypeVO querySKUSummaryNERPAdj(SKUSearchDTO skuSearchDTO) {
        var result = scrMapper.postSKUSummaryNERPAdj(skuSearchDTO.getSku(),skuSearchDTO.getStart(),skuSearchDTO.getEnd(),skuSearchDTO.getSloc());
        int total = 0;
        if(!result.isEmpty()) {
            for (var entry: result
            ) {
                total =  (int) Double.parseDouble(entry.getQty())  + total;
            }
        }

        SKUSummaryByMvmTypeVO skuSummaryByMvmTypeVO = new SKUSummaryByMvmTypeVO(result,total);

        return skuSummaryByMvmTypeVO;
    }

    @Override
    public List<SKUActivityNERP> querySKUComparisonMissingNerp(String sku, String start, String end, String sloc) {


        List<SKUActivityNERP> result = scrMapper.postSKUComparisonMissingNerp(sku,start,end,sloc);

        return result;

    }

    @Override
    public List<SKUActivityNERP> querySKUComparisonMissingNerp(String sku, String start, String end) {


        List<SKUActivityNERP> result = scrMapper.postSKUComparisonMissingNerpWOSLoc(sku,start,end);

        return result;

    }

    @Override
    public List<SKUActivitySynapse> querySKUComparisonMissingSynapse(String sku, String start, String end, String sloc) {

        List<SKUActivitySynapse> result = scrMapper.postSKUComparisonMissingSynapse(sku,start,end,sloc);

        return result;
    }

    @Override
    public List<SKUActivitySynapse> querySKUComparisonMissingSynapse(String sku, String start, String end) {

        List<SKUActivitySynapse> result = scrMapper.postSKUComparisonMissingSynapseWOSLoc(sku,start,end);

        return result;
    }

    @Override
    public List<ScrReportSummary> queryScrReportSummary(String date) {

        List<ScrReportSummary> result = scrMapper.getSCRReportSummary(date);
        return result;
    }

    @Override
    public List<ScrReportGap> queryScrReportGap(String date, String sloc) {

        if(sloc == "WR2E"){
            return scrMapper.getSCRReportGapWR2E(date,"WR2E");
        }
        else{
            return scrMapper.getSCRReportGap(date,sloc);
        }
    }

    @Override
    public List<ScrReportRaw> queryScrReportDetail(String date, String sloc) {
        if(sloc == "WR2E"){
            return scrMapper.getSCRReportDetailWR2E(date,"WR2E");
        }
        else{
            return scrMapper.getSCRReportDetil(date,sloc);
        }
    }

    @Override
    public List<ScrReportDod> queryScrReportDOD(String sloc) {
        return scrMapper.getSCRReportDOD(sloc);
    }

    @Override
    public UploadStatus uploadSCRReport(MultipartFile file) {

        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")) ;

        if(!extension.equals(".txt")) throw new ExtensionNotCorrectException("please upload correct file -- txt file is accepted");
        String filename =  "scrReport.txt";
        String path = localFolderUtil.upload(file,filename);
        //update stgTable
        scrMapper.truncateTable("stagingscrraw");
        scrMapper.updateStgTable(path,5,"stagingscrraw");
        String stgDate = scrMapper.getStgDate("stagingscrraw","Date");
        int tableDateCnt = scrMapper.getTableDate("scrraw","Date",stgDate);
        if(tableDateCnt > 1) return UploadStatus.DataExists;
        //load into actual table
        scrMapper.insert();
        return UploadStatus.LoadSuccess;
    }

    @Override
    public UploadStatus uploadSkuMB51(MultipartFile file) {
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")) ;

        if(!extension.equals(".txt")) throw new ExtensionNotCorrectException("please upload correct file -- txt file is accepted");
        String filename =  "skuMb51.txt";
        String path = localFolderUtil.upload(file,filename);
        //update stgTable
        scrMapper.truncateTable("stg_scr_nerp");
        scrMapper.updateStgTable(path,5,"stg_scr_nerp");
        scrMapper.updateComma("stg_scr_nerp");
        // check if data exists
        String stgDate = scrMapper.getStgDate("stg_scr_nerp","post_date");
        int tableDateCnt = scrMapper.getTableDate("scr_nerp","post_date",stgDate);
        if(tableDateCnt > 1) return UploadStatus.DataExists;
        //load into actual table
        scrMapper.insertMB51();
        return UploadStatus.LoadSuccess;
    }

    @Override
    public UploadStatus uploadSkuItemActivity(MultipartFile file) {

        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")) ;
        if(!extension.equals(".txt")) throw new ExtensionNotCorrectException("please upload correct file -- txt file is accepted");
        String filename =  "item_activity.txt";
        String path = localFolderUtil.upload(file,filename);
        //update stgTable
        scrMapper.truncateTable("stg_scr_item_activity");
        scrMapper.updateStgTable(path,1,"stg_scr_item_activity");
        //update comma
        scrMapper.updateComma("stg_scr_item_activity");
        // check if data exists
        String stgDate = scrMapper.getStgDate("stg_scr_item_activity","transaction_date");
        int tableDateCnt = scrMapper.getTableDate("scr_item_activity","transaction_date",stgDate);
        if(tableDateCnt > 1) return UploadStatus.DataExists;
        //load into actual table
        scrMapper.insertItemActivity();
        return UploadStatus.LoadSuccess;
    }

    @Override
    public List<SKUComparison> querySKUComparisonAll(String sku, String start, String end, String sloc) {

        List<SKUComparison> result = scrMapper.querySKUComparisonAll(sku,start,end,sloc);

        return result;
    }

    @Override
    public List<SKUActivityNERP> querySKUComparisonMissingNerp(String start, String end) {

        List<SKUActivityNERP> result = scrMapper.postSKUComparisonMissingNerpEmail(start,end);

        return result;
    }

    @Override
    public List<SKUActivitySynapse> querySKUComparisonMissingSynapse(String start, String end) {

        List<SKUActivitySynapse> result = scrMapper.postSKUComparisonMissingSynapseEmail(start,end);

        return result;
    }

    @Override
    public List<DODSearch> queryDodBySku(String material, String sloc) {

        List<DODSearch> result = scrMapper.getDodBySku(material,sloc);
        return result;
    }

    @Override
    public String queryMaxDateNerp() {
        String result = scrMapper.getMaxNerpDate();
        return result;
    }

    @Override
    public String queryMaxDateSynapse() {
        String result = scrMapper.getMaxSynapseDate();
        return result;
    }
}
