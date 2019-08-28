package com.rminfo.service.impl;

import com.rminfo.dao.UserRepository;
import com.rminfo.mapper.db2.UserMapper2;
import com.rminfo.model.Users;
import com.rminfo.model.shiro.UserInfo;
import com.rminfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Users: SeaRan
 * Date: 2019/6/20
 * Time: 15:23
 * 项目名：springboot
 * 描述：TODO
 * Description: No Description
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper2 userMapper2;

    @Resource
    private UserRepository userRepository;

    @Override
    public List<Users> getAll() {
        Map map = new HashMap();
        map.put("","");
        List<Users> userList = userMapper2.getAll(map);
        return userList;
    }

    @Override
    public int countUser() {
        return 0;
    }

    @Override
    public Users getUser(Users user) {
        Map<String,Object> map = new HashMap<>();
        map.put("username", user.getUserName());
        map.put("password",user.getPassWord());
        return userMapper2.getUser(map);
    }

    @Override
    public UserInfo findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public Page<UserInfo> findAllByUserNameContains(String userName, Pageable pageable) {
        return userRepository.findAllByUserNameContains(userName, pageable);
    }
}
