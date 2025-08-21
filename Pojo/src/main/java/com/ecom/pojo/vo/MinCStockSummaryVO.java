package com.ecom.pojo.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.IntSummaryStatistics;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MinCStockSummaryVO implements Serializable {

    private String category;
    private int minQty;

}
