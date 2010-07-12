package com.gricai.central.client.action;

import java.awt.event.ActionEvent;
import java.io.IOException;

import com.gricai.central.client.MainGui;
import com.gricai.common.message.server.LoginMessage;

public class LoginAction extends GricaIAbstractAction {
	
	public LoginAction(){
		putValue(NAME, "log in");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String userName = MainGui.getInstance().getLoginPanel().getUserName();
		String password = MainGui.getInstance().getLoginPanel().getPassword();
		LoginMessage loginMsg = new LoginMessage();
		loginMsg.setUsername(userName);
		loginMsg.setPassword(password);
		try {
			MainGui.getInstance().getClient().sendMessage(loginMsg.toByteBuffer());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
