package com.ecom.mapper.mysql;

import com.ecom.pojo.dto.UserPageQueryDTO;
import com.ecom.pojo.entity.User;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("select * from user where username = #{username}")
    User getByUsername (String username) ;

    @Insert("        insert into user ( name,username,password, phone, sex, status,create_time,update_time,create_user,update_user)\n" +
            "        values (\n" +
            "                #{name}, #{username}, #{password},#{phone}, #{sex},#{status} ,#{createTime}, #{updateTime}, #{createUser}, #{updateUser}\n" +
            "               )")
    void insert(User user);

    @Update("update user set status = #{status} where id = #{id};")
    void enable(Integer status, int id);

    @Select("select * from user where id= #{id}")
    User getByid(int id);


    void update(User user);

    Page<User> pageQuery(UserPageQueryDTO userPageQueryDTO);
}
