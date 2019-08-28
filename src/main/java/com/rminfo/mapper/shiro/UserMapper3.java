package com.rminfo.mapper.shiro;


import com.rminfo.mapper.MyMapper;
import com.rminfo.model.shiro1.User;
import com.rminfo.model.shiro1.UserWithRole;

import java.util.List;

/**
 * @author tycoding
 * @date 2019-01-19
 */
public interface UserMapper3 extends MyMapper<User> {

    List<User> queryList(User user);

    List<UserWithRole> findById(Long id);
}
