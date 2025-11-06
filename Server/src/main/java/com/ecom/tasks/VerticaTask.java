package com.ecom.tasks;

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

@Component
public class VerticaTask {

    @Autowired
    VerticaService verticaService;

    @Autowired
    private EmailServiceImpl emailService;

    @Scheduled(cron = "0 45 8 * * TUE-SUN")
    public void otdSummary() throws MessagingException, UnsupportedEncodingException {

        List<EDDOTDDetail> detail =  verticaService.queryOTDDetailYesterday();
        List<EDDSummary> summary =  verticaService.queryOTD();
        List<EDDCarrierSummary> summaryCarrier =  verticaService.queryOTDByCarrier();
        List<EDDCarrierSummary> summaryCarrierwoEDD =  verticaService.queryOTDSummaryByCarrier();

        if(summary.size() == 0) return;

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

        for (EDDSummary eddSummary:summary
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

        for (EDDCarrierSummary eddSummary:summaryCarrierwoEDD) {
            html.append("<tr>");
            html.append(
                    "<td>" + eddSummary.getCarrier() + "</td>" +
                    "<td>" + eddSummary.getServiceLevel()+ "</td>" +
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

        for (EDDCarrierSummary eddSummary:summaryCarrier) {
            html.append("<tr>");
            html.append("<td>" + eddSummary.getEdd() + "</td>" +
                    "<td>" + eddSummary.getCarrier() + "</td>" +
                    "<td>" + eddSummary.getServiceLevel()+ "</td>" +
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
        if(detail.size() > 1){
            String filePath = "C:\\Users\\l.qin3\\Documents\\scheduled\\OTDMissYesterday.xlsx" ;
            //exportToCSVUtil.writeToCsv(filePath,detail);
            try {
                writeToExcelIUtil.writeListToExcel(detail,filePath,"tracking");
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            List<String> attachments = new ArrayList<>();
            attachments.add(filePath);
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                emailService.sendEmail("eCommTeam@sea.samsung.com","OTD Summary For " + LocalDateTime.now().minusDays(1).format(formatter),html.toString(),attachments);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            emailService.sendEmail("eCommTeam@sea.samsung.com","OTD Summary For " + LocalDateTime.now().minusDays(1), html.toString());
        }





    }
}
