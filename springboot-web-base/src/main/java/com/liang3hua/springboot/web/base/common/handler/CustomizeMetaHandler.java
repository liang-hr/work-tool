package com.liang3hua.springboot.web.base.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.liang3hua.springboot.web.base.common.constant.CommonConstant;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author lhr
 * @date 2020-09-22  15:50
 */
@Component
public class CustomizeMetaHandler implements MetaObjectHandler {
    @Resource
    private HttpServletRequest httpServletRequest;

    @Override
    public void insertFill(MetaObject metaObject) {
        String userId = httpServletRequest.getHeader(CommonConstant.USER_ID);
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);
        this.setFieldValByName("updateBy",userId,metaObject);
        this.setFieldValByName("createBy",userId,metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        String userId = httpServletRequest.getHeader(CommonConstant.USER_ID);
        this.setFieldValByName("updateTime",new Date(),metaObject);
        this.setFieldValByName("updateBy",userId,metaObject);
    }
}
