package com.rminfo.service;

import com.rminfo.model.shiro.LoginResult;

/**
 * Created with IntelliJ IDEA.
 * Users: SeaRan
 * Date: 2019/6/25
 * Time: 11:38
 * 项目名：springboot
 * 描述：TODO
 * Description: No Description
 */
public interface LoginService {
    LoginResult login(String userName, String password);
    void logout();
}
