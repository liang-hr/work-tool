package com.liang3hua.quartzdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties
public class QuartzDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuartzDemoApplication.class, args);
    }

}
