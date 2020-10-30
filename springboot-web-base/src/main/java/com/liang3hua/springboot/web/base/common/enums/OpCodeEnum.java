package com.liang3hua.springboot.web.base.common.enums;

/**
 * 操作码枚举类
 *
 * @author wumin1
 * @since 2020-02-25 12:16:16
 */
public enum OpCodeEnum {
	
	SUCCESS(0, "操作成功"),
	SYSTEM_ERROR(5001, "系统错误"),
	REMOTE_RESPONSE_ERROR(5002, "远程调用结果错误"),
	REMOTE_CALL_ERROR(5003, "远程调用请求错误"),
	REMOTE_CALL_IO_ERROR(5004, "远程调用IO错误");

	private Integer code;
	private String msg;

	OpCodeEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
