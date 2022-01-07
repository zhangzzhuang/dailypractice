package com.example.diythread.conditonDemo.user;

/**
 * @author zhangzhuang
 * @since 2021-02-24
 */

public class User {

	private String name;

	@Override
	public String toString() {
		return "User{" +
				"name='" + name + '\'' +
				'}';
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User(String name) {
		this.name = name;
	}
}
