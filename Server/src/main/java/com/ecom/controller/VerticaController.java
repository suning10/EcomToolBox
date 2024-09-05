package com.ecom.controller;

import com.ecom.common.result.Result;
import com.ecom.pojo.dto.EDDDTO;
import com.ecom.pojo.dto.ParcelTrackingDTO;
import com.ecom.pojo.entity.*;
import com.ecom.service.VerticaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/vertica")
@Tag(name = "vertica")
public class VerticaController {

    @Autowired
    private VerticaService verticaService;

    @GetMapping("/test")
    public List<String> test(){
        return verticaService.testQuery();
    }

    @GetMapping("/pbDelivered")
    @Operation(summary = "PBDelivery")
    public Result<List<PBTracking>> getPBbyDeliveryDate(@RequestParam String start, @RequestParam String end ){

        List<PBTracking> resultSet = verticaService.pbQueryByDeliveryDate(start,end);

        return Result.success(resultSet);
    }

    @GetMapping("/pbShip")
    @Operation(summary = "PBShip")
    public Result<List<PBTracking>> getPBbyShipDate(@RequestParam String start, @RequestParam String end ){

        List<PBTracking> resultSet = verticaService.pbQueryByShipDate(start,end);

        return Result.success(resultSet);
    }

    @GetMapping("/pbDeliveredSummary")
    @Operation(summary = "PBDeliverySummary")
    public Result<Integer> getPBbyDeliveryDateSummary(@RequestParam String start, @RequestParam String end ){

        int cnt = verticaService.pbQueryByDeliveryDateSummary(start,end);

        return Result.success(cnt);
    }

    @GetMapping("/pbShipSummary")
    @Operation(summary = "PBShipSummary")
    public Result<Integer> getPBbyShipDateSummary(@RequestParam String start, @RequestParam String end ){

        int resultSet = verticaService.pbQueryByShipDateSummary(start,end);

        return Result.success(resultSet);
    }

    @PostMapping("/ups")
    @Operation(summary = "UPSTracking")
    public Result<List<UPSTracking>> getUPS(@RequestBody ParcelTrackingDTO parcelTrackingDTO){

        List<UPSTracking> resultSet = verticaService.queryUPS(parcelTrackingDTO);

        return Result.success(resultSet);
    }

    @PostMapping("/fedex")
    @Operation(summary = "FedExTracking")
    public Result<List<FedExTracking>> getFedEx(@RequestBody ParcelTrackingDTO parcelTrackingDTO){

        List<FedExTracking> resultSet = verticaService.queryFedEx(parcelTrackingDTO);

        return Result.success(resultSet);
    }

    @PostMapping("/edd")
    @Operation(summary = "EDD")
    public Result<List<EDD>> getEdd(@RequestBody EDDDTO eDDdto){

        List<EDD> resultSet = verticaService.queryEDD(eDDdto);

        return Result.success(resultSet);
    }

    @GetMapping("/originScanUPS")
    @Operation(summary = "originScanUPS")
    public Result<List<OriginScanUPS>> getOriginScanUPS(@RequestParam String start, @RequestParam String end ){

        List<OriginScanUPS> resultSet = verticaService.originScanUPS(start,end);

        return Result.success(resultSet);
    }


}
