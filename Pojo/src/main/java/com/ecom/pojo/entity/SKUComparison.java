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
public class SKUComparison implements Serializable{

    private String reference;
    private String item;
    private String transactionType;
    private String transactionDate;
    private String po;
    private String inventoryStatus;
    private String mvt;
    private String postDate;
    private String postTime;
    private String entryDate;
    private String synapseQty;
    private String nerpQty;
    private String valType;

}
