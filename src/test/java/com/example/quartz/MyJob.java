package com.example.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: luozijian
 * @date: 2/26/21 14:55:26
 * @description:
 */
public class MyJob implements Job {

    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(Thread.currentThread() + sdf.format(new Date()));

    }
}
