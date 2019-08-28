package com.rminfo.service.shiro;


import com.rminfo.model.shiro1.Menu;
import com.rminfo.model.shiro1.Tree;
import com.rminfo.model.shiro1.User;
import com.rminfo.model.shiro1.UserWithRole;

import java.util.List;

/**
 * @author tycoding
 * @date 2019-01-19
 */
public interface UserService extends BaseService<User> {

    User findByName(String username);

    UserWithRole findById(Long id);

    List<Tree<Menu>> getMenus(String username);

    List<User> queryList(User user);

    void add(UserWithRole user);

    boolean checkName(String name, String id);

    void update(UserWithRole user);

    void delete(List<Long> keys);

    void updatePassword(String password);
}
