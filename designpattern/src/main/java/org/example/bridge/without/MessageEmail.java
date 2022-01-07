package org.example.bridge.without;

/**
 * @author zhangzhuang
 * @since 2021-08-19
 */
public class MessageEmail implements Message {

	@Override
	public void send(String msg, String userId) {
		System.out.println("E-mail-----" + msg + "给" + userId);
	}
}
