package com.example.sortagorithm;

import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author zhangzhuang
 * @since 2021-09-13
 * 自定义一些事件监听处理器，根据自己的需要在针对每个事件做一些业务处理
 */
@Component
public class BasiApplicationListener implements ApplicationListener<ApplicationEvent> {
	@Override
	public void onApplicationEvent(ApplicationEvent applicationEvent) {
		if (applicationEvent instanceof ApplicationStartingEvent) {
			System.out.println("ApplicationStarting");
			return;
		}
		if (applicationEvent instanceof ApplicationPreparedEvent){
			System.out.println("ApplicationEnvironmentPrepared");
			return;
		}
		if (applicationEvent instanceof ApplicationFailedEvent) {
			System.out.println("ApplicationFailed");
			return;
		}
	}
}
