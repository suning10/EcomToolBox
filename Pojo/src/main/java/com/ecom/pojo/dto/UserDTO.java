package com.ecom.pojo.dto;

import lombok.Data;
import java.io.Serializable;
@Data
public class UserDTO implements Serializable{

    private int id;

    private String username;

    private String firstname;

    private String lastname;

    private String password;
}
