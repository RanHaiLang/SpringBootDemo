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

import javax.sql.DataSource;

/**
 * Created with IntelliJ IDEA.
 * Users: SeaRan
 * Date: 2019/6/11
 * Time: 16:19
 * 项目名：springboot
 * 描述：数据源配置
 * Description: No Description
 */
@Configuration
public class DataSourceConfig {
    /*@Bean(name = "paDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    @Primary
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "casDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    public DataSource testDataSource2() {
        return DataSourceBuilder.create().build();
    }*/

}
