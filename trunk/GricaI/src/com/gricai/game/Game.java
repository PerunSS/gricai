package com.gricai.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
	
	private Map<String, Player> players = new HashMap<String, Player>();
	private Player currentPlayer;
	private int size;
	private GameMode mode;
	private List<Team> teams;
	
	public Game(int size, GameMode mode){
		this(size,mode,0);
	}
	
	public Game(int size, GameMode mode, int teamSize){
		this.size = size;
		this.mode = mode;
		if(this.mode == GameMode.FFA){
			teams = new ArrayList<Team>(size);
		}else{
			teams = new ArrayList<Team>(size/teamSize);
			for(int i=0;i<teamSize;i++){
				Team team = new Team(teamSize);
				teams.add(team);
			}
		}
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
