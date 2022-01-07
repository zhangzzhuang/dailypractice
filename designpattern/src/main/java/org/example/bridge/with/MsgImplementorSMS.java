package org.example.bridge.with;

/**
 * @author zhangzhuang
 * @since 2021-08-19
 */
public class MsgImplementorSMS implements MsgImplementor {

	@Override
	public void send(String msg, String userId) {
		System.out.println("站内信-----" + msg + "给" + userId);
	}
}
