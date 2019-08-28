package com.rminfo;

import com.rminfo.service.impl.RedisHelperImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created with IntelliJ IDEA.
 * Users: SeaRan
 * Date: 2019/6/5
 * Time: 11:22
 * 项目名：springboot
 * 描述：TODO
 * Description: No Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisHelperImpl redisHelper;


    @Test
    public void test() throws Exception{
//        基本写法
//        stringRedisTemplate.opsForValue().set("aaa","111");
//        Assert.assertEquals("111",stringRedisTemplate.opsForValue().get("aaa"));
//        System.out.println(stringRedisTemplate.opsForValue().get("aaa"));
        /*Users user=new Users();
        user.setUserName("Alex");
        user.setNickName("不会打篮球的程序不是好男人");
        redisHelper.valuePut("aaa",user);
        System.out.println(redisHelper.getValue("aaa"));*/
    }

    @Test
    public void testObj() throws Exception {
        /*Users user=new Users();
        user.setUserName("Jerry");
        user.setNickName("不会打篮球的程序不是好男人!");

        ValueOperations<String, Users> operations=redisTemplate.opsForValue();
        operations.set("502", user);
        Thread.sleep(500);
        boolean exists=redisTemplate.hasKey("502");
        if(exists){
            Users u = (Users) redisTemplate.opsForValue().get("502");
            System.out.println(redisTemplate.opsForValue().get("502")+":"+u.getNickName());
        }else{
            System.out.println("exists is false");
        }*/
        // Assert.assertEquals("aa", operations.get("com.neo.f").getUserName());
    }
}
