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
public class OriginScanUPS implements Serializable {

    private String poId;
    private String doId;
    private String physicalFulfillerId;
    private String tracking;
    private String eDD;
    private String orderEntryDate;
    private String shipScanDate;
    private String originScan;
    private String originScanHour;
    private String outForDeliveryScan;
    private String diff;
    private String deliveredTs;

}
