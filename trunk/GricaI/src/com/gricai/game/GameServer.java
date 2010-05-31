package com.gricai.game;

import old.messages.Message;

import com.gricai.game.server.MessageReceiver;
import com.gricai.game.server.User;

public class GameServer implements MessageReceiver {

	private Game game;
	
	public GameServer(Game game){
		this.game = game;
	}
	
	@Override
	public void connected(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void receive(User from, Message msg) {
		// TODO Auto-generated method stub

	}

}
