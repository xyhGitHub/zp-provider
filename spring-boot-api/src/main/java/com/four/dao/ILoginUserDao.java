package com.four.dao;

import com.four.model.LoginUser;

/**
 * Created by Administrator on 2018/5/20.
 */
public interface ILoginUserDao {

    void addLoginUserInfo(LoginUser laGouUser);

    LoginUser laGouRegCheck(String loginName);

    LoginUser laGouLogin(LoginUser laGouUser);
}
