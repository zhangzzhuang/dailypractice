package com.example.diyproxy.diyproxy.entity;

import lombok.Data;

/**
 * @author zhangzhuang
 * @since 2020-10-16
 */
@Data
public class Order {

	private Object orderInfo;
	//订单创建时间进行按年分库
	private Long createTime;
	private String id;
}
