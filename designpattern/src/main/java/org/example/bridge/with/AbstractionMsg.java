package org.example.bridge.with;

/**
 * @author zhangzhuang
 * @since 2021-08-19
 */
public abstract class AbstractionMsg {

	protected MsgImplementor impl;


	public AbstractionMsg(MsgImplementor impl){
		this.impl = impl;
	}

	public void sendMsg(String msg,String userId){
		impl.send(msg, userId);
	}

	public abstract void sendMon();
}
