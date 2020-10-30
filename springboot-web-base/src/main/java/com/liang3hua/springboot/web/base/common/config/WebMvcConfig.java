package com.liang3hua.springboot.web.base.common.config;


import com.liang3hua.springboot.web.base.common.handler.ApiResultReturnHandler;
import com.liang3hua.springboot.web.base.common.interceptor.GlobalHandlerInterceptor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer, InitializingBean {
	
	@Resource
	RequestMappingHandlerAdapter requestMappingHandlerAdapter;
	
	@Resource
    private ApiResultReturnHandler apiResultReturnHandler;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 链路追踪
		registry.addInterceptor(new GlobalHandlerInterceptor());
	}

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		// URL不区分大小写
		AntPathMatcher pathMatcher = new AntPathMatcher();
		pathMatcher.setCaseSensitive(false);
		configurer.setPathMatcher(pathMatcher);
	}

	@Override
    public void afterPropertiesSet() {
        List<HandlerMethodReturnValueHandler> returnValueHandlers = requestMappingHandlerAdapter
                .getReturnValueHandlers();
        List<HandlerMethodReturnValueHandler> list = new ArrayList<>();
		//自定义returnHandler
		list.add(apiResultReturnHandler);
		list.addAll(returnValueHandlers);
        requestMappingHandlerAdapter.setReturnValueHandlers(list);
    }

}