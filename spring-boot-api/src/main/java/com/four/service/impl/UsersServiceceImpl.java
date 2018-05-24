/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserServiceceImpl
 * Author:   Administrator
 * Date:     2018/5/9 20:51
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.four.service.impl;

import com.four.dao.DaoUsers;
import com.four.model.User;
import com.four.service.UsersService;
import com.four.service.UsersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2018/5/9
 * @since 1.0.0
 */
@Service("usersService")
public class UsersServiceceImpl implements UsersService {

    @Resource
    private DaoUsers daouser;


    @Override
    public List<User> queryUserInfo() {


        return daouser.queryUserInfo();
    }



}
