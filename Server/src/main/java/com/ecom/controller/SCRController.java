package com.ecom.controller;


import com.ecom.common.enumeration.UploadStatus;
import com.ecom.common.result.Result;
import com.ecom.pojo.dto.SKUSearchDTO;
import com.ecom.pojo.entity.*;
import com.ecom.pojo.vo.SKUSummaryByMvmTypeVO;
import com.ecom.service.SCRService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("admin/scr")
@Tag(name = "SCR")
@Slf4j
public class SCRController {

    @Autowired
    private SCRService scrService;


    /*
    has references, joined by reference, sloc and sku
     */
    @PostMapping("/skuComparision")
    @Operation(summary = "SKUComparison")
    public Result<List<SKUComparison>> skuComparison(@RequestBody SKUSearchDTO skuSearchDTO) {

        var result = scrService.querySKUComparison(skuSearchDTO.getSku(),skuSearchDTO.getStart(),skuSearchDTO.getEnd(),skuSearchDTO.getSloc());
        return Result.success(result);
    }

        /*
    has references, joined by reference, sloc and sku
     */
    @PostMapping("/skuComparisionAll")
    @Operation(summary = "SKUComparisonAll")
    public Result<List<SKUComparison>> skuComparisonAll(@RequestBody SKUSearchDTO skuSearchDTO) {

        List<SKUComparison> result = scrService.querySKUComparisonAll(skuSearchDTO.getSku(),skuSearchDTO.getStart(),skuSearchDTO.getEnd(),skuSearchDTO.getSloc());
        return Result.success(result);
    }

    /*
        has references, joined by reference, sloc and sku
        no reference in item activity
    */
    @PostMapping("/skuComparisionMissingNerp")
    @Operation(summary = "SKUComparisonNERP")
    public Result<List<SKUActivityNERP>> skuComparisonMissingNerp(@RequestBody SKUSearchDTO skuSearchDTO) {
        List<SKUActivityNERP> result;
        if(skuSearchDTO.getSloc().toLowerCase().equals("all")) {
            result = scrService.querySKUComparisonMissingNerp(skuSearchDTO.getSku(),skuSearchDTO.getStart(),skuSearchDTO.getEnd());
        }
        else{
            result = scrService.querySKUComparisonMissingNerp(skuSearchDTO.getSku(),skuSearchDTO.getStart(),skuSearchDTO.getEnd(),skuSearchDTO.getSloc());
        }
        return Result.success(result);
    }



    /*
    has references, joined by reference, sloc and sku
    no reference in NERP
*/
    @PostMapping("/skuComparisionMissingItemActivity")
    @Operation(summary = "SKUComparisonItemActivity")
    public Result<List<SKUActivitySynapse>> skuComparisonMissingItemActivity(@RequestBody SKUSearchDTO skuSearchDTO) {

        List<SKUActivitySynapse> result ;
        if(skuSearchDTO.getSloc().toLowerCase().equals("all")) {
            result = scrService.querySKUComparisonMissingSynapse(skuSearchDTO.getSku(),skuSearchDTO.getStart(),skuSearchDTO.getEnd());
        }
        else{
            result = scrService.querySKUComparisonMissingSynapse(skuSearchDTO.getSku(),skuSearchDTO.getStart(),skuSearchDTO.getEnd(),skuSearchDTO.getSloc());
        }

        return Result.success(result);
    }

    /*
    no reference, adjustment, show by SKU , nerp
     */
    @PostMapping("/skuDetailNERPAdj")
    @Operation(summary = "SKUDetailNERPAdj")
    public Result<List<SKUActivityNERP>> skuDetailNERPAdj(@RequestBody SKUSearchDTO skuSearchDTO) {

        List<SKUActivityNERP> result = scrService.querySKUDetailNERPAdj(skuSearchDTO);
        return Result.success(result);
    }

