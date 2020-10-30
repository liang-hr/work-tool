package com.liang3hua.springboot.web.base.common.result;


import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.liang3hua.springboot.web.base.common.enums.BusinessCodeEnum;
import com.liang3hua.springboot.web.base.common.enums.OpCodeEnum;
import com.liang3hua.springboot.web.base.common.enums.StatusCodeEnum;
import com.liang3hua.springboot.web.base.common.util.InheritableTraceContextHolder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author lhr
 * @date 2020-09-17  22:05
 */
@Setter
@Getter
@Accessors(chain = true)
@ApiModel("页面返回数据")
@NoArgsConstructor
public class PageResult<T> implements Serializable {
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

    //页码
    @JsonProperty("PageNum")
    @ApiModelProperty(value = "页码")
    private int pageNum;
    //每页大小
    @JsonProperty("PageSize")
    @ApiModelProperty(value = "页码大小")
    private int pageSize;
    //总页数
    @JsonProperty("Pages")
    @ApiModelProperty(value = "总页数")
    private int pages;
    //总记录数
    @JsonProperty("Total")
    @ApiModelProperty(value = "总记录数")
    private long total;
    // 业务数据
    @JsonProperty("Data")
    @ApiModelProperty(value = "操作返回数据对象")
    private List<T> data;

    public PageResult(Integer statusCode, Integer opCode, String opDesc){
        this.statusCode = statusCode;
        this.opCode = opCode;
        this.opDesc = opDesc;
    }

    public PageResult(IPage<T> pageInfo){
        this(StatusCodeEnum.SUCCESS.getCode(), OpCodeEnum.SUCCESS.getCode(), OpCodeEnum.SUCCESS.getMsg());

        this.pageNum = Math.toIntExact(pageInfo.getCurrent());
        this.pageSize = Math.toIntExact(pageInfo.getSize());
        this.pages = Math.toIntExact(pageInfo.getPages());
        this.total = pageInfo.getTotal();
        this.data = pageInfo.getRecords();
    }

    public PageResult(int pageNum, int pageSize, long total, List<T> list){
        this(StatusCodeEnum.SUCCESS.getCode(), OpCodeEnum.SUCCESS.getCode(), OpCodeEnum.SUCCESS.getMsg());

        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.pages = (int)(total % pageSize == 0? total/pageSize: total/pageSize + 1);
        this.total = total;
        this.data = list;
    }
}
