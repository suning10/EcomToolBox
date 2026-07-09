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
public class MinCStock implements Serializable {

    private String item;
    private String minQty;
    private String category;
    private String description;
    private String creationDate;

    public void setMinQty(String minQty){
        this.minQty =  minQty != null ? minQty.trim() : null;
    }

    public void setDescription(String description){
        this.description =  description != null ? description.trim() : null;
    }
}
