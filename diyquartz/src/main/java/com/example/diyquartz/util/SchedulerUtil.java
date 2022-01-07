package com.example.diyquartz.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author zhangzhuang
 * @since 2020-10-21
 */
public class SchedulerUtil {

	private static Logger logger = LoggerFactory.getLogger(SchedulerUtil.class);


	/**
	 * 新增定时任务
	 *
	 * @param jobClassPath 类路径
	 * @param jobName      任务名称
	 * @param jobGroup     组别
	 * @param jobCron      Cron表达式
	 * @param jobDataMap   需要传递的参数
	 * @throws Exception
	 */
	public static void addJob(String jobClassPath, String jobName, String jobGroup, String jobCron, String jobDataMap) {

		try {
			// 通过SchedulerFactory获取一个调度器实例
			SchedulerFactory sf = new StdSchedulerFactory();
			Scheduler scheduler = sf.getScheduler();
			// 启动调度器
			scheduler.start();
			//构建job信息
			JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassPath).getClass())
					.withIdentity(jobName,jobGroup).build();
			// JobDataMap用于传递任务运行时的参数，比如定时发送邮件，可以用json形式存储收件人等等信息
			if (StringUtils.isNotEmpty(jobDataMap)){
				JSONObject jb = JSONObject.parseObject(jobDataMap);
				Map<String, Object> dataMap =(Map<String, Object>) jb.get("data");
				dataMap.entrySet().stream().forEach(entry -> jobDetail.getJobDataMap().put(entry.getKey(),entry.getValue()));
			}
			//构建cron触发器
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(jobCron);
			CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName,jobGroup)
					.withSchedule(scheduleBuilder).startNow().build();
			scheduler.scheduleJob(jobDetail,trigger);
		} catch (Exception e) {
			logger.info("创建定时任务失败" + e);
		}

	}

	/**
	 * 删除一个定时任务
	 *
	 * @param jobName  任务名称
	 * @param jobGroup 组别
	 * @throws Exception
	 */
	public static void jobDelete(String jobName, String jobGroup) {

		try {
			SchedulerFactory factory = new StdSchedulerFactory();
			Scheduler scheduler = factory.getScheduler();
			TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
			scheduler.pauseTrigger(triggerKey);
			scheduler.unscheduleJob(triggerKey);
			scheduler.deleteJob(JobKey.jobKey(jobName, jobGroup));
		} catch (SchedulerException e) {
			logger.info("删除定时任务失败" + "---" + jobName + "---" + jobGroup + e);
		}
	}

	/**
	 * 更新定时任务表达式
	 *
	 * @param jobName  任务名称
	 * @param jobGroup 组别
	 * @param jobCron  Cron表达式
	 * @throws Exception
	 */
	public static void jobReschedule(String jobName, String jobGroup, String jobCron) {

		try {
			SchedulerFactory factory = new StdSchedulerFactory();
			Scheduler scheduler = factory.getScheduler();
			TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);

			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(jobCron);
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).startNow().build();
			scheduler.rescheduleJob(triggerKey, trigger);
		} catch (SchedulerException e) {
			logger.info("更新定时任务失败" + "---" + jobName + "---" + jobGroup + e);
		}
	}

	/**
	 * 检查Job是否存在
	 *
	 * @throws Exception
	 */
	public static Boolean isResume(String jobName, String jobGroup) {

		try {
			SchedulerFactory factory = new StdSchedulerFactory();
			Scheduler scheduler = factory.getScheduler();
			TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
			return scheduler.checkExists(triggerKey);
		} catch (SchedulerException e) {
			logger.info("检查Job是否存在失败" + "---" + jobName + "---" + jobGroup + e);
		}
		return false;
	}

	/**
	 * 启用一个定时任务
	 *
	 * @param jobName  任务名称
	 * @param jobGroup 组别
	 * @throws Exception
	 */
	public static void jobResume(String jobName, String jobGroup) {

		try {
			SchedulerFactory factory = new StdSchedulerFactory();
			Scheduler scheduler = factory.getScheduler();
			scheduler.resumeJob(JobKey.jobKey(jobName, jobGroup));
		} catch (SchedulerException e) {
			logger.info("启用一个定时任务失败" + "---" + jobName + "---" + jobGroup + e);

		}

	}


	/**
	 * 获取Job实例
	 * @param classname
	 * @return
	 * @throws Exception
	 */
	public static BaseJob getClass(String classname) throws Exception {
		try {
			Class<?> c = Class.forName(classname);
			return (BaseJob) c.newInstance();
		} catch (Exception e) {
			throw new Exception("类["+classname+"]不存在！");
		}

	}
}
