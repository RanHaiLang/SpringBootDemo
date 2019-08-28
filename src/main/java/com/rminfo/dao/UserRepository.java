package com.rminfo.dao;

import com.rminfo.model.shiro.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * Users: SeaRan
 * Date: 2019/5/30
 * Time: 16:55
 * 项目名：springboot
 * 描述：TODO
 * Description: No Description
 */
@Repository
public interface UserRepository extends JpaRepository<UserInfo,Integer> {

    UserInfo findByUserName(String userName);
    Page<UserInfo> findAllByUserNameContains(String userName, Pageable pageable);

}
