package com.ecom.service.impl;

import com.ecom.common.constant.MessageConstant;
import com.ecom.common.constant.StatusConstant;
import com.ecom.common.context.BaseContext;
import com.ecom.common.exception.AccountLockedException;
import com.ecom.common.exception.AccountNotFoundException;
import com.ecom.common.exception.PasswordErrorException;
import com.ecom.common.result.PageResult;
import com.ecom.common.result.Result;
import com.ecom.mapper.mysql.UserMapper;
import com.ecom.pojo.dto.UserDTO;
import com.ecom.pojo.dto.UserLoginDTO;
import com.ecom.pojo.dto.UserPageQueryDTO;
import com.ecom.pojo.entity.User;
import com.ecom.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j

public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public PageResult pageQuery(UserPageQueryDTO userPageQueryDTO) {
        PageHelper.startPage(userPageQueryDTO.getPage(),userPageQueryDTO.getPageSize());
        Page<User> page = userMapper.pageQuery(userPageQueryDTO);
        //can't get total from one query
        long total = page.getTotal();
        List<User> records = page.getResult();

        return new PageResult(total,records);
    }

    @Override
    public User login(UserLoginDTO userLoginDTO) {
        String username = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();


        User user = userMapper.getByUsername(username);

        //2、error handle
        if (user == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);}
            // use md5 to encrypt password
            //password = DigestUtils.md5DigestAsHex(password.getBytes());
            if (!password.equals(user.getPassword())) {
                throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
            }

            if (user.getStatus() == StatusConstant.DISABLE) {
                throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
            }

        return user;
    }

    @Override
    public void save(UserDTO userDTO) {

        User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        user.setStatus(1);
        user.setCreateUser(BaseContext.getCurrentId());
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateUser(BaseContext.getCurrentId());
        user.setUpdateTime(LocalDateTime.now());

        userMapper.insert(user);
    }

    @Override
    public void startOrStop(Integer status, int id) {
        userMapper.enable(status,id);
    }

    @Override
    public User getById(int id) {
        User user = userMapper.getByid(id);
        return user;
    }

    @Override
    public void update(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        user.setUpdateTime(LocalDateTime.now());
        user.setUpdateUser(BaseContext.getCurrentId());
        userMapper.update(user);
    }


}
