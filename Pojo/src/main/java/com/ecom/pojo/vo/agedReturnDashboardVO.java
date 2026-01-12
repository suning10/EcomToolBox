package com.ecom.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class agedReturnDashboardVO implements Serializable {
    private String bol;
    private String po;
    private String DO;
    private String material;
    private String qty;
    private String rdd;
    private String plannedGi;
    private String sloc;
    private String documentDate;
    private String deliveryQty;
    private String referenceDoc;
    private String createdby;
    private int agedDays;
    private String agedBucket;
    private String flagRefusal;
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
    private String soldTo;
    private String rma;
}
