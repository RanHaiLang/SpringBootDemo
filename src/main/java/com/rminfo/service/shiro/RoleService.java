package com.rminfo.service.shiro;


import com.rminfo.model.shiro1.Role;
import com.rminfo.model.shiro1.RoleWithMenu;

import java.util.List;

/**
 * @author tycoding
 * @date 2019-01-19
 */
public interface RoleService extends BaseService<Role> {

    List<Role> findUserRole(String username);

    List<Role> findAllRole(Role role);

    List<Role> queryList(Role role);

    RoleWithMenu findById(Long id);

    void add(RoleWithMenu role);

    boolean checkName(String name, String id);

    void update(RoleWithMenu role);

    void delete(List<Long> keys);
}
