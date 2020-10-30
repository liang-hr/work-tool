package com.liang3hua.springboot.web.base.common.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liang3hua.springboot.web.base.common.annotation.ApiResult;
import com.liang3hua.springboot.web.base.common.result.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@Component
public class ApiResultReturnHandler implements HandlerMethodReturnValueHandler {
    @Resource
    private ObjectMapper objectMapper;

    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        return methodParameter.getMethodAnnotation(ApiResult.class) != null
                || methodParameter.getDeclaringClass().getAnnotation(ApiResult.class) != null;
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter methodParameter,
            ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {

    	// 标识请求是否已经在该方法内完成处理
        modelAndViewContainer.setRequestHandled(true);

        Object baseObj;
        if(returnValue == null) {
            baseObj = BaseResult.success(null);

        }else if (returnValue instanceof BaseResult){
            // 已经是统一返回格式，不做处理
            baseObj = returnValue;

        }else{
            baseObj = BaseResult.success(returnValue);
        }

        HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.print(objectMapper.writeValueAsString(baseObj));
            writer.flush();
        } catch (IOException e) {
        	log.error(e.getMessage(), e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

}
