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
public class LocationSynapseVO implements Serializable {

    private String location;
    private String item;
    private String inv_status;
    private int quantity;
    //private String descripiton;
    private String category;
}
