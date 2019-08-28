package com.rminfo.mapper.shiro;


import com.rminfo.mapper.MyMapper;
import com.rminfo.model.shiro1.Menu;

import java.util.List;

/**
 * @author tycoding
 * @date 2019-01-19
 */
public interface MenuMapper extends MyMapper<Menu> {

    List<Menu> findUserMenus(String username);

    List<Menu> findUserPermissions(String username);

    void changeTopNode(List<Long> ids);
}
