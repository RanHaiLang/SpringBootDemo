package com.rminfo.mapper.db1;

import com.rminfo.model.Users;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Users: SeaRan
 * Date: 2019/6/12
 * Time: 14:44
 * 项目名：springboot
 * 描述：TODO
 * Description: No Description
 */

public interface UserMapper {
    List<Users> getAll(Map map);
    int countUser();
    Users getUser();
}
