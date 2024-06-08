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
public class Return implements Serializable {
    private String poLookupKey;
    private String rdoId;
    private String returnTrackingId;
    private String trackingId;
    private String sku;
    private String rmaNumber;
    private String soId;
    private String poId;
    private String lineItemDetailedStatus;
    private String rsoId;
    private String channel;
    private String currentRefundStatus;
    private String labelGeneratedTs;
    private String refundDt;

}
