package com.chinasoft.lgh.movies.datacollector;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 */
@SpringBootApplication
@MapperScan(basePackages = "com.chinasoft.lgh.movies.datacollector.mapper",annotationClass = Repository.class)
public class CollectorApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(CollectorApplication.class, args);
    }
    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder application) {
        return application.sources(CollectorApplication.class);
    }
}
