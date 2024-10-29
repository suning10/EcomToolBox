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
public class ScrReportSummary implements Serializable {

    private String sloc;
    private String absoluteGapCost;
    private String netGapCost;
    private String absoluteGap;
    private String netGap;
    private String skuWithError;
    private String skuWithErrorNumber;
    private String nerpOverage;
    private String synapseOverage;

}
