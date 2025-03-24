package com.ecom.service.impl;


import com.ecom.mapper.mysql.SCRMapper;
import com.ecom.pojo.entity.SKUActivityNERP;
import com.ecom.pojo.entity.SKUActivitySynapse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Service
public class SCRAsyncTaskImpl {

    @Autowired
    @Qualifier("customExecutor")
    private Executor executor;

    @Autowired
    private SCRMapper scrMapper;

    @Async("customExecutor")
    public CompletableFuture<Void> testAysnc1(){
        return CompletableFuture.runAsync(()->{
            try {
                Thread.sleep(8000);
                String result = "Task1 Completed on " + Thread.currentThread().getName();
                System.out.println(result);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        },executor);

    }

    @Async("customExecutor")
    public CompletableFuture<String> testAysnc2(){
        return CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(5000);
                String result = "Task2 Completed on " + Thread.currentThread().getName();
                System.out.println(result);
                return "Task 2 is completed";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        },executor);

    }


    // return a synapse transaction, reference in Synapse but not in Nerp
    @Async("customExecutor")
    public CompletableFuture<List<SKUActivitySynapse>> findMissingTransactionNERP(String start, String end){
        return CompletableFuture.supplyAsync(()->{
//            String resultMsg = "TaskNERP Completed on " + Thread.currentThread().getName();
//            System.out.println(resultMsg);
            List<SKUActivitySynapse> result =  scrMapper.getMissingTransactionNerp(start, end);
            return result;


        },executor);

    }

    // return a Nerp transaction, reference in Synapse but not in Synapse
    @Async("customExecutor")
    public CompletableFuture<List<SKUActivityNERP>> findMissingTransactionSynapse(String start, String end){
        return CompletableFuture.supplyAsync(()->{
//            String resultMsg = "TaskSynapse Completed on " + Thread.currentThread().getName();
//            System.out.println(resultMsg);
            List<SKUActivityNERP> result =  scrMapper.getMissingTransactionSynapse(start, end);
            return result;
        },executor);

    }
}
