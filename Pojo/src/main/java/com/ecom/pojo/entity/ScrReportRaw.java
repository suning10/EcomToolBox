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
public class ScrReportRaw implements Serializable{

    private String material;
    private String description;
    private String qiNerp;
    private String qi3pl;
    private String qiGap;
    private String unsNerp;
    private String uns3pl;
    private String unsGap;
    private String blkNerp;
    private String blk3pl;
    private String blkGap;
    private String totalNerp;
    private String total3pl;
    private String totalGap;
    private String sloc;
    private String valType;
    private String absoluteGapValue;
    private String date;
}
