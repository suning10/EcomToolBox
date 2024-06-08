package com.ecom.controller;

import com.ecom.pojo.dto.UserLoginDTO;
import com.ecom.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/user")
@Tag(name = "User")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @Operation(summary = "login")
    @PostMapping("/login")
    public void login(@RequestBody UserLoginDTO userLoginDTO){
        log.info("Start Login");
        log.info("{} calling login ",userLoginDTO);
        userService.login();

    }
}
