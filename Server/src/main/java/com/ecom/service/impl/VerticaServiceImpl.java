package com.ecom.service.impl;

import com.ecom.common.enumeration.EDDSearchBy;
import com.ecom.common.exception.OutOfRangeException;
import com.ecom.common.result.Result;
import com.ecom.mapper.vertica.VerticaMapper;
import com.ecom.pojo.dto.EDDDTO;
import com.ecom.pojo.dto.ParcelTrackingDTO;
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


}
