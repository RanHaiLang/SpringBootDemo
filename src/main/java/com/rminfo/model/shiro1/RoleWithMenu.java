package com.rminfo.model.shiro1;

import lombok.Data;

import java.util.List;

/**
 * 为什么创建这个类？
 * 请看 {@link //cn.tycoding.system.entity.UserWithRole} 中的解释
 *
 * @author tycoding
 * @date 2019-02-05
 */
@Data
public class RoleWithMenu extends Role {

    private Long menuId;

    private List<Long> menuIds;

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public List<Long> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<Long> menuIds) {
        this.menuIds = menuIds;
    }
}
