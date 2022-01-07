package com.example.diyproxy.diyproxy.service;

import com.example.diyproxy.diyproxy.dao.OrderDao;
import com.example.diyproxy.diyproxy.entity.Order;

/**
 * @author zhangzhuang
 * @since 2020-10-16
 */
public class OrderService implements  IOrderService {

	OrderDao orderDao;

	public OrderService() {
		this.orderDao = new OrderDao();
	}

	@Override
	public int createOrder(Order order) {
		System.out.println("OrderService调用orderDao创建订单");
		return orderDao.insert();
	}
}
