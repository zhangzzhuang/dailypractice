package com.example.diyquartz.task;

import com.example.diyquartz.util.BaseJob;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhangzhuang
 * @since 2020-10-21
 */

@DisallowConcurrentExecution
public class Task1 implements BaseJob {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(Thread.currentThread().getName() + " " +sdf.format(date) + " Task1： ----咕泡学院，只为更好的你----");

	}
}
