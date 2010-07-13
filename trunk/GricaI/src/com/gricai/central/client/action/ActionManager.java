package com.gricai.central.client.action;

public class ActionManager {

	private LoginAction loginAction = new LoginAction();
	private ExitAction exitAction = new ExitAction();
	private LogoutAction logoutAction = new LogoutAction();
	private EnterRoomAction enterRoomAction = new EnterRoomAction();
	private ShowGamesAction showGamesAction = new ShowGamesAction();
	private RefreshRoomsAction refreshRoomsAction = new RefreshRoomsAction();
	
	
	public LoginAction getLoginAction(){
		return loginAction;
	}

	public ExitAction getExitAction() {
		return exitAction;
	}

	public LogoutAction getLogoutAction() {
		return logoutAction;
	}

	public EnterRoomAction getEnterRoomAction() {
		return enterRoomAction;
	}

	public ShowGamesAction getShowGamesAction() {
		return showGamesAction;
	}

	public RefreshRoomsAction getRefreshRoomsAction() {
		return refreshRoomsAction;
	}
	
}
