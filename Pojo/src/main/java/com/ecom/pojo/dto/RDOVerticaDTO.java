package com.ecom.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
@Schema(description = "RDOVertica")
public class RDOVerticaDTO implements Serializable{
    private List<String> idList;
    /*
    1. rdo
    2. po
    3. RMA
     */
    private String searchFlag;

}
