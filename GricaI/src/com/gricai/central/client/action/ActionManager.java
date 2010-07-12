package com.gricai.central.client.action;

public class ActionManager {

	private LoginAction loginAction = new LoginAction();
	private ExitAction exitAction = new ExitAction();
	
	public LoginAction getLoginAction(){
		return loginAction;
	}

	public ExitAction getExitAction() {
		return exitAction;
	}
	
}
