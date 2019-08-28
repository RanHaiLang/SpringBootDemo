package com.rminfo.dao;

import com.rminfo.model.shiro.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 * Users: SeaRan
 * Date: 2019/6/11
 * Time: 11:13
 * 项目名：springboot
 * 描述：TODO
 * Description: No Description
 */
public interface UserInfoRepository extends JpaRepository<UserInfo,Long > {


}
