package com.example.sortagorithm.reflect;

/**
 * @author zhangzhuang
 * @since 2021-04-26
 */
public class TargetObject {

	private String value;

	public TargetObject() {
		value = "JavaGuide";
	}

	public void publicMethod(String s) {
		System.out.println("I love " + s);
	}

	private void privateMethod() {
		System.out.println("value is " + value);
	}

}
