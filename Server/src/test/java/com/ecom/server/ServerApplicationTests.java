package com.ecom.server;

import com.ecom.common.properties.EmailListrProperties;
import com.ecom.common.utils.LocalFolderUtil;
import com.ecom.common.utils.writeToExcelIUtil;
import com.ecom.pojo.entity.EDDCarrierSummary;
import com.ecom.pojo.entity.EDDOTDDetail;
import com.ecom.pojo.entity.EDDSummary;
import com.ecom.pojo.vo.agedReturnDashboardVO;
import com.ecom.pojo.vo.agedReturnSummaryVO;
import com.ecom.service.ReturnSearchService;
import com.ecom.service.impl.EmailServiceImpl;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import com.ecom.common.utils.writeToExcelIUtil;
import com.ecom.pojo.entity.EDDCarrierSummary;
import com.ecom.pojo.entity.EDDOTDDetail;
import com.ecom.pojo.entity.EDDSummary;
import com.ecom.service.VerticaService;
import com.ecom.service.impl.EmailServiceImpl;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class ServerApplicationTests {
    @Autowired
    private ReturnSearchService returnSearchService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private EmailServiceImpl emailService;
    @Autowired
    private LocalFolderUtil localFolderUtil;
    @Autowired
    private EmailListrProperties emailListrProperties;

    @Autowired
    VerticaService verticaService;

//    @Test
//    public void redisTest() {
//        redisTemplate.opsForValue().set("key1","value1");
//        var val = redisTemplate.opsForValue().get("key1");
//        assert val.equals("value1");
//    }
//
//    @Test
//    public void getSummaryTest() {
//        try {
//            var result = returnSearchService.getAgedReturnDashboard(true);
//            var test = returnSearchService.getAgedReturnSummary(result);
//            assert (test.size() > 1);
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//
//
//    }

    @Test
    public void testAgedReturn(){
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
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                emailService.sendEmail("l.qin3@partner.sea.samsung.com","Aged Return Dashboard For " + LocalDateTime.now().format(formatter),html.toString(),attachments);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }

        Assertions.assertEquals(1,1);
    }

    @Test
    public void testOTD() throws MessagingException, UnsupportedEncodingException {
        List<EDDOTDDetail> detail = verticaService.queryOTDDetailYesterday();
        if(detail.size() == 0) return;
        List<EDDSummary> summary = verticaService.queryOTD();
        List<EDDCarrierSummary> summaryCarrier = verticaService.queryOTDByCarrier();
        List<EDDCarrierSummary> summaryCarrierwoEDD = verticaService.queryOTDSummaryByCarrier();

        if (summary.size() == 0) return;

        StringBuilder html = new StringBuilder();
        html.append("        <html>" +
                "            <head>" +
                "            <title>SCR Summary</title>" +
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
                "        <p> Please See Current Week's OTD </p>");

        html.append("<table border = '1'>        " +
                "               <thead>" +
                "               <tr>" +
                "               <th>Date</th>" +
                "              <th>Miss</th>" +
                "              <th>Total</th>" +
                "              <th>OTD</th>" +
                "            </tr>" +
                "        </thead>" +
                "        <tbody> ");

        for (EDDSummary eddSummary : summary
        ) {

            html.append("<tr>");
            html.append("<td>" + eddSummary.getEdd() + "</td>" +
                    "<td>" + eddSummary.getCntMiss() + "</td>" +
                    "<td>" + eddSummary.getCntTotal() + "</td>" +
                    "<td>" + eddSummary.getOtd() + "</td>"
            );
            html.append("</tr>");

        }
        html.append("</tbody></table>");

        /*
            add summary by carrier for the week w/o date
        */
        html.append("<br><p>OTD By Carrier and Service Level</p><table border = '1'>        " +
                "               <thead>" +
                "               <tr>" +
                "               <th>Carrier</th>" +
                "               <th>Service Level</th>" +
                "              <th>Miss</th>" +
                "              <th>Total</th>" +
                "              <th>OTD</th>" +
                "            </tr>" +
                "        </thead>" +
                "        <tbody> ");

        for (EDDCarrierSummary eddSummary : summaryCarrierwoEDD) {
            if(eddSummary.getEdd()==null) continue;
            html.append("<tr>");
            html.append(
                    "<td>" + eddSummary.getCarrier() + "</td>" +
                            "<td>" + eddSummary.getServiceLevel() + "</td>" +
                            "<td>" + eddSummary.getCntMiss() + "</td>" +
                            "<td>" + eddSummary.getCntTotal() + "</td>" +
                            "<td>" + eddSummary.getOtd() + "</td>"
            );
            html.append("</tr>");

        }
        html.append("</tbody></table>");

        /*
            add summary by carrier
        */
        html.append("<br><p>OTD By Day, Carrier and Service Level </p><table border = '1'>        " +
                "               <thead>" +
                "               <tr>" +
                "               <th>Date</th>" +
                "               <th>Carrier</th>" +
                "               <th>Service Level</th>" +
                "              <th>Miss</th>" +
                "              <th>Total</th>" +
                "              <th>OTD</th>" +
                "            </tr>" +
                "        </thead>" +
                "        <tbody> ");

        for (EDDCarrierSummary eddSummary : summaryCarrier) {
            if(eddSummary.getEdd() == null) continue;
            html.append("<tr>");
            html.append("<td>" + eddSummary.getEdd() + "</td>" +
                    "<td>" + eddSummary.getCarrier() + "</td>" +
                    "<td>" + eddSummary.getServiceLevel() + "</td>" +
                    "<td>" + eddSummary.getCntMiss() + "</td>" +
                    "<td>" + eddSummary.getCntTotal() + "</td>" +
                    "<td>" + eddSummary.getOtd() + "</td>"
            );
            html.append("</tr>");

        }
        html.append("</tbody></table>");


        //Due to the limitation of CSV file, you may need to se notepad to open the CSV and see the actual Mail Innovation tracking numbers <br>
        html.append("<br> <p>Carrier Finance is included in the misses (see column finance_plan_id, eg. vzwplan_36). EDD/ship date for carrier finance might not accurate</p>");
        html.append("<br> <p></p>");
        html.append("</html>");
        // write to csv
        if (detail.size() > 1) {
            String filePath = "C:\\Users\\l.qin3\\Documents\\scheduled\\OTDMissYesterday.xlsx";
            //exportToCSVUtil.writeToCsv(filePath,detail);
            try {
                writeToExcelIUtil.writeListToExcel(detail, filePath, "tracking");
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            List<String> attachments = new ArrayList<>();
            attachments.add(filePath);
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                emailService.sendEmail("l.qin3@partner.sea.samsung.com", "OTD Summary For " + LocalDateTime.now().minusDays(1).format(formatter), html.toString(), attachments);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        } else {
            emailService.sendEmail("l.qin3@partner.sea.samsung.com", "OTD Summary For " + LocalDateTime.now().minusDays(1), html.toString());
        }

    }


}
