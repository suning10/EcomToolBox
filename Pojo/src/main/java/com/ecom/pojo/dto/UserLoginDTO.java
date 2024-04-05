package com.ecom.pojo.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "login DTO")
public class UserLoginDTO {


    @Schema(description = "username")
    private String username;

    @Schema(description = "password")
    private String password;


}
