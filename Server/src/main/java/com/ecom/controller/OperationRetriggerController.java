package com.ecom.controller;


import com.ecom.common.result.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/retrigger")
@Tag(name = "retrigger")
@Slf4j
public class OperationRetriggerController {

    @GetMapping("/taskRetrigger")
    public Result retriggerTask(@RequestParam int taskID){

        return Result.success();
    }
}
