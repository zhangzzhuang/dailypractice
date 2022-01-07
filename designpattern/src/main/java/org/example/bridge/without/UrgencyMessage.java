package org.example.bridge.without;

/**
 * @author zhangzhuang
 * @since 2021-08-19
 */
public interface UrgencyMessage extends Message {

	/**监控某消息的处理过程
	 * @param msgId
	 * @return 包含监控到的数据对象
	 */
	public Object watch(String msgId);
}
