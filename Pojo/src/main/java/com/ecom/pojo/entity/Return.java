package com.ecom.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Return {

    private String returnTracking;
    private String outboundTracking;
    private String rdoId;
    private String sku;
    private String rmaNumber;
    private String soId;
    private String poId;
    private String fedexReturnException;
    private String fedexDeliveryException;
    private String lineItemStatus;
    private String rsoId;

}
