package com.ecom.tasks;

import com.ecom.common.properties.ExecutableProperties;
import com.ecom.common.utils.LocalFolderUtil;
import com.ecom.mapper.mysql.SCRMapper;
import com.ecom.pojo.entity.ScrReportSummary;
import com.ecom.service.impl.EmailServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.List;


@Component
@Slf4j
public class SCRTask {

    @Autowired
    private EmailServiceImpl emailService;
    @Autowired
    private SCRMapper scrMapper;

    @Autowired
    private LocalFolderUtil localFolderUtil;

    @Autowired
    private ExecutableProperties executableProperties;


    @Scheduled(cron = "0 0 8 * * *")
    public void invokeSAP(){
        try{
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command(executableProperties.getPath());
            //processBuilder.command("C:\\Users\\l.qin3\\source\\repos\\ConsoleApp1\\ConsoleApp1\\bin\\Debug\\ConsoleApp1.exe");
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            if(exitCode == 0) {return;}
            else{
                log.error("successfully invoked sap but failed during executing");
                return;
            }
        }
        catch (Exception e){
            log.error("error happened while executing sap.exe");
            log.error(e.getMessage());
        }
    }

    @Scheduled(cron = "0 15 8 * * *")
    public void scrReport(){

        String day = LocalDate.now().toString();

        //check if file is up to date
        var fileProperty = localFolderUtil.getUpdateTime("scrReport.txt");
        String fileTIme =  fileProperty.get("updateTime").substring(0,10);
        if(!fileTIme.equals(day)) return;
//        int tableDateCnt = scrMapper.getTableDate("scrraw","Date",day);
//        if(tableDateCnt < 2) return;
//        //load data here
        scrMapper.truncateTable("stagingscrraw");
        scrMapper.updateStgTable(fileProperty.get("filepath"),5,"stagingscrraw");
        String stgDate = scrMapper.getStgDate("stagingscrraw","Date");
        int tableDateCnt = scrMapper.getTableDate("scrraw","Date",stgDate);
        if(tableDateCnt > 1) return;
        //load into actual table
        scrMapper.insert();

        List<ScrReportSummary> result = scrMapper.getSCRReportSummary("0");
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
                "        <p> Please see Today's SCR Summary below </p>");
        
        html.append("<table border = '1'>        " +
                "               <thead>" +
                "               <tr>" +
                "               <th>SLoc</th>" +
                "              <th>Absolute Gap Cost</th>" +
                "              <th>Net Gap Cost</th>" +
                "              <th>Absolute Gap</th>" +
                "              <th>Net Gap</th>" +
                "              <th>% SKUs with Error</th>" +
                "              <th># SKUs with Error</th>" +
                "              <th>NERP Overage</th>" +
                "              <th>Synapse Overage</th>" +
                "            </tr>" +
                "        </thead>" +
                "        <tbody> ");

        for (ScrReportSummary scrReportSummary:result
             ) {
            html.append("<tr>");
            html.append("<td>" + scrReportSummary.getSloc() + "</td>" +
                    "<td>" + scrReportSummary.getAbsoluteGapCost() + "</td>" +
                    "<td>" + scrReportSummary.getNetGapCost() + "</td>" +
                    "<td>" + scrReportSummary.getAbsoluteGap() + "</td>" +
                    "<td>" + scrReportSummary.getNetGap() + "</td>" + "<td>" +
                            scrReportSummary.getSkuWithError()+ "</td>" + "<td>" + scrReportSummary.getSkuWithErrorNumber() + "</td>" +
                            "<td>" + scrReportSummary.getNerpOverage() + "</td>" +
                            "<td>" + scrReportSummary.getSynapseOverage() + "</td>"
                    );
            html.append("</tr>");
        }

        html.append("</tbody></table><br><p>To View Details, Please go to http://drtw-seaqlik.sea.samsung.com:8888/#/scr/scrReasearch </p><br><i>New SCR Research Has Been Added. Go to SCR Research -> SCR Research. You Can Check Activity on Items Have Gaps </i></body></html>");
        try{
        emailService.sendEmailHTML("eCommTeam@sea.samsung.com","SCR Summary",html.toString());}
        catch (Exception e){
            log.error(e.toString());
        }
    }
}
