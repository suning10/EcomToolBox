package com.ecom.controller;

import com.ecom.common.constant.JwtClaimsConstant;
import com.ecom.common.properties.JwtProperties;
import com.ecom.common.result.PageResult;
import com.ecom.common.result.Result;
import com.ecom.common.utils.JwtUtil;
import com.ecom.pojo.dto.UserDTO;
import com.ecom.pojo.dto.UserLoginDTO;
import com.ecom.pojo.dto.UserPageQueryDTO;
import com.ecom.pojo.entity.User;
import com.ecom.pojo.vo.UserVO;
import com.ecom.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/user/")
@Tag(name = "User")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties;
    @Operation(summary = "login")
    @PostMapping("/login")
    public Result<UserVO> login(@RequestBody UserLoginDTO userLoginDTO){
        log.info("Start Login");
        log.info("{} calling login ",userLoginDTO);
        User user = userService.login(userLoginDTO);

        //create jwt token
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID,user.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getUserTtl(),
                claims);

        UserVO userVO = UserVO.builder()
                        .id(user.getId())
                        .userName(user.getUsername())
                        .name(user.getName())
                        .token(token)
                        .build();

        return Result.success(userVO);

    }

    @PostMapping
    @Operation(summary = "add new user")
    public Result save(@RequestBody UserDTO userDTO){

        // call User service
        userService.save(userDTO);

        return Result.success();
    }
    @GetMapping("/page")
    public Result<PageResult> page(UserPageQueryDTO userPageQueryDTO){

        PageResult pageResult= userService.pageQuery(userPageQueryDTO);
        return Result.success(pageResult);
    }


    /**
     * 启用禁用员工账号
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    public Result startOrStop(@PathVariable Integer status, int id){

        userService.startOrStop(status,id);
        return Result.success();
    }


    @GetMapping("/{id}")
    public Result<User> getById(@PathVariable int id){
        User User= userService.getById(id);
        return Result.success(User);
    }

    @PutMapping
    public Result update(@RequestBody UserDTO userDTO){
        
        userService.update(userDTO);
        return Result.success();
    }


}
