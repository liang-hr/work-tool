package com.liang3hua.springboot.web.base.common.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

/**
 * @author lhr
 * @date 2020-09-23  20:33
 */
public class IdKit {
    private static long workerId = 1;
    private static long datacenterId = 1;

    public static long nextId(){
        // TODO workerId、datacenterId分配
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        return snowflake.nextId();
    }
}
