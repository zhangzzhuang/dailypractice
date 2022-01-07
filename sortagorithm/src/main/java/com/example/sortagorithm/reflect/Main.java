package com.example.sortagorithm.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * @author zhangzhuang
 * @since 2021-04-26
 */
public class Main {

	public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {


		//获取targetObject类的Class对象并且创建targetObject类实例
		Class<?> clazz = Class.forName("com.example.sortagorithm.reflect.TargetObject");

		TargetObject targetObject = (TargetObject) clazz.newInstance();


		//获取所有类中所有定义的方法
		Method[] methods = clazz.getDeclaredMethods();

		for (Method m : methods) {
			System.out.println(m.getName());
		}

		//获取指定方法并调用
		Method publicMethod = clazz.getDeclaredMethod("publicMethod",String.class);

		publicMethod.invoke(targetObject,"zzzzzzz");

		//获取指定参数并对参数进行修改
		Field field = clazz.getDeclaredField("value");
		//关闭访问权限的检查
		field.setAccessible(true);

		field.set(targetObject,"bbbbbbbbb");

		//调用private方法
		Method privateMethod = clazz.getDeclaredMethod("privateMethod");
		privateMethod.setAccessible(true);
		privateMethod.invoke(targetObject);
	}
}
