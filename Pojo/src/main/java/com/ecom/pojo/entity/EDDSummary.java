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
public class EDDSummary implements Serializable {
    private String edd;
    private String cntMiss;
    private String cntTotal;
    private String otd;

}
