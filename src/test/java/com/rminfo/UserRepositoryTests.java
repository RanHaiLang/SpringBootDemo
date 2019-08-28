package com.rminfo;

import com.rminfo.model.Users;
import com.rminfo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Users: SeaRan
 * Date: 2019/5/30
 * Time: 17:03
 * 项目名：springboot
 * 描述：TODO
 * Description: No Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UserRepositoryTests {

    @Autowired
    private UserService userService;

    @Test
    public void test() throws Exception{
        if("124".contains("5")){
            System.out.println("我进来了");
        }


        List<Users> userList = userService.getAll();
    }
}
