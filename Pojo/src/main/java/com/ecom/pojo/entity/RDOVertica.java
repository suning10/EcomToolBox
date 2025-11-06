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
public class RDOVertica implements Serializable {

    private String poId;
    private String soId;
    private String odo;
    private String rdo;
    private String sku;
    private String quantity;
    private String refundStatus;
    private String returnChannel;
    private String returnReason;
    private String returnTrackingId;
    private String returnStatus;
    private String returnShippedTs;
    private String labelGeneratedTs;
    private String initiatedTs;
    private String returnSubReasonCode;
    private String returnAddressId;
    private String soLineId;
    private String outboundSoId;
}
