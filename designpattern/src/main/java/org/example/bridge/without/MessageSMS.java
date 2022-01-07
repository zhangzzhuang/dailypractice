package org.example.bridge.without;

/**
 * @author zhangzhuang
 * @since 2021-08-19
 */
public class MessageSMS implements Message {

	@Override
	public void send(String msg, String userId) {
		System.out.println("站内信-----" + msg + "给" + userId);
	}
}
