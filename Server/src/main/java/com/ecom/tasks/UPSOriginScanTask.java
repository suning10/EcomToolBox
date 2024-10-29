package com.ecom.tasks;


import com.ecom.mapper.vertica.VerticaMapper;
import com.ecom.pojo.entity.OriginScanUPS;
import com.ecom.service.impl.EmailServiceImpl;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Component
@Slf4j
public class UPSOriginScanTask {

    @Autowired
    private EmailServiceImpl emailService;
    @Autowired
    private VerticaMapper verticaMapper;
    @Scheduled(cron = "0 30 8 * * *")
    public void upsLateOriginScan(){
        int dayofweek = LocalDate.now().getDayOfWeek().getValue();
        String start = LocalDate.now().minusDays(dayofweek).toString();
        String end = LocalDate.now().toString();
        List<String> pathStringList = new ArrayList<>();
        String pathStringOriginScan = "C:\\Users\\l.qin3\\Documents\\uploads\\LateOriginScan.csv";
        pathStringList.add(pathStringOriginScan);
        String pathStringShipNotTender = "C:\\Users\\l.qin3\\Documents\\uploads\\ShipNotTender.csv";
        pathStringList.add(pathStringShipNotTender);
        for (String pathString:pathStringList
             ) {
            List<OriginScanUPS> result;
            if(pathString.equals(pathStringOriginScan)){
               result = verticaMapper.queryOriginScanUPS(start,end);
               log.warn("originScanQueryResult");
            }
            else{
                result = verticaMapper.queryShipNotTenderUPS(start,end);
                log.warn("ShipNotScanResult");
            }

            Path filePath = Paths.get(pathString);
            try{
                if (Files.notExists(filePath)) {
                    filePath.toFile().createNewFile();
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Tracking Number");
                stringBuilder.append(',');
                stringBuilder.append("PO Number");
                stringBuilder.append(',');
                stringBuilder.append("DO Number");
                stringBuilder.append(',');
                stringBuilder.append("EDD");
                stringBuilder.append(',');
                stringBuilder.append("Order Entry Date");
                stringBuilder.append(',');
                stringBuilder.append("Ship Scan Date");
                stringBuilder.append(',');
                stringBuilder.append("Origin Scan Date");
                stringBuilder.append(',');
                stringBuilder.append("Origin Scan Hour");
                stringBuilder.append(',');
                stringBuilder.append("Delivery Date");
                stringBuilder.append(',');
                stringBuilder.append("Different");
                stringBuilder.append(',');
                stringBuilder.append("Delivery TS");
                stringBuilder.append(',');
                stringBuilder.append("UPS Estimated Delivery Date");
                stringBuilder.append(System.lineSeparator());
                for (OriginScanUPS originScanups:result
                ) {
                    stringBuilder.append(originScanups.getTracking());
                    stringBuilder.append(',');
                    stringBuilder.append(originScanups.getPoId());
                    stringBuilder.append(',');
                    stringBuilder.append(originScanups.getDoId());
                    stringBuilder.append(',');
                    stringBuilder.append(originScanups.getEDD());
                    stringBuilder.append(',');
                    stringBuilder.append(originScanups.getOrderEntryDate());
                    stringBuilder.append(',');
                    stringBuilder.append(originScanups.getShipScanDate());
                    stringBuilder.append(',');
                    stringBuilder.append(originScanups.getOriginScan());
                    stringBuilder.append(',');
                    stringBuilder.append(originScanups.getOriginScanHour());
                    stringBuilder.append(',');
                    stringBuilder.append(originScanups.getOutForDeliveryScan());
                    stringBuilder.append(',');
                    stringBuilder.append(originScanups.getDiff());
                    stringBuilder.append(',');
                    stringBuilder.append(originScanups.getDeliveredTs());
                    stringBuilder.append(',');
                    stringBuilder.append(originScanups.getEstimatedDelivery());
                    stringBuilder.append(System.lineSeparator());
                }

                Files.write(filePath, stringBuilder.toString().getBytes(), StandardOpenOption.WRITE);
            }

            catch (Exception e){
                log.error("error with writing to csv");
            }
        }



        try{
        emailService.sendEmail("eCommTeam@sea.samsung.com","Late Origin Scan and NotYetTender","Please see attached",pathStringList);}
        catch (Exception e){
            log.error(e.toString());
        }
    }
}
