package com.ecom.service;

import com.ecom.common.result.PageResult;
import com.ecom.common.result.Result;
import com.ecom.pojo.dto.UserDTO;
import com.ecom.pojo.dto.UserLoginDTO;
import com.ecom.pojo.dto.UserPageQueryDTO;
import com.ecom.pojo.entity.User;

public interface UserService {
    PageResult pageQuery(UserPageQueryDTO userPageQueryDTO);

    User login(UserLoginDTO userLoginDTO);

    void save(UserDTO userDTO);

    void startOrStop(Integer status, int id);

    User getById(int id);

    void update(UserDTO userDTO);
}
