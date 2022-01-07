package org.example.bridge.with;

/**
 * @author zhangzhuang
 * @since 2021-08-19
 */
public class RefinedAbstraction extends AbstractionMsg {


	public RefinedAbstraction(MsgImplementor impl) {
		super(impl);
	}

	@Override
	public void sendMon() {

	}


	public void otherOperation(){

	}

}
