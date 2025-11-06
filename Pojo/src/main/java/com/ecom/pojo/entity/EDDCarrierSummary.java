package com.ecom.pojo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EDDCarrierSummary implements Serializable {
    private String edd;
    private String carrier;
    private String serviceLevel;
    private String cntMiss;
    private String cntTotal;
    private String otd;

}
