package com.liang3hua.springboot.web.base.common.util;

/**
 * 子线程可以共享的InheritableThreadLocal，用于存储日志跟踪id
 * @author lhr
 * @date 2020-09-25  16:26
 */
public class InheritableTraceContextHolder {
    private static final InheritableThreadLocal<String> inheritable_trace_info = new InheritableThreadLocal<String>(){
        @Override
        protected String initialValue(){
            return String.valueOf(IdKit.nextId());
        }
    };

    public static void init(){
        inheritable_trace_info.set(String.valueOf(IdKit.nextId()));
    }

    public static String getTrace(){
        return  inheritable_trace_info.get();
    }

    public static void  clear(){
        inheritable_trace_info.remove();
    }

}
