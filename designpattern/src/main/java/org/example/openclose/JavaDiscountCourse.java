package org.example.openclose;

/**
 * @author zhangzhuang
 * @since 2021-12-17
 */
public class JavaDiscountCourse extends JavaCourse {

	private Double discountPrice;

	public JavaDiscountCourse(Integer id, String name, Double price) {
		super(id, name, price);
	}

	public Double getDiscountPrice() {
		return super.getPrice() * 0.61;
	}
}
