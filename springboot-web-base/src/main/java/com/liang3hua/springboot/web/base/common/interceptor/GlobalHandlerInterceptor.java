package com.liang3hua.springboot.web.base.common.interceptor;


import com.liang3hua.springboot.web.base.common.util.InheritableTraceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局拦截器
 * @author lhr
 * @date 2020-09-24  20:09
 */
@Slf4j
public class GlobalHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 初始化日志跟踪id
        InheritableTraceContextHolder.init();
        request.setAttribute("_timestamp", System.currentTimeMillis());
        log.info("【START】 uri:[{}].", request.getRequestURI());

        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long begin = (long) request.getAttribute("_timestamp");
        long end = System.currentTimeMillis();
        log.info("【END】   uri:[{}], cost: {}ms. ", request.getRequestURI(), end - begin);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        // 清除日志跟踪id
        InheritableTraceContextHolder.clear();
    }

}
