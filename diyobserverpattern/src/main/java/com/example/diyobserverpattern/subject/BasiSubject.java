package com.example.diyobserverpattern.subject;

import com.example.diyobserverpattern.observer.DiyObserver;

import java.util.ArrayList;
import java.util.List;


/**
 * @author zhangzhuang
 * @since 2021-03-04
 */
public class BasiSubject implements DiySubject{

	private List<DiyObserver> observerList = new ArrayList<>();


	@Override
	public void attach(DiyObserver observer) {
		observerList.add(observer);
	}

	@Override
	public void detach(DiyObserver observer) {
		observerList.remove(observer);
	}

	@Override
	public void notifyChanged() {
		for (DiyObserver o: observerList) {
			o.update();
		}
	}
}
