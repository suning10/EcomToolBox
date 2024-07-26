package com.ecom.service.impl;

import com.ecom.mapper.vertica.VerticaMapper;
import com.ecom.pojo.dto.ParcelTrackingDTO;
import com.ecom.pojo.entity.FedExTracking;
import com.ecom.pojo.entity.PBTracking;
import com.ecom.pojo.entity.UPSTracking;
import com.ecom.service.VerticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<String> test() {return verticaMapper.test2();}
}
