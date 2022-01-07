package org.example.dependenceinversion;

/**
 * @author zhangzhuang
 * @since 2021-12-17
 */
public class User {

	public void study(ICourse course){

		course.courseItem();

	}

	public static void main(String[] args) {
		User user = new User();
		user.study(new JavaCourse());
		user.study(new PythonCourse());
	}
}
