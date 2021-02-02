package com.liang3hua.quartzdemo.schedule;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author lhr
 * @version 1.0.0
 * @Description
 * @createTime 2021-02-02 16:44:00
 */
@Component
@Slf4j
public class JobManager {

    private static final String job_name = "detect_job";
    private static final String job_group = "detect_job_group";
    private static final String trigger_name = "detect_trigger";
    private static final String trigger_group = "detect_trigger_group";

    @Resource
    private Scheduler scheduler;


    /**
     * 添加调度任务
     * @throws SchedulerException
     */
    public void addScheduleJob(Long jobId, String cron) throws SchedulerException {
        log.info("添加任务调度，id[{}]", jobId);
        JobDetail jobDetail = buildJobDetail(jobId);
        Trigger trigger = buildTrigger(jobId, cron);

        // 手动将触发器与任务绑定到调度器内
        scheduler.scheduleJob(jobDetail, trigger);
    }

    public void updateScheduleJob(Long jobId, String cron) throws SchedulerException {
        if (Objects.isNull(jobId) || Objects.isNull(cron))
            return;

        log.info("更新任务调度，id[{}]", jobId);

        // 检查job是否存在
        JobKey jobKey = getJobKey(jobId);
        if (!scheduler.checkExists(jobKey)){
            // 重新添加调度
            addScheduleJob(jobId, cron);

        }else {

            TriggerKey triggerKey = getTriggerKey(jobId);
            Trigger trigger = buildTrigger(jobId, cron);
            scheduler.rescheduleJob(triggerKey, trigger);
        }
    }

    public void delScheduleJob(Long jobId) throws SchedulerException {
        log.info("删除任务调度，id[{}]", jobId);

        TriggerKey triggerKey = getTriggerKey(jobId);
        // 停止触发器
        scheduler.pauseTrigger(triggerKey);
        // 移除触发器
        scheduler.unscheduleJob(triggerKey);
        // 中断正在执行的任务
        scheduler.interrupt(getJobKey(jobId));
        // 删除任务
        scheduler.deleteJob(getJobKey(jobId));
    }

    public void delScheduleJob_0(Long jobId) throws SchedulerException {
        log.info("删除任务调度，id[{}]", jobId);

        TriggerKey triggerKey = getTriggerKey(jobId);
        // 停止触发器
        scheduler.pauseTrigger(triggerKey);
        // 移除触发器
        scheduler.unscheduleJob(triggerKey);
        // 删除任务
        scheduler.deleteJob(getJobKey(jobId));
    }

    private Trigger buildTrigger(Long jobId, String cron) {
        if (Objects.isNull(jobId) || Objects.isNull(cron))
            return null;

        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);

        // 创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(getTriggerKey(jobId))
                .withSchedule(scheduleBuilder)
                .build();

        return trigger;
    }

    private JobDetail buildJobDetail(Long jobId) {
        if (Objects.isNull(jobId))
            return null;

        // JobDetail
        JobDetail jobDetail = JobBuilder
                .newJob(DemoJob.class)
                .withIdentity(getJobKey(jobId))
                .storeDurably()
                .build();

        return jobDetail;
    }

    private JobKey getJobKey(Long templateUid){
        return new JobKey(getJobName(templateUid), job_group);
    }

    private TriggerKey getTriggerKey(Long templateUid){
        return new TriggerKey(getTriggerName(templateUid), trigger_group);
    }

    private String getJobName(Long templateUid){
        return job_name + "_" +templateUid;
    }

    private String getTriggerName(Long templateUid){
        return trigger_name + "_" +templateUid;
    }
}
