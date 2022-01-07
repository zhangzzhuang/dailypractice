package com.example.diyproxy.diyproxy.proxy;

import com.example.diyproxy.diyproxy.db.DynamicDataSourceEntity;
import com.example.diyproxy.diyproxy.service.IOrderService;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhangzhuang
 * @since 2020-10-19
 */
public class OrderServiceDynamicProxy implements InvocationHandler {


	private SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

	IOrderService proxyObj;

	
	public IOrderService getProxyObj(IOrderService proxyObj) {

		this.proxyObj = proxyObj;
		Class<?> clazz = proxyObj.getClass();
		return (IOrderService)Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);

	}

	@Override
	public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
		before(objects[0]);

		Object object = method.invoke(proxyObj, objects);

		after();
		return object;
	}


	private void before(Object target) {
		try {
			Long time = (Long) target.getClass().getMethod("getCreateTime").invoke(target);
			Integer dbRouter = Integer.valueOf(yearFormat.format(new Date(time)));
			System.out.println("静态代理类自动分配到【DB_" + dbRouter + "】数据源处理数据");
			DynamicDataSourceEntity.set(dbRouter);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

	}

	private void after() {
		DynamicDataSourceEntity.restore();
	}
}
