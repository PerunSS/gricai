package com.gricai.game;

import java.util.HashMap;
import java.util.Map;

public class Team {

	private int size;
	private Map<String,Player> players;
	
	public Team(int size){
		this.size = size;
		players = new HashMap<String, Player>(size);
	}
	
	public boolean addPlayer(Player player){
		if(players.size()<size){
			players.put(player.getUsername(), player);
			return true;
		}
		return false;
	}
}
