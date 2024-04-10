package com.ecom.pojo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;


@Data
@Schema(description = "searchByDTO")
public class SearchByRDODTO {

    @JsonProperty("rdo")
    private List<String> rdoList;
}
