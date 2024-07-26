package com.ecom.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UPSTracking implements Serializable{

    private String trackingId;
    private String estimatedDelivery;
    private String  eventStatusCode;
    private String  eventStatusDescription;
    private String  eventLocationCity;
    private String  eventDateTime;
    private String  shippedTs;
    private String  actualDeliveredTs;
    private String  originalScanTs;
    private String  outForDeliveryTs;
    private int  isActive;
}
