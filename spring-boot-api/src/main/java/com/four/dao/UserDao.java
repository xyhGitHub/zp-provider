package com.four.dao;

import com.four.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * Created by 34210 on 2018/5/15.
 */
@Mapper
public interface UserDao {

    @Select("select * from u_user where nickname=#{user.nickname} and pswd=#{user.pswd}")
    public List<User> userList(@Param("user") User user);
}
