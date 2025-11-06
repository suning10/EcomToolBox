package com.ecom.tasks;

import com.ecom.common.properties.EmailListrProperties;
import com.ecom.common.utils.LocalFolderUtil;
import com.ecom.common.utils.exportToCSVUtil;

import com.ecom.pojo.entity.MinCStock;

import com.ecom.service.impl.CStockServiceImpl;
import com.ecom.service.impl.EmailServiceImpl;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Component
@Slf4j
public class CStockTask {

    @Autowired
    private CStockServiceImpl cStockService;
    @Autowired
    private EmailServiceImpl emailService;
    @Autowired
    private LocalFolderUtil localFolderUtil;
    @Autowired
    private EmailListrProperties emailListrProperties;
    @Scheduled(cron = "0 0 5 * * *")
    public void cStockReport(){
        String filePath = "C:\\Users\\l.qin3\\Documents\\scheduled\\minStockQty.csv" ;
        var fileProperty = localFolderUtil.getUpdateTime("locationNERP.txt");
        String fileTIme =  fileProperty.get("updateTime").substring(0,10);
        String day = LocalDate.now().toString();
        fileProperty = localFolderUtil.getUpdateTime("locationInventory.txt");
        String fileTImeS =  fileProperty.get("updateTime").substring(0,10);
        String dayS = LocalDate.now().toString();
        if(!fileTIme.equals(day) && !fileTImeS.equals(dayS)) return;
        cStockService.prepareData();
        List<MinCStock> result = cStockService.getMinQtyCStock();
        exportToCSVUtil.writeToCsv(filePath,result);
        List<String> attachments = new ArrayList<>();
        attachments.add(filePath);
        String[] emailTo = emailListrProperties.getCstock();
        try {
            emailService.sendEmail(emailTo,"CStock Pull@" + LocalDateTime.now(),"Please see attached",attachments);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
