package com.example.diyredis.stock;

/**
 * 获取库存回调
 * @author zhangzhuang
 * @since 2022-01-04
 */
public interface IStockCallback {

	/**
	 * 获取库存
	 * @return
	 */
	int getStock();
}
