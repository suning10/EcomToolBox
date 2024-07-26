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
public class PBTracking implements Serializable{

    private String trackingIdLong;
    private String trackingId;
    private String productId;
    private String poId;
    private String fulfillmentDeliveryDoId;
    private String pickedupTs;
    private String expectedDeliveryTs;
    private String fulfillmentCarrierId;
    private String shipmentStatus;
    private String shipDateTs;
    private String deliveredTs;
    private String originalPromiseDeliveryTs;
    private String currentPromiseDeliveryTs;

}
