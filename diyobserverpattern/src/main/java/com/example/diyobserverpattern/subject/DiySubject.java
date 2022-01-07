package com.example.diyobserverpattern.subject;

import com.example.diyobserverpattern.observer.DiyObserver;


/**
 * 被观察者
 * @author zhangzhuang
 * @since 2021-03-04
 */
public interface DiySubject {

	/**
	 * 	订阅操作
	 */
	void attach(DiyObserver observer);

	/**
	 * 取消订阅操作
	 */
	void detach(DiyObserver observer);

	/**
	 * 通知变动
	 */
	void notifyChanged();

}
