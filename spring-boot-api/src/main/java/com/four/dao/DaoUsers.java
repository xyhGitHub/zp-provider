package com.four.dao;

import com.four.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2018/5/9.
 */
@Mapper
public interface DaoUsers {
    @Select("select * from t_user")
    List<User> queryUserInfo();



}
