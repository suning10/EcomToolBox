package com.ecom.controller;

import com.ecom.common.exception.BaseException;
import com.ecom.common.result.Result;
import com.ecom.pojo.entity.KPI;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ecom.service.KpiService;

import java.util.List;

@RestController
@RequestMapping("admin/kpi")
@Tag(name = "KPI")
@Slf4j
public class KPIController {

    @Autowired
    private KpiService kpiService;

    @GetMapping("/getSummaryByDate")
    public Result<List<KPI>> getKPISummary(@RequestParam String date){

        List<KPI> result = kpiService.getKPISummary(date);
        return Result.success(result);
    }

    @PostMapping("/insertKPI")
    public Result InsertKPI(@RequestBody KPI kpi){

        if(kpi.getCallout().length() > 500) {
            throw new BaseException("call out too long");
        }
        kpiService.insertKPI(kpi);
        return Result.success();
    }
}
