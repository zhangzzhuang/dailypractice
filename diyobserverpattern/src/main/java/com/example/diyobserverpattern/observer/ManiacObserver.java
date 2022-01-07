package com.example.diyobserverpattern.observer;

/**
 * @author zhangzhuang
 * @since 2021-03-04
 */
public class ManiacObserver implements DiyObserver {

	@Override
	public void update() {
		System.out.println(this.getClass()+"接收到了通知");
	}
}
