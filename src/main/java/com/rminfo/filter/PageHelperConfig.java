package com.rminfo.filter;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * Users: SeaRan
 * Date: 2019/6/17
 * Time: 10:32
 * 项目名：springboot
 * 描述：配置mybatis的分页插件pageHelper
 * Description: No Description
 */
@Configuration//将该类加到spring容器里
public class PageHelperConfig {


    @Bean//加上该注解spring容器自动配置
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        properties.setProperty("dialect", "mysql");//配置mysql数据库的方言
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
