package com.example.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: luozijian
 * @date: 2/26/21 15:00:26
 * @description:
 */
public class MainScheduler {

    //创建调度器
    public static Scheduler getScheduler() throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        return schedulerFactory.getScheduler();
    }


    public static void schedulerJob() throws SchedulerException{
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("job1", "group1").build();
        JobDetail jobDetail1 = JobBuilder.newJob(MyJob.class).withIdentity("job2", "group2").build();
        //创建触发器 每3秒钟执行一次
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group3")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever())
                .build();

        Trigger trigger1 = TriggerBuilder.newTrigger().withIdentity("trigger2", "group3")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever())
                .build();

        Scheduler scheduler = getScheduler();
        //将任务及其触发器放入调度器
        scheduler.scheduleJob(jobDetail, trigger);
//        scheduler.scheduleJob(jobDetail1, trigger1);
        //调度器开始调度任务
        scheduler.start();

    }

    public static void main(String[] args) throws SchedulerException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println("进程启动时间：" + sdf.format(new Date()));
        MainScheduler mainScheduler = new MainScheduler();
        mainScheduler.schedulerJob();
    }
}
