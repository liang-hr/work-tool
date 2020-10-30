package com.liang3hua.springboot.web.base.http.api;

import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * 动态替换baseURL
 * @author lianghuarong
 * @date 2020/10/29  17:38
 */
@RetrofitClient(baseUrl = "http://www.baidu.com/")
public interface TestOverideBaseUrlApi {

    @GET
    Response<ResponseBody> test(@Url String url);
}
