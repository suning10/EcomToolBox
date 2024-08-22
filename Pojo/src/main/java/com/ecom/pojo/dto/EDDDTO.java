package com.ecom.pojo.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.io.Serializable;
import java.util.List;


@Data
@Schema(description = "EDD")
public class EDDDTO implements Serializable{
    private List<String> idList;
    private String searchFlag;
}
