package com.example.diyproxy.diyproxy.proxy;

import com.example.diyproxy.diyproxy.db.DynamicDataSourceEntity;
import com.example.diyproxy.diyproxy.entity.Order;
import com.example.diyproxy.diyproxy.service.IOrderService;
import com.example.diyproxy.diyproxy.service.OrderService;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhangzhuang
 * @since 2020-10-19
 */
public class OrderServiceStaticProxy implements IOrderService {

	private SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

	private IOrderService orderService;

	public OrderServiceStaticProxy(IOrderService orderService) {
		this.orderService = orderService;
	}

	@Override
	public int createOrder(Order order) {
		Long time = order.getCreateTime();
		Integer dbRouter = Integer.valueOf(yearFormat.format(new Date(time)));
		System.out.println("静态代理类自动分配到【DB_" +  dbRouter + "】数据源处理数据" );
		DynamicDataSourceEntity.set(dbRouter);
		this.orderService.createOrder(order);
		DynamicDataSourceEntity.restore();
		return 0;
	}
}
