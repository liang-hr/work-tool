package com.liang3hua.springboot.web.base.http.api;

import com.github.lianjiatech.retrofit.spring.boot.annotation.OkHttpClientBuilder;
import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import com.github.lianjiatech.retrofit.spring.boot.interceptor.LogStrategy;
import com.liang3hua.springboot.web.base.http.UnsafeOkHttpClient;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;

/**
 * @author lianghuarong
 * @date 2020/10/29  10:00
 */
@RetrofitClient(baseUrl = "https://www.talentread.com/",logStrategy = LogStrategy.BODY)
public interface TestHttpsApi {
    @OkHttpClientBuilder
    static OkHttpClient.Builder okhttpClientBuilder() {
        return UnsafeOkHttpClient.getUnsafeOkHttpClientBuilder();
    }

    @GET("/")
    Response<ResponseBody> test();
}
