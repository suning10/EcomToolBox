package com.ecom.pojo.dto;

import lombok.Data;
import java.io.Serializable;
@Data
public class UserDTO implements Serializable{


    private String username;

    private String name;

    private String password;

    private String phone;

    private String sex;
}
