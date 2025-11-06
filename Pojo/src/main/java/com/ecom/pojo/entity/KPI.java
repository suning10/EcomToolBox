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
public class KPI implements Serializable {

    private String focusArea;
    private String department;
    private String kpiName;
    private String kpiGoal;
    private String date;
    private String callout;

}
