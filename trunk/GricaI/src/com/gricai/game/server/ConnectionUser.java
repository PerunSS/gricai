package com.gricai.game.server;

import com.gricai.common.message.Message;

public interface ConnectionUser {

	public void receive(Message msg);
	public void stateChange(int state);
}
