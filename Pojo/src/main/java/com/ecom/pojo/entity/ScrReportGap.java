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
public class ScrReportGap implements Serializable{

    private String material;
    private String description;
    private String synapseTotal;
    private String nerpTotal;
    private String totalGap;
    private String absMapValue;
    private String netGap;
    private String netGapMapValue;
    private String sloc;
}
