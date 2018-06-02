package com.chinasoft.lgh.movies.datacollector.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author Administrator
 */
@Configuration
public class MyBatisConfig {

    @Resource
    private DataSource dataSource;

    @Resource
    private Environment environment;


    /**
     * 创建sqlSessionFactoryBean 实例
     * 并且设置configtion 如驼峰命名.等等
     * 设置mapper 映射路径
     * 设置datasource数据源
     *
     * @return
     */
    @Bean(name = "sessionFactory")
    public SqlSessionFactoryBean createSqlSessionFactoryBean() throws IOException {

        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setConfigLocation(pathMatchingResourcePatternResolver.getResources(environment.getProperty("mybatis.config-location"))[0]);
        sqlSessionFactoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources(environment.getProperty("mybatis.mapper-locations")));
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }
}
