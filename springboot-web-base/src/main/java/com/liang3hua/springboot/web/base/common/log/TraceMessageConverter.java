package com.liang3hua.springboot.web.base.common.log;


import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.liang3hua.springboot.web.base.common.util.InheritableTraceContextHolder;

/**
 * 日志信息转换器（日志跟踪id-traceId）
 * @author lhr
 * @date 2020-09-24  20:16
 */
public class TraceMessageConverter extends ClassicConverter {
    @Override
    public String convert(ILoggingEvent event) {
        return InheritableTraceContextHolder.getTrace();
    }
}
