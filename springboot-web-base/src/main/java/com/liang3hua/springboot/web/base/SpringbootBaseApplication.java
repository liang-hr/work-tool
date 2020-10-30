package com.liang3hua.springboot.web.base;

import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("com.liang3hua.springboot.web.base.dao")
@EnableConfigurationProperties
@ServletComponentScan
@RetrofitScan("com.liang3hua.springboot.web.base.http.api")
public class SpringbootBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootBaseApplication.class, args);
    }

}
