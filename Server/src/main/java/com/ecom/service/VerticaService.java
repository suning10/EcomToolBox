package com.ecom.service;

import com.ecom.pojo.dto.ParcelTrackingDTO;
import com.ecom.pojo.entity.FedExTracking;
import com.ecom.pojo.entity.PBTracking;
import com.ecom.pojo.entity.UPSTracking;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VerticaService {

    List<PBTracking> pbQueryByDeliveryDate(String start, String end);

    List<String> testQuery();

    List<PBTracking> pbQueryByShipDate(String start, String end);

    List<UPSTracking> queryUPS(ParcelTrackingDTO parcelTrackingDTO);

    int pbQueryByDeliveryDateSummary(String start, String end);

    int pbQueryByShipDateSummary(String start, String end);

    List<FedExTracking> queryFedEx(ParcelTrackingDTO parcelTrackingDTO);
}
