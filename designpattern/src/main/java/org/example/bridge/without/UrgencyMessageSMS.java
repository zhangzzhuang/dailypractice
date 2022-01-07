package org.example.bridge.without;

/**
 * @author zhangzhuang
 * @since 2021-08-19
 */
public class UrgencyMessageSMS implements UrgencyMessage {

	@Override
	public Object watch(String msgId) {

		//获取相应的数据，组织成监控的数据对象，然后返回
		return null;
	}

	@Override
	public void send(String msg, String userId) {
		msg = "加急：" + msg;
		System.out.println("站内信-----" + msg + "给" + userId);

	}
}
