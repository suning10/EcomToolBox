package com.ecom.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.io.Serializable;
import java.util.List;


@Data
@Schema(description = "ParcelTrackDTO")
public class ParcelTrackingDTO implements Serializable{
    private List<String> trackingId;
    private int isActive;
}
