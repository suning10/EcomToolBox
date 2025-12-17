//package com.ecom.server;
//
//import com.ecom.common.properties.EmailListrProperties;
//import com.ecom.common.utils.LocalFolderUtil;
//import com.ecom.common.utils.writeToExcelIUtil;
//import com.ecom.pojo.vo.agedReturnDashboardVO;
//import com.ecom.pojo.vo.agedReturnSummaryVO;
//import com.ecom.service.ReturnSearchService;
//import com.ecom.service.impl.EmailServiceImpl;
//import jakarta.mail.MessagingException;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.RedisTemplate;
//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.security.NoSuchAlgorithmException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.List;
//
//
//@SpringBootTest
//class ServerApplicationTests {
//    @Autowired
//    private ReturnSearchService returnSearchService;
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    @Autowired
//    private EmailServiceImpl emailService;
//    @Autowired
//    private LocalFolderUtil localFolderUtil;
//    @Autowired
//    private EmailListrProperties emailListrProperties;
////    @Test
////    public void redisTest() {
////        redisTemplate.opsForValue().set("key1","value1");
////        var val = redisTemplate.opsForValue().get("key1");
////        assert val.equals("value1");
////    }
////
////    @Test
////    public void getSummaryTest() {
////        try {
////            var result = returnSearchService.getAgedReturnDashboard(true);
////            var test = returnSearchService.getAgedReturnSummary(result);
////            assert (test.size() > 1);
////        } catch (NoSuchAlgorithmException e) {
////            throw new RuntimeException(e);
////        }
////
////
////    }
//
//    @Test
//    public void testAgedReturn(){
//        List<agedReturnDashboardVO> result;
//        try {
//            result =  returnSearchService.getAgedReturnDashboard(false);
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//
//        if(result.size() > 1){
//            String filePath = "C:\\Users\\l.qin3\\Documents\\scheduled\\AgedReturnDashboard.xlsx" ;
//            //exportToCSVUtil.writeToCsv(filePath,detail);
//            try {
//                writeToExcelIUtil.writeListToExcel(result,filePath,"bol");
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            } catch (IllegalAccessException e) {
//                throw new RuntimeException(e);
//            }
//            List<String> attachments = new ArrayList<>();
//            attachments.add(filePath);
//            List<agedReturnSummaryVO> summary = returnSearchService.getAgedReturnSummary(result);
//            StringBuilder html = new StringBuilder();
//            html.append("        <html>" +
//                    "            <head>" +
//                    "            <title>Aged Return Summary</title>" +
//                    "                <style>" +
//                    "                        table {" +
//                    "                        border-collapse: collapse;" +
//                    "                        width: 100%;" +
//                    "                        }" +
//                    "                    " +
//                    "                        th, td {" +
//                    "                        border: 1px solid #ddd;" +
//                    "                        text-align: left;" +
//                    "                        padding: 8px;" +
//                    "                        color:black" +
//                    "                        }" +
//                    "                    " +
//                    "                    " +
//                    "                        th {" +
//                    "                    background-color: blue;" +
//                    "                    color: white;" +
//                    "                        }" +
//                    "            </style>" +
//                    "        </head>" +
//                    "        " +
//                    "" +
//                    "" +
//                    "        <p> Please See Aged Return Summary Below </p>");
//
//            html.append("<table border = '1'>        " +
//                    "               <thead>" +
//                    "               <tr>" +
//                    "               <th>Aged Bucket</th>" +
//                    "              <th>Scan Status</th>" +
//                    "              <th>Refusal?</th>" +
//                    "              <th>Number of RDOs</th>" +
//                    "            </tr>" +
//                    "        </thead>" +
//                    "        <tbody> ");
//
//            for ( agedReturnSummaryVO s: summary
//            ) {
//                html.append("<tr>");
//                html.append("<td>" + s.getAgedReturnSummaryObj().getAgedBucket() + "</td>" +
//                        "<td>" + s.getAgedReturnSummaryObj().getScanStatus() + "</td>" +
//                        "<td>" + s.getAgedReturnSummaryObj().getFlagRefusal() + "</td>" +
//                        "<td>" + s.getCount() + "</td>"
//                );
//                html.append("</tr>");
//
//            }
//            html.append("</tbody></table>");
//
//
//            try {
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
//                emailService.sendEmail("l.qin3@partner.sea.samsung.com","Aged Return Dashboard For " + LocalDateTime.now().format(formatter),html.toString(),attachments);
//            } catch (MessagingException e) {
//                throw new RuntimeException(e);
//            } catch (UnsupportedEncodingException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//        Assertions.assertEquals(1,1);
//    }
//
//
//
//}
