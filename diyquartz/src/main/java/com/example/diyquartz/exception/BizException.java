package com.example.diyquartz.exception;

/**
 * @author zhangzhuang
 * @since 2020-10-21
 *
 * 自定义异常
 */
public class BizException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public BizException(String msg) {
		super(msg);
	}
}
