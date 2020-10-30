package com.liang3hua.springboot.web.base.common.enums;

public enum BusinessCodeEnum {
	DTS("S01001", "采集"),
	VIAS("S01002", "解析"),
	VIDEO("S01003", "视频"),
	P_SERVICE("S01004", "公共服务");

	private String code;
	private String msg;

	BusinessCodeEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
