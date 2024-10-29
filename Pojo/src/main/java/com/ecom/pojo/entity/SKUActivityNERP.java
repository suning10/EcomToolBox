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
public class SKUActivityNERP implements Serializable {

    private String material;
    private String plnt;
    private String sloc;
    private String mvt;
    private String movementTypeText;
    private String specialStock;
    private String reference;
    private String matDoc;
    private String qty;
    private String userName;
    private String docHeader;
    private String valType;
    private String postDate;
    private String postTime;
    private String entryDate;
}
