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
public class agedReturnNerp implements Serializable {

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
    private String soldTo;


}
