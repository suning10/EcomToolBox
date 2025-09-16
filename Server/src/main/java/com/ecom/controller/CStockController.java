package com.ecom.controller;


import com.ecom.common.result.Result;
import com.ecom.pojo.dto.PUMIDTO;
import com.ecom.pojo.dto.skuCategoryDTO;
import com.ecom.pojo.entity.MinCStock;
import com.ecom.pojo.vo.MinCStockSummaryVO;
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
    public Result<List<MinCStock>> getMinQty(){

        List<MinCStock> result = cStockService.getMinQtyCStock();
        return Result.success(result);

    }

    @GetMapping("/minQtySummary")
    public Result<List<MinCStockSummaryVO>> getMinQtySummary(){

        List<MinCStockSummaryVO> result = cStockService.getMinQtyCStockSummary();
        return Result.success(result);

    }



    @PostMapping("/uploadSKUCategory")
    public Result uploadSKUCategory(@RequestBody List<skuCategoryDTO> skuCategoryDTOS){

        cStockService.uploadSKUCategory(skuCategoryDTOS);
        return Result.success();
    }

    @GetMapping("/getSKUCategory")
    public Result<List<skuCategoryDTO>> getSKUCategory(){

        List<skuCategoryDTO> result =  cStockService.getSkuCateogy();
        return Result.success(result);
    }

    @GetMapping("/getSKUCategoryBySKU")
    public Result<skuCategoryDTO> getSKUCategoryBySku(@RequestParam String sku){

        skuCategoryDTO result =  cStockService.getSkuCateogyBYSKU(sku);
        return Result.success(result);
    }


    @GetMapping("/delSKUCategory")
    public Result<List<skuCategoryDTO>> delSKUCategory(@RequestParam String sku){

        cStockService.delSkuCateogy(sku);
        return Result.success();
    }

    @PutMapping
    public Result<List<skuCategoryDTO>> updateSKUCategory(@RequestBody skuCategoryDTO skuCategoryDTO){

        cStockService.updateSkuCateogy(skuCategoryDTO);
        return Result.success();
    }

    /*
    pumi related
     */
    // date = all or exact date
    public Result<List<PUMIDTO>> getPUMI(@RequestParam String date){

        if(date.equals("all")) {
            List<PUMIDTO> result = cStockService.getPUMIAll();
            return Result.success(result);
        }

        List<PUMIDTO> result = cStockService.getPUMIByDate(date);

        return Result.success(result);

    }

    @PostMapping("/uploadPUMI")
    public Result uploadPUMI(@RequestBody List<PUMIDTO> pumidtos){

        cStockService.uploadPUMI(pumidtos);
        return Result.success();

    }
}
