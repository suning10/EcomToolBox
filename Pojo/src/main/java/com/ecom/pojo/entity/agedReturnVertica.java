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
public class agedReturnVertica implements Serializable {
    private String rdo;
    private String sku;
    private String quantity;
    private String refundStatus;
    private String refundChannel;
    private String returnTrackingId;
    private String returnStatus;
    private String returnShippedTs;
    private String returnDeliveredTs;
    private String labelGeneratedTs;
    private String initiatedTs;
    private String soLineId;
    private String soId;
    private String statusDetail;
    private String statusDetailDescription;
    private String statusDetailTs;
    private String scanStatus;
}
