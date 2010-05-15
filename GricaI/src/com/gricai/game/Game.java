package com.gricai.game;

import java.util.HashMap;
import java.util.Map;

public class Game {
	
	private Map<String, Player> players = new HashMap<String, Player>();
	private Player currentPlayer;
	private int size;
	
	public Game(int size){
		this.size = size;
	}
	
	public boolean addPlayer(Player player){
		if(players.size() < size){
			players.put(player.getUsername(), player);
			return true;
		}
		return false;
	}
	
	public void removePlayer(String username){
		players.remove(username);
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}
}
