package org.example.bridge.without;

/**
 * @author zhangzhuang
 * @since 2021-08-19
 */
public interface Message {

	/**
	 * @param msg
	 * @param userId
	 */
	void send(String msg,String userId);
}
