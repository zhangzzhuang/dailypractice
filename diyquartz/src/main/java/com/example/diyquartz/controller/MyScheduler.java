package com.example.diyquartz.controller;

import com.example.diyquartz.task.MyJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author zhangzhuang
 * @since 2020-10-21
 */
public class MyScheduler {

	public static void main(String[] args) throws SchedulerException {
		//step 3 创建调度器

		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();

		//step 4 创建JobDetail实例，绑定Job
		JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
				.withIdentity("job1", "group1")
				.usingJobData("basi", "9527")
				.usingJobData("moon", 5.21F)
				.build();

		//step 5 创建触发器，每隔3秒执行一次
		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("trigger1", "group1")
				.usingJobData("basi", "2673")
				.startNow()
				.withSchedule(SimpleScheduleBuilder.simpleSchedule()
						.withIntervalInSeconds(3)
						.repeatForever()
				).build();

		//step 6 绑定job和触发器，启动调度器
		scheduler.scheduleJob(jobDetail, trigger);
		scheduler.start();
	}
}
