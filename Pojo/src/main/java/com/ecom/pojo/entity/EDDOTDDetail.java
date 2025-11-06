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
public class EDDOTDDetail implements Serializable {

    private String poId;
    private String doId;
    private String financePlanId;
    private String shippedSku;
    private String shippingAmt;
    private String physicalFulfillerId ;
    private String tracking;
    private String orderDate;
    private String EDD;
    private String deliveredTs;
    private String shipDateTs;
    private String promisedShipDt;
    private String fulfillmentCarrierId;
    private String shipmentStatus;
}
