package com.rminfo.controller;

import com.rminfo.dao.UserRepository;
import com.rminfo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * Users: SeaRan
 * Date: 2019/6/3
 * Time: 16:27
 * 项目名：springboot
 * 描述：TODO
 * Description: No Description
 */
@RestController
@RequestMapping(value = "userbinding")
public class UserBindingController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "bangding",method = {RequestMethod.POST})
    public ModelAndView bangding(String UserId,String username,String password){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("msg");

        //判断用户是否存在
        /*Users u = userRepository.findByNickName(username);
        if(u==null){//用户不存在
            System.out.println("qingyong用户不存在");
        }else {
            u.setUserName(UserId);
            System.out.println(userRepository.save(u));;
        }*/


        return mv;
    }


    @RequestMapping("/getuser")
    @Cacheable(value = "user_keys")
    public Users getUser(){
        Users user=new Users("aa@126.com", "aa", "aa123456", "aa","123");
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
        return user;
    }


}
