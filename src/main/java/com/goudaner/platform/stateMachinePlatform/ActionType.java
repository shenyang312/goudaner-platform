package com.goudaner.platform.stateMachinePlatform;

/**
 * Event
 */
public enum ActionType {
 APPROVE(1), //批准
	REJECT(2);//拒绝
	
	private int action;
	
	private ActionType(int action) {
		this.action = action;
	}

	public int getAction() {
		return action;
	}
}
