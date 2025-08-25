package com.ecom.controller;


import com.ecom.common.result.Result;
import com.ecom.pojo.dto.PUMIDTO;
import com.ecom.pojo.vo.MinCStockSummaryVO;
import com.ecom.pojo.vo.MinCStockVO;
import com.ecom.service.CStockService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/cstock")
@Tag(name = "Cstock")
@Slf4j
public class CStockController {

    @Autowired
    private CStockService cStockService;

    @GetMapping("/minQty")
    public Result<List<MinCStockVO>> getMinQty(){

        List<MinCStockVO> result = cStockService.getMinQtyCStock();
        return Result.success(result);

    }

    @GetMapping("/minQtySummary")
    public Result<List<MinCStockSummaryVO>> getMinQtySummary(){

        List<MinCStockSummaryVO> result = cStockService.getMinQtyCStockSummary();
        return Result.success(result);

    }

    @PostMapping("/uploadPUMI")
    public Result uploadPUMI(@RequestBody List<PUMIDTO> pumidtos){

        cStockService.uploadPUMI(pumidtos);
        return Result.success();

    }
}
