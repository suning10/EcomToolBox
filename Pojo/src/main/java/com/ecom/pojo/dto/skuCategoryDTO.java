package com.ecom.pojo.dto;

import java.io.Serializable;

import lombok.Data;
@Data
public class skuCategoryDTO implements Serializable {
    private String sku;
    private String category;
    private String description;
}
