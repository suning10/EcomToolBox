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
public class EDD implements Serializable{

    private String poId;
    private String doId;
    private String shippedSku;
    private String shippingAmt;
    private String shippedQty ;
    private String physicalFulfillerId;
    private String tracking;
    private String EDD;
    private String orderEntryDate;
    private String shipScanDate;
    private String deliveredTs;
    private String fulfillmentCarrierId;
    private String salesPrice;
    private String listPrice;
    private String cancellationStatus;
    private String cancellationReason;
    private String orderTsEst;
    private String lineItemStatus;
    private String orderStatus;
}
