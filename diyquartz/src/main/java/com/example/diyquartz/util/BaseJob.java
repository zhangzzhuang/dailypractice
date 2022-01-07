package com.example.diyquartz.util;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author zhangzhuang
 * @since 2020-10-21
 */
public interface BaseJob extends Job {


	void execute(JobExecutionContext context) throws JobExecutionException;
}
