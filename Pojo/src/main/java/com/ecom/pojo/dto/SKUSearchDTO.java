package com.ecom.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SKUSearchDTO implements Serializable {

    private String sku;
    private String start;
    private String end;
    private String sloc;
}
