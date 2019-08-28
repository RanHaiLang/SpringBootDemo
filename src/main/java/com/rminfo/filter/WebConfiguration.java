package com.rminfo.filter;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created with IntelliJ IDEA.
 * Users: SeaRan
 * Date: 2019/5/30
 * Time: 15:33
 * 项目名：springboot
 * 描述：TODO
 * Description: No Description
 */
//@Configuration
public class WebConfiguration implements WebMvcConfigurer {


    //注册拦截器
    /*@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")//拦截的路径
                .excludePathPatterns("/AppLogin/**")//不拦截
                .excludePathPatterns("/home/**")
                .excludePathPatterns("/static/**")
                .excludePathPatterns("/user");
    }*/
}
