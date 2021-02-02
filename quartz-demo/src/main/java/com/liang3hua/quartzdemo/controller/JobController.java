package com.liang3hua.quartzdemo.controller;

import com.liang3hua.quartzdemo.schedule.JobManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.quartz.SchedulerException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author lhr
 * @version 1.0.0
 * @Description
 * @createTime 2021-02-02 16:55:00
 */
@RestController
@RequestMapping("job")
@Api(value = "job管理", tags = "job管理")
public class JobController {

    @Resource
    private JobManager jobManager;

    @ApiOperation(value = "添加job", notes = "添加job")
    @GetMapping("add")
    public String add(
            @ApiParam(name = "jobId", value = "jobId", required = true)
            @RequestParam("jobId") Long jobId,
            @ApiParam(name = "cron", value = "cron", required = true)
            @RequestParam("cron") String cron) throws SchedulerException {
        jobManager.addScheduleJob(jobId, cron);
        return "success";
    }

    @ApiOperation(value = "更新job", notes = "更新job")
    @GetMapping("update")
    public String update(
            @ApiParam(name = "jobId", value = "jobId", required = true)
            @RequestParam("jobId") Long jobId,
            @ApiParam(name = "cron", value = "cron", required = true)
            @RequestParam("cron") String cron) throws SchedulerException {
        jobManager.updateScheduleJob(jobId, cron);
        return "success";
    }

    @ApiOperation(value = "删除job", notes = "删除job")
    @GetMapping("delete")
    public String delete(
            @ApiParam(name = "jobId", value = "jobId", required = true)
            @RequestParam("jobId") Long jobId) throws SchedulerException {
        jobManager.delScheduleJob(jobId);
        return "success";
    }

}
