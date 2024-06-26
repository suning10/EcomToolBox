package com.ecom.service.impl;

import com.ecom.common.result.Result;
import com.ecom.mapper.mysql.UserMapper;
import com.ecom.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j

public class UserServiceImpl implements UserService {

    @Autowired
   private UserMapper userMapper;
    @Override
    public Result login() {

        return Result.success();
    }
}
