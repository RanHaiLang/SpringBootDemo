package com.rminfo.service.shiro;


import com.rminfo.model.shiro1.LoginLog;

import java.util.List;

/**
 * @author tycoding
 * @date 2019-03-13
 */
public interface LoginLogService extends BaseService<LoginLog> {

    List<LoginLog> findByPage(LoginLog log);

    void delete(List<Long> ids);

    void saveLog(LoginLog log);
}
