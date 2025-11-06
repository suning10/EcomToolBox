package com.ecom.tasks;


import com.ecom.common.properties.ExecutableProperties;
import com.ecom.common.utils.LocalFolderUtil;
import com.ecom.mapper.mysql.SCRMapper;
import com.ecom.pojo.entity.SCRTrend;
import com.ecom.pojo.entity.ScrReportSummary;
import com.ecom.service.SCRService;
import com.ecom.service.impl.EmailServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Component
@Slf4j
public class SCRTask {

    @Autowired
    private EmailServiceImpl emailService;
    @Autowired
    private SCRMapper scrMapper;

    @Autowired
    private SCRService scrService;

    @Autowired
    private LocalFolderUtil localFolderUtil;

    @Autowired
    private ExecutableProperties executableProperties;


    @Scheduled(cron = "0 0 4,8 * * *")
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

        //load data here

        scrMapper.truncateTable("stagingscrraw");
        scrMapper.updateStgTable(fileProperty.get("filepath"),5,"stagingscrraw");
        String stgDate = scrMapper.getStgDate("stagingscrraw","Date");
        int tableDateCnt = scrMapper.getTableDate("scrraw","Date",stgDate);
        if(tableDateCnt > 1) return;
        //load into actual table
        scrMapper.insert();
        List<ScrReportSummary> result = scrService.queryScrReportSummary("0");
        //get trend
        List<SCRTrend> scrTrendResult = scrMapper.getSCRTrend(LocalDate.now().minusDays(7).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                                                                LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
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
                "              <th>Total Value</th>" +
                "              <th>Total Qty</th>" +
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
                            "<td>" + scrReportSummary.getSynapseOverage() + "</td>"+
                            "<td>" + scrReportSummary.getTotalValue() + "</td>"+
                            "<td>" + scrReportSummary.getTotalQty() + "</td>"
                    );
            html.append("</tr>");
        }

        html.append("</tbody></table><br> <p> SCR Trend </p>");
        html.append("<table border = '1'>        " +
                "               <thead>" +
                "               <tr>" +
                "               <th>Date</th>" +
                "              <th>Sloc</th>" +
                "              <th>Absolute Gap Cost</th>" +
                "              <th>Absolute Gap</th>" +
                "            </tr>" +
                "        </thead>" +
                "        <tbody> ");

        for (SCRTrend scrTrend:scrTrendResult
        ) {
            html.append("<tr>");
            html.append("<td>" + scrTrend.getDate() + "</td>" +
                    "<td>" + scrTrend.getSloc() + "</td>" +
                    "<td>" + scrTrend.getAbsGap() + "</td>" +
                    "<td>" + scrTrend.getAbsGapValue() + "</td>"
            );
            html.append("</tr>");
        }
        html.append("</tbody></table><br><p>To View Details, Please go to http://drtw-seaqlik.sea.samsung.com:8888/#/scr/scrReasearch </p><br><i>New SCR Research Has Been Added. Go to SCR Research -> SCR Research. You Can Check Activity on Items Having Gaps/Or Performing DOD Research </i> <br><i>SCR Trend is Available at http://drtw-seaqlik.sea.samsung.com:8888/#/history </i></body></html>");
        var fileAttachment = this.generateSCR();
        try{
        emailService.sendEmail("eCommTeam@sea.samsung.com","SCR Summary",html.toString(),fileAttachment);}
        catch (Exception e){
            log.error(e.toString());
        }
    }
    @Scheduled(cron = "0 0 10 * * *")
    public void loadMB51ItemActivity(){

        //check file is up-to-date
        String day = LocalDate.now().toString();
        var filePropertyMB51 = localFolderUtil.getUpdateTime("skuMb51.txt");
        String fileTImeMb51 =  filePropertyMB51.get("updateTime").substring(0,10);
        var filePropertyIA = localFolderUtil.getUpdateTime("item_activity.txt");
        String fileTImeIA =  filePropertyIA.get("updateTime").substring(0,10);
        if(!fileTImeMb51.equals(day) || !fileTImeIA.equals(day)) {
            try{
                String body = LocalDate.now().minusDays(1).toString();
                emailService.sendEmail("eCommTeam@sea.samsung.com", body + " SCR NERP and Synpase Failed to load, Please update manually","At " + LocalDateTime.now().toString());}
            catch (Exception e){
                log.error("error in sending SCR Loading Email NERP and IA" + e.getMessage());
            }
            return;
        }

        //start loading MB51
        //update stgTable
        scrMapper.truncateTable("stg_scr_nerp");
        scrMapper.updateStgTable(filePropertyMB51.get("filepath"),5,"stg_scr_nerp");
        scrMapper.updateComma("stg_scr_nerp");
        // check if data exists
        String stgDate = scrMapper.getStgDate("stg_scr_nerp","post_date");
        int tableDateCnt = scrMapper.getTableDate("scr_nerp","post_date",stgDate);
        if(tableDateCnt > 1) return;
        //load into actual table
        scrMapper.insertMB51();

        // start loading Item Activity

        //update stgTable
        scrMapper.truncateTable("stg_scr_item_activity");
        scrMapper.updateStgTable(filePropertyIA.get("filepath"),1,"stg_scr_item_activity");
        //update comma
        scrMapper.updateComma("stg_scr_item_activity");
        // check if data exists
        stgDate = scrMapper.getStgDate("stg_scr_item_activity","transaction_date");
        tableDateCnt = scrMapper.getTableDate("scr_item_activity","transaction_date",stgDate);
        if(tableDateCnt > 1) return;
        //load into actual table
        scrMapper.insertItemActivity();

        String body = LocalDate.now().minusDays(1).toString();

        try{
        emailService.sendEmail("eCommTeam@sea.samsung.com",body + " SCR NERP and Synpase have been loaded","At " + LocalDateTime.now().toString());}
        catch (Exception e){
            log.error("error in sending SCR Loading Email NERP and IA" + e.getMessage());
        }

    }

    private List<String> generateSCR(){
        String end = LocalDate.now().toString();
        List<String> pathStringList = new ArrayList<>();
        String pathStringBlockUnblock = "C:\\Users\\l.qin3\\Documents\\uploads\\SCRMaintainBlockUnblock.csv";
        pathStringList.add(pathStringBlockUnblock);
        String pathStringMovetoWC1EorWR2E = "C:\\Users\\l.qin3\\Documents\\uploads\\SCRMaintainMovement.csv";
        pathStringList.add(pathStringMovetoWC1EorWR2E);
        String pathStringRemainToBlock = "C:\\Users\\l.qin3\\Documents\\uploads\\SCRMaintainRemainToBlock.csv";
        pathStringList.add(pathStringRemainToBlock);
        for (String pathString:pathStringList
        ) {

            Path filePath = Paths.get(pathString);
            try {
                if (Files.exists(filePath)) {
                    Files.deleteIfExists(filePath);
                    filePath.toFile().createNewFile();
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Material");
                stringBuilder.append(',');
//                stringBuilder.append("Description");
//                stringBuilder.append(',');
                stringBuilder.append("QINerp");
                stringBuilder.append(',');
                stringBuilder.append("QI3pl");
                stringBuilder.append(',');
                stringBuilder.append("QIGap");
                stringBuilder.append(',');
                stringBuilder.append("UNSNerp");
                stringBuilder.append(',');
                stringBuilder.append("UNS3pl");
                stringBuilder.append(',');
                stringBuilder.append("unsGap");
                stringBuilder.append(',');
                stringBuilder.append("blkNerp");
                stringBuilder.append(',');
                stringBuilder.append("blk3pl");
                stringBuilder.append(',');
                stringBuilder.append("blkGap");
                stringBuilder.append(',');
                stringBuilder.append("totalNerp");
                stringBuilder.append(',');
                stringBuilder.append("total3pl");
                stringBuilder.append(',');
                stringBuilder.append("totalGap");
                stringBuilder.append(',');
                stringBuilder.append("valType");
                stringBuilder.append(',');
                stringBuilder.append("sloc");
                stringBuilder.append(',');
                if (pathString.equals(pathStringRemainToBlock)){
                    stringBuilder.append("blocked qty in step 2");
                    stringBuilder.append(',');
                    stringBuilder.append("action");
                    stringBuilder.append(',');
                    stringBuilder.append("Remained to Block");
                }
                if (pathString.equals(pathStringBlockUnblock)){
                    stringBuilder.append("action");
                    stringBuilder.append(',');
                    stringBuilder.append("Qty to Block or Unblock");
                }
                if (pathString.equals(pathStringMovetoWC1EorWR2E)){
                    stringBuilder.append("action1");
                    stringBuilder.append(',');
                    stringBuilder.append("qtyToMove1");
                    stringBuilder.append(',');
                    stringBuilder.append("action2");
                    stringBuilder.append(',');
                    stringBuilder.append("qtyToMove2");
                }
                stringBuilder.append(',');
                stringBuilder.append("date");
                stringBuilder.append(System.lineSeparator());

                if (pathString.equals(pathStringBlockUnblock)) {
                    var result_block = scrMapper.getBlockUnblock(end);
                    for (var entity: result_block
                    ) {
                        stringBuilder.append(entity.getMaterial());
                        stringBuilder.append(',');
//                        stringBuilder.append(quote(entity.getDescription()));
//                        stringBuilder.append(',');
                        stringBuilder.append(entity.getQiNerp());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getQi3pl());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getQiGap());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getUnsNerp());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getUns3pl());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getUnsGap());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getBlkNerp());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getBlk3pl());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getBlkGap());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getTotalNerp());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getTotal3pl());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getTotalGap());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getValType());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getSloc());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getAction());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getQtyToBlock());
                        stringBuilder.append(',');
                        stringBuilder.append(quote(entity.getDate()));

                    }
                }
                else if (pathString.equals(pathStringMovetoWC1EorWR2E)) {
                    var result_movement = scrMapper.getMovement(end);
                    for (var entity: result_movement
                    ) {
                        stringBuilder.append(entity.getMaterial());
                        stringBuilder.append(',');
//                        stringBuilder.append(quote(entity.getDescription()));
//                        stringBuilder.append(',');
                        stringBuilder.append(entity.getQiNerp());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getQi3pl());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getQiGap());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getUnsNerp());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getUns3pl());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getUnsGap());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getBlkNerp());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getBlk3pl());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getBlkGap());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getTotalNerp());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getTotal3pl());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getTotalGap());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getValType());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getSloc());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getAction1());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getQtyToMove1());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getAction2());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getQtyToMove2());
                        stringBuilder.append(',');
                        stringBuilder.append(quote(entity.getDate()));

                    }
                } else {
                    var result_remain = scrMapper.getRemainToBlock(end);
                    for (var entity: result_remain
                    ) {
                        stringBuilder.append(entity.getMaterial());
                        stringBuilder.append(',');
//                        stringBuilder.append(quote(entity.getDescription()));
//                        stringBuilder.append(',');
                        stringBuilder.append(entity.getQiNerp());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getQi3pl());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getQiGap());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getUnsNerp());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getUns3pl());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getUnsGap());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getBlkNerp());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getBlk3pl());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getBlkGap());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getTotalNerp());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getTotal3pl());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getTotalGap());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getValType());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getSloc());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getBlockedQty());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getAction());
                        stringBuilder.append(',');
                        stringBuilder.append(entity.getQtyToBlock());
                        stringBuilder.append(',');
                        stringBuilder.append(quote(entity.getDate()));
                    }
                }
                stringBuilder.append(System.lineSeparator());
                Files.write(filePath, stringBuilder.toString().getBytes(), StandardOpenOption.WRITE);
            } catch (IOException e) {
                log.error(e.toString());
            }


        }

        return pathStringList;
    }

    private String quote(String text){
        if(text.contains(",")|| text.contains("\"") || text.contains("\n")){
            text = "\"" + text.replace("\"","\"\"") +  "\"";

        }
        return text;
    }
}
