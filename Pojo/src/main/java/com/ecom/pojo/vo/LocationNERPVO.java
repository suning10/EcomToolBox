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
public class LocationNERPVO implements Serializable {

    private String sku;
    private int qty;
    private String category;
}
