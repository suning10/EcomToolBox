package com.ecom.tasks;

import com.ecom.common.properties.EmailListrProperties;
import com.ecom.common.utils.LocalFolderUtil;
import com.ecom.common.utils.exportToCSVUtil;
import com.ecom.common.utils.writeToExcelIUtil;
import com.ecom.pojo.entity.EDDCarrierSummary;
import com.ecom.pojo.entity.EDDSummary;
import com.ecom.pojo.entity.MinCStock;
import com.ecom.pojo.vo.agedReturnDashboardVO;
import com.ecom.pojo.vo.agedReturnSummaryVO;
import com.ecom.service.ReturnSearchService;
import com.ecom.service.impl.CStockServiceImpl;
import com.ecom.service.impl.EmailServiceImpl;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Component
@Slf4j
public class AgedReturnTask {

    @Autowired
    private ReturnSearchService returnSearchService;
    @Autowired
    private EmailServiceImpl emailService;
    @Autowired
    private LocalFolderUtil localFolderUtil;
    @Autowired
    private EmailListrProperties emailListrProperties;
    @Scheduled(cron = "0 30 8 * * *")
    public void cStockReport(){

        if(!returnSearchService.uploadAgedReturnNerp()){
            try {
                emailService.sendEmail("l.qin3@partner.sea.samsung.com","Fail to Load AgedReturnNERP","At " + LocalDateTime.now().toString());
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }

        List<agedReturnDashboardVO> result;
        try {
            result =  returnSearchService.getAgedReturnDashboard(false);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        if(result.size() > 1){
            String filePath = "C:\\Users\\l.qin3\\Documents\\scheduled\\AgedReturnDashboard.xlsx" ;
            //exportToCSVUtil.writeToCsv(filePath,detail);
            try {
                writeToExcelIUtil.writeListToExcel(result,filePath,"bol");
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            List<String> attachments = new ArrayList<>();
            attachments.add(filePath);
            List<agedReturnSummaryVO> summary = returnSearchService.getAgedReturnSummary(result);
            StringBuilder html = new StringBuilder();
            html.append("        <html>" +
                    "            <head>" +
                    "            <title>Aged Return Summary</title>" +
                    "                <style>" +
                    "                        table {" +
                    "                        border-collapse: collapse;" +
                    "                        width: 100%;" +
                    "                        }" +
                    "                    " +
                    "                        th, td {" +
                    "                        border: 1px solid #ddd;" +
                    "                        text-align: left;" +
                    "                        padding: 8px;" +
                    "                        color:black" +
                    "                        }" +
                    "                    " +
                    "                    " +
                    "                        th {" +
                    "                    background-color: blue;" +
                    "                    color: white;" +
                    "                        }" +
                    "            </style>" +
                    "        </head>" +
                    "        " +
                    "" +
                    "" +
                    "        <p> Please See Aged Return Summary Below </p>");

            html.append("<table border = '1'>        " +
                    "               <thead>" +
                    "               <tr>" +
                    "               <th>Aged Bucket</th>" +
                    "              <th>Scan Status</th>" +
                    "              <th>Refusal?</th>" +
                    "              <th>Number of RDOs</th>" +
                    "            </tr>" +
                    "        </thead>" +
                    "        <tbody> ");

            for ( agedReturnSummaryVO s: summary
            ) {
                html.append("<tr>");
                html.append("<td>" + s.getAgedReturnSummaryObj().getAgedBucket() + "</td>" +
                        "<td>" + s.getAgedReturnSummaryObj().getScanStatus() + "</td>" +
                        "<td>" + s.getAgedReturnSummaryObj().getFlagRefusal() + "</td>" +
                        "<td>" + s.getCount() + "</td>"
                );
                html.append("</tr>");

            }
            html.append("</tbody></table>");


            try {
                String[] emailList = emailListrProperties.getAgeReturn();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                emailService.sendEmail(emailList,"Aged Return Dashboard For " + LocalDateTime.now().format(formatter),html.toString(),attachments);

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
