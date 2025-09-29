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
public class LocationSynapse implements Serializable {

    private String location;
    private String item;
    private String inv_status;
    private int quantity;
    //private String descripiton;
}
