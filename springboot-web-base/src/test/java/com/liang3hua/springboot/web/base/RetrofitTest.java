package com.liang3hua.springboot.web.base;

import cn.hutool.core.util.StrUtil;
import com.liang3hua.springboot.web.base.http.api.TestHttpsApi;
import com.liang3hua.springboot.web.base.http.api.TestOverideBaseUrlApi;
import okhttp3.ResponseBody;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import retrofit2.Response;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author lianghuarong
 * @date 2020/10/29  17:57
 */
@SpringBootTest(classes = SpringbootBaseApplication.class) // 使用main中的启动类
@RunWith(SpringRunner.class)
public class RetrofitTest {
    @Resource
    private TestHttpsApi testHttpsApi;
    @Resource
    private TestOverideBaseUrlApi testOverideBaseUrlApi;

    @Test // 使用org.junit.Test;
    public void testHttps() throws IOException {
        Response<ResponseBody> response = testHttpsApi.test();
        System.out.println(response.body().string());
    }

    @Test
    public void testOverideBaseUrl() throws IOException {
        String url = "http://cn.bing.com/";
        System.out.println(StrUtil.format("@Url:{}", url));
        testOverideBaseUrlApi.test(url);

        url = "/";
        System.out.println(StrUtil.format("@Url:{}", url));
        testOverideBaseUrlApi.test(url);
    }
}
