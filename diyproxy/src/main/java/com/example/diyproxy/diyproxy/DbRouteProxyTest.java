package com.example.diyproxy.diyproxy;

import com.example.diyproxy.diyproxy.entity.Order;
import com.example.diyproxy.diyproxy.proxy.OrderServiceDynamicProxy;
import com.example.diyproxy.diyproxy.proxy.OrderServiceStaticProxy;
import com.example.diyproxy.diyproxy.service.IOrderService;
import com.example.diyproxy.diyproxy.service.OrderService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhangzhuang
 * @since 2020-10-19
 */
public class DbRouteProxyTest {


	public static void main(String[] args) {
//		静态代理
		Order staticOrder = new Order();

		staticOrder.setCreateTime(System.currentTimeMillis());

		OrderServiceStaticProxy orderServiceStaticProxy = new OrderServiceStaticProxy(new OrderService());

		orderServiceStaticProxy.createOrder(staticOrder);
//

//		动态代理


		try {
			Date date = new SimpleDateFormat("yyyy/MM/dd").parse("2019/03/01");
			Order order = new Order();
			order.setCreateTime(date.getTime());

			IOrderService orderService = new OrderServiceDynamicProxy().getProxyObj(new OrderService());
			orderService.createOrder(order);
		} catch (ParseException e) {
			e.printStackTrace();
		}


	}
}
