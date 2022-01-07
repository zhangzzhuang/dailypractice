package org.example.bridge.with;

/**
 * @author zhangzhuang
 * @since 2021-08-19
 */
public class MsgImplementorEmail implements MsgImplementor {

	@Override
	public void send(String msg, String userId) {
		System.out.println("E-mail-----" + msg + "给" + userId);
	}
}
