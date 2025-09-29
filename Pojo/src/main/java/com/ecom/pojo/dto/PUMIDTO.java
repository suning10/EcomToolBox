package com.ecom.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
@Schema(description = "PUMIDTO")
public class PUMIDTO implements Serializable{
    private String sku;
    private String pumiQty;
    private String pumiDate;

}
