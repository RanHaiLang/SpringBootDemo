package com.rminfo.model.shiro1;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author tycoding
 * @Date 2019-01-19
 */
@Data
@ToString
@Table(name = "tb_user_role")
public class UserRole implements Serializable {

    @Column(name = "user_id")
    private long userId;

    @Column(name = "role_id")
    private long roleId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
}
