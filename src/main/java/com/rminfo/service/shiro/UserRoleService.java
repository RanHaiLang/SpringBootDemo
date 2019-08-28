package com.rminfo.service.shiro;


import com.rminfo.model.shiro1.UserRole;

import java.util.List;

/**
 * @author tycoding
 * @date 2019-02-03
 */
public interface UserRoleService extends BaseService<UserRole> {

    void deleteUserRolesByUserId(List<Long> keys);

    void deleteUserRolesByRoleId(List<Long> keys);
}
