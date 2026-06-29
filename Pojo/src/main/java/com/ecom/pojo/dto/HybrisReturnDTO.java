package com.ecom.pojo.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HybrisReturnDTO {

    private String poId;
    private String sku;
    private String rdoId;
    private String returnTracking;
    private String returnDeliveredTs ;
    private String returnInitDate;
    private String returnTrackingNumber;
    private String returnShipped;
    private String rmaNumber;
    private String soId;
    private String soLineId;

}
