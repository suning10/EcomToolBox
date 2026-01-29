package com.ecom.pojo.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReturnTracking {
    private String poId;
    private String returnTrackingId;
    private String statusDetail;
    private String statusDetailDescription;
    private String statusDetailTs;

}
