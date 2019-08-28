package com.rminfo.filter;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created with IntelliJ IDEA.
 * Users: SeaRan
 * Date: 2019/6/19
 * Time: 16:47
 * 项目名：springboot
 * 描述：TODO
 * Description: No Description
 */
@Configuration
@MapperScan(basePackages = "com.rminfo.mapper.shiro", sqlSessionTemplateRef  = "shiroSqlSessionTemplate")
@EnableTransactionManagement
public class DataSourceshiroConfig {

    @Bean(name = "shiroDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.shirodb")
    @Primary
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "shiroSqlSessionFactory")
    @Primary //此处必须在主数据库的数据源配置上加上@Primary
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("shiroDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //加载mybatis全局配置文件
        bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis/mybatis-config.xml"));
        //加载所有的mapper.xml映射文件
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/**/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "shiroTransactionManager")
    @Primary
    public DataSourceTransactionManager testTransactionManager(@Qualifier("shiroDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "shiroSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("shiroSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
