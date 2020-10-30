package com.liang3hua.springboot.web.base.common.exception;


import cn.hutool.core.text.StrFormatter;
import com.liang3hua.springboot.web.base.common.enums.OpCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BizException extends RuntimeException {  
      
	private static final long serialVersionUID = 1L;
	//异常信息  
    private String message;  
    //状态码
    private Integer code;

    public BizException(Integer code, String message){
        this.code = code;
        this.message=message;
    }

    public BizException(String message){
        this(OpCodeEnum.SYSTEM_ERROR.getCode(), message);
    }

    public BizException(OpCodeEnum opCodeEnum){
        this(opCodeEnum.getCode(), opCodeEnum.getMsg());
    }

    /**
     * 业务异常构造器，对返回枚举值的信息部分作补充说明
     * @param opCodeEnum 返回枚举值
     * @param detail 对返回枚举的信息部分作补充，message = opCodeEnum.getMsg() + “，” + detail
     */
    public BizException(OpCodeEnum opCodeEnum, String detail){
        this(opCodeEnum.getCode(), StrFormatter.format("{}，{}", opCodeEnum.getMsg(), detail));
    }
}