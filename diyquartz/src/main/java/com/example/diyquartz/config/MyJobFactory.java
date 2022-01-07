package com.example.diyquartz.config;

import org.quartz.spi.TriggerFiredBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author zhangzhuang
 * @since 2020-10-21
 */
@Component
public class MyJobFactory extends AdaptableJobFactory{


	@Autowired
	private AutowireCapableBeanFactory capableBeanFactory;

	protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
		//调用父类的方法
		Object jobInstance = super.createJobInstance(bundle);
		capableBeanFactory.autowireBean(jobInstance);

		return jobInstance;
	}
}
