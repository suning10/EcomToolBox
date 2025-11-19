package com.ecom.pojo.vo;


import com.ecom.pojo.entity.agedReturnSummary;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class agedReturnSummaryVO implements Serializable {

    private agedReturnSummary agedReturnSummaryObj;
    private int count;

}
