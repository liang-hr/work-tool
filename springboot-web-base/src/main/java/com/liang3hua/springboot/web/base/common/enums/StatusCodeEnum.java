package com.liang3hua.springboot.web.base.common.enums;

/**
 * 请求状态码枚举类
 *
 * @author wumin1
 * @since 2020-02-25 12:16:16
 */
public enum StatusCodeEnum {

	SUCCESS(200, "请求成功"),
	SYSTEM_ERROR(5001, "系统错误");

	private Integer code;
	private String msg;

	StatusCodeEnum(Integer code, String msg) {
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
