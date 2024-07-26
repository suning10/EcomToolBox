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
public class FedExTracking {
    private String trackingId;
    private String statusdetailTs;
    private String statusdetailDescription;
    private String serviceType;
    private String createdTs;
    private String eventTs;
    private String eventsDescription;
    private String statusCity;
    private String shipmentDelayCity;
    private String shipmentException ;
    private String exceptionDescription;
    private String returnTrackingNumberNew;
    private int isActive;


}
