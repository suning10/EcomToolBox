package com.ecom.service.impl;

import com.ecom.common.result.Result;
import com.ecom.mapper.UserMapper;
import com.ecom.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
