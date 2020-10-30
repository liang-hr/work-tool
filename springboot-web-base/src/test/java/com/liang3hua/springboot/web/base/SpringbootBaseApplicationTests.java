package com.liang3hua.springboot.web.base;

import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitScan;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RetrofitScan("com.liang3hua.springboot.web.base.http.api")
class SpringbootBaseApplicationTests {

    @Test
    void contextLoads() {
    }

}
