package org.example.bridge.without;

/**
 * @author zhangzhuang
 * @since 2021-08-19
 */
public class UrgencyMessageEmail implements UrgencyMessage{
	@Override
	public Object watch(String msgId) {
		return null;
	}

	@Override
	public void send(String msg, String userId) {
		msg = "加急：" + msg;
		System.out.println("E-mail-----" + msg + "给" + userId);
	}
}
