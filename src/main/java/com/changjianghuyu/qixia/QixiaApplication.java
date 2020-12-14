package com.changjianghuyu.qixia;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

//@Configuration
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication(scanBasePackages = {"com.changjianghuyu.qixia.web"})
@MapperScan("com.changjianghuyu.qixia.web.dao")
@CrossOrigin
public class QixiaApplication {
    public static void main(String[] args) {
        SpringApplication.run(QixiaApplication.class, args);
    }
}
