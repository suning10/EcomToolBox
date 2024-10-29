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
public class SKUSummaryByMvmTypeSynapse implements Serializable{

    private String item;
    private String movement;
    private String source;
    private String qty;
    private String date;

}

