package com.four.service;

import com.four.model.User;

import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * Created by 34210 on 2018/5/15.
 */
public interface UserService {

    public List<User> userList(User user);
}
