package com.gricai.game.server;

import com.gricai.common.message.Message;
import com.gricai.game.Player;


public class User implements ConnectionUser {
	private Connection conn;
	private int number;
	private Player player;

	public User(Connection c, MessageReceiver mrec) {
		this(c);
	}

	public User(Connection c) {
		conn = c;
		conn.attach(this);
		player = new Player(this);
	}

	public void send(Message msg) {
		conn.send(msg);
	}

	public void stateChange(int state) {

	}

	public int getNumber() {
		return number;
	}

	@Override
	public void receive(Message msg) {

	}

	public Player getPlayer() {
		return player;
	}

}
