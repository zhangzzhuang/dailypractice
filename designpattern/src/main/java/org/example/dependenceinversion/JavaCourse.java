package org.example.dependenceinversion;

/**
 * @author zhangzhuang
 * @since 2021-12-17
 */
public class JavaCourse implements ICourse{


	@Override
	public void courseItem() {
		System.out.println("我在学习Java课程");
	}
}
