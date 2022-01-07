package com.example.diyquartz.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.diyquartz.entity.SysJob;
import com.example.diyquartz.exception.BizException;
import com.example.diyquartz.service.ISysJobService;
import com.example.diyquartz.util.Constant;
import com.example.diyquartz.util.SchedulerUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



/**
 * @author zhangzhuang
 * @since 2020-10-21
 */
@RestController
@RequestMapping("job")
public class JobController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ISysJobService sysJobService;


	/**
	 * 添加定时任务
	 *
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/addJob", method = RequestMethod.POST)
	private int addJob(@RequestBody SysJob params) {
		logger.info("添加任务开始... ...");
		int num = 0;
		String jobName = params.getJobName();
		String jobClassPath = params.getJobClassPath();
		String jobGroup = params.getJobGroup();
		String jobCron = params.getJobCron();
		String jobDescribe = params.getJobDescribe();
		String jobDataMap = params.getJobDataMap();

		if (StringUtils.isBlank(params.getJobName())) {
			throw new BizException("任务名称不能为空");
		}
		if (StringUtils.isBlank(jobGroup)) {
			throw new BizException("任务组别不能为空");
		}
		if (StringUtils.isBlank(jobCron)) {
			throw new BizException("Cron表达式不能为空");
		}
		if (StringUtils.isBlank(jobClassPath)) {
			throw new BizException("任务类路径不能为空");
		}

		// 参数不为空时校验格式
		if (StringUtils.isNotBlank(jobDataMap)) {
			try {
				JSONObject.parseObject(jobDataMap);
			} catch (Exception e) {
				throw new BizException("参数JSON格式错误");
			}
		}

		// 已存在校验
		SysJob queryBean = new SysJob();
		queryBean.setJobName(jobName);
		SysJob result = sysJobService.selectByBean(queryBean);
		if (null != result) {
			throw new BizException("任务名为" + jobName + "的任务已存在！");
		}

		SysJob bean = new SysJob();
		bean.setJobName(jobName);
		bean.setJobClassPath(jobClassPath);
		bean.setJobGroup(jobGroup);
		bean.setJobCron(jobCron);
		bean.setJobDescribe(jobDescribe);
		bean.setJobDataMap(jobDataMap);
		bean.setJobStatus(Constant.JOB_STATE.YES);
		try {
			num = sysJobService.insertSelective(bean);
		} catch (Exception e) {
			throw new BizException("新增定时任务失败");
		}

		SchedulerUtil.addJob(jobClassPath, jobName, jobGroup, jobCron, jobDataMap);
		return num;
	}

	/**
	 * 删除一个任务
	 *
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteJob", method = RequestMethod.POST)
	public int deleteJob(@RequestBody SysJob params) {
		logger.info("删除定时任务状态开始... ...");
		int num = 0;
		if (params.getId() == null) {
			throw new BizException("任务ID不能为空");
		}


		// 存在性校验
		SysJob result = sysJobService.selectByBean(params);
		if (null == result) {
			throw new BizException("任务ID为" + params.getId() + "的任务不存在！");
		}

		try {
			num = sysJobService.deleteByPrimaryKey(params.getId());
		} catch (Exception e) {
			throw new BizException("从数据库删除定时任务时发生异常！");
		}
		SchedulerUtil.jobDelete(result.getJobName(), result.getJobGroup());
		return num;
	}


	/**
	 * 修改定时表达式
	 */
	@RequestMapping(value = "/reScheduleJob", method = RequestMethod.POST)
	public int updateByBean(@RequestBody SysJob params) {
		logger.info("修改定时任务信息开始... ...");
		int num = 0;
		String jobCron = params.getJobCron();
		String jobDescribe = params.getJobDescribe();

		// 数据非空校验
		if (params.getId() == null) {
			throw new BizException("任务ID不能为空");
		}
		SysJob result = sysJobService.selectByPrimaryKey(params.getId());
		// 数据不存在
		if (null == result) {
			throw new BizException("根据任务ID[" + params.getId() + "]未查到相应的任务记录");
		}

		params.setJobCron(jobCron);
		params.setJobDescribe(jobDescribe);
		try {
			num = sysJobService.updateByPrimaryKeySelective(params);
		} catch (Exception e) {
			throw new BizException("变更任务表达式异常：" + e.getMessage());
		}
		//只有任务状态为启用，才开始运行
		// 如果先启动再手工插入数据，此处会报空指针异常
		if (result.getJobStatus() == Constant.JOB_STATE.YES) {
			SchedulerUtil.jobReschedule(result.getJobName(), result.getJobGroup(), jobCron);
		}

		// 返回成功
		return num;
	}

	/**
	 * 变更定时任务执行状态
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/changeState", method = RequestMethod.POST)
	public int changeState(@RequestBody SysJob params){
		logger.info("变更定时任务状态开始... ...");
		if (params.getId() == null) {
			throw new BizException("任务ID不能为空");
		}

		// 校验
		SysJob result = sysJobService.selectByBean(params);
		if (null == result) {
			throw new BizException("任务ID为" + params.getId() + "的任务不存在！");
		}

		//如果是现在是启用，则停用
		if(Constant.JOB_STATE.YES == result.getJobStatus()){
			params.setJobStatus(Constant.JOB_STATE.NO);
			Boolean b=SchedulerUtil.isResume(result.getJobName(), result.getJobGroup());
			if (b) {
				SchedulerUtil.jobDelete(result.getJobName(), result.getJobGroup());
			}
		}

		//如果现在是停用，则启用
		if(Constant.JOB_STATE.NO == result.getJobStatus()){
			params.setJobStatus(Constant.JOB_STATE.YES);
			Boolean b=SchedulerUtil.isResume(result.getJobName(), result.getJobGroup());
			//存在则激活，不存在则添加
			if (b) {
				SchedulerUtil.jobResume(result.getJobName(), result.getJobGroup());
			}else {
				SchedulerUtil.addJob(result.getJobClassPath(),result.getJobName(), result.getJobGroup(), result.getJobCron(),result.getJobDataMap());
			}
		}

		try {
			sysJobService.updateByPrimaryKeySelective(params);
		} catch (Exception e) {
			throw new BizException("更新数据库的定时任务信息异常！");
		}
		// 1表示成功
		return 1;

	}
}
