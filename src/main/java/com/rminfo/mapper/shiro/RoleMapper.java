package com.rminfo.mapper.shiro;


import com.rminfo.mapper.MyMapper;
import com.rminfo.model.shiro1.Role;
import com.rminfo.model.shiro1.RoleWithMenu;

import java.util.List;

/**
 * @author tycoding
 * @date 2019-01-19
 */
public interface RoleMapper extends MyMapper<Role> {

    List<Role> findUserRole(String username);

    List<RoleWithMenu> findById(Long id);
}
