package com.rminfo.service;

import com.rminfo.model.Users;
import com.rminfo.model.shiro.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Users: SeaRan
 * Date: 2019/6/3
 * Time: 16:47
 * 项目名：springboot
 * 描述：TODO
 * Description: No Description
 */

public interface UserService {
    List<Users> getAll();
    int countUser();
    Users getUser(Users user);


    //jap
    UserInfo findByUserName(String userName);
    Page<UserInfo> findAllByUserNameContains(String userName, Pageable pageable);
}
