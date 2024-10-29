package com.ecom.pojo.vo;


import com.ecom.pojo.entity.SKUSummaryByMvmType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SKUSummaryByMvmTypeVO implements Serializable {

    private List<SKUSummaryByMvmType> data;
    private int total;
}
