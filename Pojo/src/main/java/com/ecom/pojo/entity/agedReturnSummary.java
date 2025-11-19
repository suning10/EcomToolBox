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
public class agedReturnSummary implements Serializable {

    private String flagRefusal;
    private String agedBucket;
    private String scanStatus;
}