    /*
    no reference, adjustment, show by SKU, item activity
     */
    @PostMapping("/skuDetailSynapseAdj")
    @Operation(summary = "SKUDetailSynapseAdj")
    public Result<List<SKUActivitySynapse>> skuDetailSynapseAdj(@RequestBody SKUSearchDTO skuSearchDTO) {

        List<SKUActivitySynapse> result = scrService.querySKUDetailSynapseAdj(skuSearchDTO);
        return Result.success(result);
    }

    /*
    no reference, adjustment, total qty , item activity
     */
    @PostMapping("/skuSummarySynapseAdj")
    @Operation(summary = "SKUDetailSynapseAdj")
    public Result<SKUSummaryByMvmTypeVO> skuSummarySynapseAdj(@RequestBody SKUSearchDTO skuSearchDTO) {

        SKUSummaryByMvmTypeVO result = scrService.querySKUSummarySynapseAdj(skuSearchDTO);
        return Result.success(result);
    }

    /*
    no reference, adjustment, total qty , nerp
     */
    @PostMapping("/skuSummaryNERPAdj")
    @Operation(summary = "SKUDetailNERPAdj")
    public Result<SKUSummaryByMvmTypeVO> skuSummaryNERPAdj(@RequestBody SKUSearchDTO skuSearchDTO) {

        SKUSummaryByMvmTypeVO result = scrService.querySKUSummaryNERPAdj(skuSearchDTO);
        return Result.success(result);
    }

    /*
        SCR Report Summary all
     */
    @GetMapping("/scrReportSummary")
    @Operation(summary = "SKUReportSummary")
    public Result<List<ScrReportSummary>> skuReportSummary(@RequestParam String date) {

        List<ScrReportSummary> result = scrService.queryScrReportSummary(date);
        return Result.success(result);
    }

    /*
    SCR Report gap
 */
    @GetMapping("/scrReportGap")
    @Operation(summary = "SKUReportGap")
    public Result<List<ScrReportGap>> skuReportGap(@RequestParam String date, @RequestParam String sloc) {

        List<ScrReportGap> result = scrService.queryScrReportGap(date,sloc);
        return Result.success(result);
    }

    /*
    SCR Report Detail
    */
    @GetMapping("/scrReportDetail")
    @Operation(summary = "SKUReportDetail")
    public Result<List<ScrReportRaw>> skuReportDetail(@RequestParam String date, @RequestParam String sloc) {

        List<ScrReportRaw> result = scrService.queryScrReportDetail(date,sloc);
        return Result.success(result);
    }

    /*
        SCR Report DOD
    */
    @GetMapping("/scrReportDOD")
    @Operation(summary = "scrReportDOD")
    public Result<List<ScrReportDod>> skuReportDOD(@RequestParam String sloc) {

        List<ScrReportDod> result = scrService.queryScrReportDOD(sloc);
        return Result.success(result);
    }


    /*
    SCR File Import
    */
    @PostMapping("/scrReportImport")
    @Operation(summary = "scrReportImport")
    public Result skuReportImport(MultipartFile file) {

        UploadStatus uploadStatus =  scrService.uploadSCRReport(file);
        if(uploadStatus != UploadStatus.LoadSuccess) return Result.error("Data Already Exists or Load Failed");

        return Result.success();
    }

    /*
        MB51 Import
    */
    @PostMapping("/skuMB51Import")
    @Operation(summary = "scrMB51Import")
    public Result skuMB51Import(MultipartFile file) {

        UploadStatus uploadStatus = scrService.uploadSkuMB51(file);
        if(uploadStatus != UploadStatus.LoadSuccess) return Result.error("Data Already Exists or Load Failed");
        return Result.success();
    }

    /*
    Item Activity Import
*/
    @PostMapping("/skuItemActivityImport")
    @Operation(summary = "scrItemActivityImport")
    public Result skuItemActivityImport(MultipartFile file) {

        UploadStatus uploadStatus = scrService.uploadSkuItemActivity(file);
        if(uploadStatus != UploadStatus.LoadSuccess) return Result.error("Data Already Exists or Load Failed");
        return Result.success();
    }
}



