package org.example.openclose;

/**
 * @author zhangzhuang
 * @since 2021-12-17
 */
public class JavaCourse implements ICourse {

	private Integer id;
	private String name;
	private Double price;

	public JavaCourse(Integer id, String name, Double price) {
		id = id;
		this.name = name;
		this.price = price;
	}

	@Override
	public Integer getId() {
		return null;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public Double getPrice() {
		return null;
	}
}
