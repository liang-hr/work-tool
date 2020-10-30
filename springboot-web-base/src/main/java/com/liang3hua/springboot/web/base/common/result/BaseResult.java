package com.liang3hua.springboot.web.base.common.result;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.liang3hua.springboot.web.base.common.enums.BusinessCodeEnum;
import com.liang3hua.springboot.web.base.common.enums.OpCodeEnum;
import com.liang3hua.springboot.web.base.common.enums.StatusCodeEnum;
import com.liang3hua.springboot.web.base.common.exception.BizException;
import com.liang3hua.springboot.web.base.common.util.InheritableTraceContextHolder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("接口返回封装对象")
public class BaseResult<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	// 请求状态代码
	@JsonProperty("StatusCode")
	@ApiModelProperty(value = "请求状态代码，200：成功，其它：失败")
	private Integer statusCode;
	// 业务操作代码
	@JsonProperty("OpCode")
	@ApiModelProperty(value = "操作结果代码，0：成功，其它：失败")
	private Integer opCode;
	// 业务操作描述
	@JsonProperty("OpDesc")
	@ApiModelProperty(value = "操作结果描述")
	private String opDesc;
	// 业务数据
	@JsonProperty("Data")
	@ApiModelProperty(value = "操作返回数据对象")
	private T data;
	// 业务编码
	@JsonProperty("BusinessCode")
	@ApiModelProperty(value = "业务编码，S01001：采集，S01002：解析，S01003：视频，S01004：公共服务")
	private String businessCode = BusinessCodeEnum.VIDEO.getCode();
	// 时间戳
	@JsonProperty("LocalTime")
	@ApiModelProperty(value = "时间戳")
	private String localTime = DateTime.now().toString("yyyyMMddHHmmss");
	// 链路id
	@JsonProperty("TraceId")
	@ApiModelProperty(value = "链路id")
	private String traceId = InheritableTraceContextHolder.getTrace();

	public BaseResult(Integer statusCode, Integer opCode, String opDesc, T data){
		this.statusCode = statusCode;
		this.opCode = opCode;
		this.opDesc = opDesc;
		this.data = data;
	}

	public static <T> BaseResult<T> success(T data) {
		return new BaseResult<>(StatusCodeEnum.SUCCESS.getCode(), OpCodeEnum.SUCCESS.getCode(), OpCodeEnum.SUCCESS.getMsg(), data);
	}

	public static <T> BaseResult<T> success() {
		return success(null);
	}

	private static BaseResult failWithTraceId(Integer opCode, String msg) {
		String opDesc = StrUtil.format("<{}> {}", InheritableTraceContextHolder.getTrace(), msg);
		return new BaseResult(StatusCodeEnum.SYSTEM_ERROR.getCode(), opCode, opDesc, null);
	}

	public static <T> BaseResult<T> fail(String msg) {
		return failWithTraceId(OpCodeEnum.SYSTEM_ERROR.getCode(), msg);
	}

	public static <T> BaseResult<T> fail(BizException bizException) {
		return failWithTraceId(bizException.getCode(), bizException.getMessage());
	}

	public static <T> BaseResult<T> fail(OpCodeEnum opCodeEnum) {
		return failWithTraceId(opCodeEnum.getCode(), opCodeEnum.getMsg());
	}

}