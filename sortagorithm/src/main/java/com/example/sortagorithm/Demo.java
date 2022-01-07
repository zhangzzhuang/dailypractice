package com.example.sortagorithm;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author zhangzhuang
 * @since 2021-09-13
 */
public class Demo {


	public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

		Class<?> aClass = Class.forName("com.example.sortagorithm.User");
		User user1 = (User) aClass.newInstance();

		User user2 = (User) aClass.getConstructor(String.class,String.class).newInstance("zhangsan","20");

		Method method = user2.getClass().getMethod("sayHello",String.class);

		method.invoke(user2,"ssss");
	}


}
class User{

	private String name;
	private String age;
	public User(){}
	public User(String name, String age) {
		this.name = name;
		this.age = age;
	}
	@Override
	public String toString() {
		return "Demo [name=" + name + ", age=" + age + "]";
	}

	public void sayHello(String name) {
		System.out.println("Hello "+name);
	}

}