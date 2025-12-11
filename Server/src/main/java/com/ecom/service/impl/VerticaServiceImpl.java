package com.ecom.service.impl;

import com.ecom.common.enumeration.EDDSearchBy;
import com.ecom.common.exception.OutOfRangeException;
import com.ecom.common.result.Result;
import com.ecom.mapper.vertica.VerticaMapper;
import com.ecom.pojo.dto.EDDDTO;
import com.ecom.pojo.dto.ParcelTrackingDTO;
import com.ecom.pojo.dto.RDOVerticaDTO;
import com.ecom.pojo.entity.*;
import com.ecom.service.VerticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class VerticaServiceImpl implements VerticaService {

    @Autowired
    private VerticaMapper verticaMapper;

    @Override
    public List<PBTracking> pbQueryByDeliveryDate(String start, String end) {
        return verticaMapper.queryByDeliveryDatePB(start,end);
    }

    @Override
    public List<String> testQuery() {
        return verticaMapper.test2();
    }

    @Override
    public List<PBTracking> pbQueryByShipDate(String start, String end) {
        return verticaMapper.queryByShipPB(start,end);
    }

    @Override
    public List<UPSTracking> queryUPS(ParcelTrackingDTO parcelTrackingDTO) {
        // breakDTO into query string
        //StringBuilder stringBuilder = new StringBuilder();
        //build trakcing number string
//        for(String s : parcelTrackingDTO.getTrackingId()){
//            stringBuilder.append("'" + s + "',");
//        }
        // remove the last comma
        //stringBuilder.deleteCharAt(stringBuilder.length() -1);

        //change to lower case
        List<String> lowerCaseTrackingIds = parcelTrackingDTO.getTrackingId();
        lowerCaseTrackingIds.replaceAll(String::toLowerCase);

        List<UPSTracking> result = verticaMapper.queryUPSTracking(lowerCaseTrackingIds,parcelTrackingDTO.getIsActive());

        return result;
    }

    @Override
    public int pbQueryByDeliveryDateSummary(String start, String end) {
        return verticaMapper.queryByDeliveryDatePBSummary(start, end);
    }

    @Override
    public int pbQueryByShipDateSummary(String start, String end) {
        return verticaMapper.queryByShipDatePBSummary(start, end);
    }

    @Override
    public List<FedExTracking> queryFedEx(ParcelTrackingDTO parcelTrackingDTO) {
        List<String> lowerCaseTrackingIds = parcelTrackingDTO.getTrackingId();
        lowerCaseTrackingIds.replaceAll(String::toLowerCase);
        return verticaMapper.queryFedExTracking(lowerCaseTrackingIds,parcelTrackingDTO.getIsActive());
    }

    @Override
    public List<EDD> queryEDD(EDDDTO eDDdto) {

        if(eDDdto.getSearchFlag().equals("PO")) {
            return verticaMapper.queryEDD(eDDdto.getIdList(),0);
        }

        if(eDDdto.getSearchFlag().equals("DO")) {
            return verticaMapper.queryEDD(eDDdto.getIdList(),1);
        }
        if(eDDdto.getSearchFlag().equals("TRACKING")) {
            return verticaMapper.queryEDD(eDDdto.getIdList(),2);
        }

        return null;
    }

    @Override
    public List<OriginScanUPS> originScanUPS(String start, String end) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(start,dateTimeFormatter);
        LocalDate endDate = LocalDate.parse(end,dateTimeFormatter);
        //check if date range is too wide
        long days = ChronoUnit.DAYS.between(startDate,endDate);
        if(days > 30) throw new OutOfRangeException("Range Too Wide");

        return verticaMapper.queryOriginScanUPS(start,end);
    }


    @Override
    public List<OriginScanUPS> shipNotTenderUPS(String start, String end) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(start,dateTimeFormatter);
        LocalDate endDate = LocalDate.parse(end,dateTimeFormatter);
        //check if date range is too wide
        long days = ChronoUnit.DAYS.between(startDate,endDate);
        if(days > 30) throw new OutOfRangeException("Range Too Wide");

        return verticaMapper.queryShipNotTenderUPS(start,end);
    }

    @Override
    public List<EDDCe> queryEDDCe(EDDDTO eDDdto) {
        if(eDDdto.getSearchFlag().equals("PO")) {
            return verticaMapper.queryEDDCe(eDDdto.getIdList(),0);
        }

        if(eDDdto.getSearchFlag().equals("DO")) {
            return verticaMapper.queryEDDCe(eDDdto.getIdList(),1);
        }
        if(eDDdto.getSearchFlag().equals("TRACKING")) {
            return verticaMapper.queryEDDCe(eDDdto.getIdList(),2);
        }

        return null;
    }

    @Override
    public List<RDOVertica> queryRDO(RDOVerticaDTO rdoDto) {

        if(rdoDto.getSearchFlag().equals("PO")) return verticaMapper.queryRDO(rdoDto.getIdList(),0);

        if(rdoDto.getSearchFlag().equals("RDO")) return verticaMapper.queryRDO(rdoDto.getIdList(),1);

        if(rdoDto.getSearchFlag().equals("RMA")) return verticaMapper.queryRDO(rdoDto.getIdList(),2);

        return null;
    }

    @Override
    public List<EDDSummary> queryOTD() {

        List<EDDSummary> summary = verticaMapper.getOTDForWeek();
        summary.stream().forEach(edd -> {
            double num = Double.parseDouble(edd.getOtd());
            edd.setOtd(String.format("%.2f%%",num * 100));
        });

        return summary;
    }

    @Override
    public List<EDDOTDDetail> queryOTDDetailYesterday() {
        List<EDDOTDDetail> result  = verticaMapper.getOTDDetail();
//        result.stream().forEach(data->{
//            data.setTracking("'" + data.getTracking());
//        });
        return result;
    }

    @Override
    public List<EDDCarrierSummary> queryOTDByCarrier() {
        List<EDDCarrierSummary> summary = verticaMapper.getOTDForWeekByCarrier();
        summary.stream().forEach(edd -> {
            double num = Double.parseDouble(edd.getOtd());
            edd.setOtd(String.format("%.2f%%",num * 100));
        });

        return summary;

    }

    @Override
    public List<EDDCarrierSummary> queryOTDSummaryByCarrier() {
        List<EDDCarrierSummary> summary = verticaMapper.getOTDForWeekByCarrierSummary();
        summary.stream().forEach(edd -> {
            double num = Double.parseDouble(edd.getOtd());
            edd.setOtd(String.format("%.2f%%",num * 100));
        });

        return summary;

    }


}
