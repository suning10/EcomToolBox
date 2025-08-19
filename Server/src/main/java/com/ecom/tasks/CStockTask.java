package com.ecom.tasks;

import com.ecom.common.utils.exportToCSVUtil;
import com.ecom.mapper.mysql.CStockMapper;
import com.ecom.mapper.mysql.SCRMapper;
import com.ecom.pojo.vo.MinCStockVO;
import com.ecom.service.CStockService;
import com.ecom.service.impl.CStockServiceImpl;
import com.ecom.service.impl.EmailServiceImpl;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
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
    @Scheduled(cron = "0 0 5 * * *")
    public void cStockReport(){
        String filePath = "C:\\Users\\l.qin3\\Documents\\scheduled\\minStockQty.csv" ;
        cStockService.prepareData();
        List<MinCStockVO> result = cStockService.getMinQtyCStock();
        exportToCSVUtil.writeToCsv(filePath,result);
        List<String> attachments = new ArrayList<>();
        attachments.add(filePath);
        try {
            emailService.sendEmail("eCommTeam@sea.samsung.com","CStock Pull@" + LocalDateTime.now(),"Please see attached",attachments);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
