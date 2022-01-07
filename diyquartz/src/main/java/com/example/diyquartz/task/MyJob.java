package com.example.diyquartz.task;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author zhangzhuang
 * @since 2020-10-21
 */
//step 2
public class MyJob implements Job {
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
				//哎可死Q特

				//Executor 亦嗝Z克特
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		JobDataMap jobMap = context.getJobDetail().getJobDataMap();

		JobDataMap triggerMap = context.getTrigger().getJobDataMap();
		System.out.println("" + sf.format(date) + "任务1开始执行" + jobMap.getString("basi") + "-----" + triggerMap.getString("basi"));
	}
}
