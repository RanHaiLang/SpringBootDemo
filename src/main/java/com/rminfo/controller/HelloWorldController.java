package com.rminfo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rminfo.model.Users;
import com.rminfo.util.JwtUtil;
import com.rminfo.dao.UserRepository;
import com.rminfo.mapper.db1.UserMapper;
import com.rminfo.service.UserService;
import com.rminfo.util.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Users: SeaRan
 * Date: 2019/5/30
 * Time: 15:04
 * 项目名：springboot
 * 描述：TODO
 * Description: No Description
 */
@RestController
@RequestMapping("AppLogin")
public class HelloWorldController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }

    @RequestMapping("/user")
    public ModelAndView getuser() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("userinfo.html");


        return mv;
    }

    @RequestMapping("/layout")
    public ModelAndView layout() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("layout.html");


        return mv;
    }
    @RequestMapping("/public/{url}")
    public ModelAndView publicHtml(@PathVariable String url){
        ModelAndView mv = new ModelAndView();
        mv.setViewName(url+".html");
        Users u = userMapper.getUser();
        return mv;
    }

    @RequestMapping(value = "userbypage",method = RequestMethod.GET)
    public PageInfo<Users> findItemByPage(@RequestParam(required = false, defaultValue = "1") int pageIndex,
                                          @RequestParam(required = false, defaultValue = "10") int pageSize) {

        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(pageIndex, pageSize);
        List<Users> userList = userService.getAll();
        Users u = userService.getUser(new Users("RHL","aa111"));
        PageInfo pageInfo = new PageInfo(userList);
        return pageInfo;
    }


    //处理登录
    @RequestMapping(value="login", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseData login(HttpServletRequest request, @RequestParam( "username") String username,
                     @RequestParam("password") String password) {
        ResponseData responseData = null;
        try {
            Users user = new Users();
            user.setUserName(username);
            user.setPassWord(password);

            responseData = ResponseData.ok();
            //先到数据库验证
            Users u = userService.getUser(user);
            if(null != u) {
                //给用户jwt加密生成token
                String token = JwtUtil.sign(u, 60L* 1000L* 30L);
                ///封装成对象返回给客户端
                responseData.putDataValue("loginId", u.getId());
                responseData.putDataValue("token", token);
                responseData.putDataValue("user", user);
            }
            else{
                responseData =  ResponseData.customerError();
            }
        } catch (Exception e) {
            responseData = ResponseData.serverInternalError();
        }
        return responseData;
    }
}
