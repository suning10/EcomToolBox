package com.ecom.tasks;


import com.ecom.common.utils.LocalFolderUtil;
import com.ecom.common.utils.exportToCSVUtil;
import com.ecom.service.impl.EmailServiceImpl;
import com.ecom.service.impl.SCRAsyncTaskImpl;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
public class AsyncTask {

    @Autowired
    SCRAsyncTaskImpl scrAsyncTask;

    @Autowired
    private EmailServiceImpl emailService;

    //@Scheduled(cron = "0 39 16 * * *")
    public void testAsync() throws ExecutionException, InterruptedException {

        CompletableFuture task1 = scrAsyncTask.testAysnc1().thenRun(()->{
            System.out.println("then run without any input, usually used in last callback");
        });
        CompletableFuture<String> task2 = scrAsyncTask.testAysnc2().thenApply(name -> {return "then apply task2 " + name ;});

        System.out.println(task2.get());

        System.out.println("All task completed");


    }

    //@Scheduled(cron = "0 55 13 * * *")
    public void testAsync1() throws ExecutionException, InterruptedException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        List<String> pathStringList = new ArrayList<>();
        String file1 = "C:\\Users\\l.qin3\\Documents\\scheduled\\Synpase" + LocalDateTime.now().format(formatter).toString() +".csv";
        String file2 = "C:\\Users\\l.qin3\\Documents\\scheduled\\Nerp"+ LocalDateTime.now().format(formatter).toString() + ".csv";
        CompletableFuture task1 = scrAsyncTask.findMissingTransactionNERP("2025-03-20","2025-03-24").
                                        thenAccept(result -> exportToCSVUtil.writeToCsv(file1,result));
        CompletableFuture task2 = scrAsyncTask.findMissingTransactionSynapse("2025-01-12","2025-03-19").
                                        thenAccept(result -> exportToCSVUtil.writeToCsv(file2,result));

        CompletableFuture<Void> tasks = CompletableFuture.allOf(task1,task2);
        tasks.thenRun(()->{
            try {
                if(LocalFolderUtil.fileExists(file1)) pathStringList.add(file1);
                if(LocalFolderUtil.fileExists(file2)) pathStringList.add(file2);
                emailService.sendEmail("l.qin3@partner.sea.samsung.com","Your Scheduled File is Ready","Please see attached for Missing Transactions",pathStringList);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }).thenRun(()->{
            // delete the file being exported
            LocalFolderUtil.deleteFile(file1);
            LocalFolderUtil.deleteFile(file2);
        });

        System.out.println("All task completed");


    }

}
