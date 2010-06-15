package com.gricai.game;

import com.gricai.game.server.User;


public class Player {
	private String username;
	private User user;
	
	private int gameIndex;
	
	public Player(User user, String username){
		this.username = username;
		this.setUser(user);
	}
	
	public Player(User user){
		this(user, null);
	}

	public String getUsername() {
		return username;
	}

	public void setGameIndex(int gameIndex) {
		this.gameIndex = gameIndex;
	}

	public int getGameIndex() {
		return gameIndex;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
