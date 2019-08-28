package com.rminfo.service.shiro;


import com.rminfo.model.shiro1.RoleMenu;

import java.util.List;

/**
 * @author tycoding
 * @date 2019-02-05
 */
public interface RoleMenuService extends BaseService<RoleMenu> {

    void deleteRoleMenusByRoleId(List<Long> keys);

    void deleteRoleMenusByMenuId(List<Long> ids);
}
