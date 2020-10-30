package com.liang3hua.springboot.web.base.common.log;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * @author lianghuarong
 * @date 2020/10/27  11:31
 */
@Slf4j
public class LogUtil {

    /**
     * 修改logback日志级别
     * @param rootLevel root,全局级别:ALL,TRACE,DEBUG,INFO,WARN,ERROR,OFF
     * @param singleLevel 单独设置类日志级别:ALL,TRACE,DEBUG,INFO,WARN,ERROR,OFF
     * @param singlePath 单独类路径:com.chinasofti.cloudeasy.api.web.LogController
     * @return
     */
    public static String changeLogbackLevel(String rootLevel, String singleLevel, String singlePath) {

        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        log.warn("set log rootLevel:{},singleLevel:{},singlePath:{}", rootLevel, singleLevel,
                singlePath);

        if (!StringUtils.isEmpty(rootLevel)) {
            // 设置全局日志级别
            ch.qos.logback.classic.Logger logger = loggerContext.getLogger("root");
            logger.setLevel(Level.toLevel(rootLevel));
        }

        if (!StringUtils.isEmpty(singleLevel)) {
            // 设置某个类日志级别-可以实现定向日志级别调整
            ch.qos.logback.classic.Logger vLogger = loggerContext.getLogger(singlePath);
            if (vLogger != null) {
                vLogger.setLevel(Level.toLevel(singleLevel));
            }
        }
        return "success";
    }
}
