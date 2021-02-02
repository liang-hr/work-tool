package com.liang3hua.quartzdemo.schedule;

import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;
import org.quartz.core.jmx.QuartzSchedulerMBean;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author lhr
 * @version 1.0.0
 * @Description
 * @createTime 2021-02-02 16:24:00
 */
public class DemoJob extends QuartzJobBean implements InterruptableJob {
    public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private boolean interrupted = false;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        while (!interrupted){

            System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern(TIME_FORMAT)));

            try {
                Thread.sleep(5_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws JobExecutionException {
        new DemoJob().executeInternal(null);
    }

    @Override
    public void interrupt() throws UnableToInterruptJobException {
        System.out.println("中断任务");
        interrupted = true;
    }
}
