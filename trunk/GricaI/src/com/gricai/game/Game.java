package com.gricai.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gricai.common.message.Message;

public class Game {
	
	private Map<String, Player> players = new HashMap<String, Player>();
	private Player currentPlayer;
	private int size;
	private GameMode mode;
	private List<Team> teams;
	
	enum GameState{
		PREPARE,STARTING,PLAY,ENDING
	}
	
	private GameState gameState;
	
	public Game(int size, GameMode mode){
		this(size,mode,0);
	}
	
	public Game(int size, GameMode mode, int teamSize){
		this.size = size;
		this.mode = mode;
		if(this.mode == GameMode.FFA){
			teams = new ArrayList<Team>(size);
			for(int i=0;i<size;i++){
				Team team = new Team(1);
				teams.add(team);
			}
		}else{
			teams = new ArrayList<Team>(size/teamSize);
			for(int i=0;i<size/teamSize;i++){
				Team team = new Team(teamSize);
				teams.add(team);
			}
		}
		gameState = GameState.PREPARE;
	}
	
	public boolean addPlayer(Player player, int teamNumber){
		if(players.size() < size){
			return teams.get(teamNumber).addPlayer(player);
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
	
	/*
	 * load game, and send to all clients that game is started, save start state
	 */
	private boolean startGame(){
		if(gameState == GameState.PREPARE){
			
			return true;
		}
		return false;
	}
	
	/*
	 * ends game, displays results, and tell all clients that they should return to main server 
	 */
	private void endGame(){
		
	}
	
	/*
	 * returns next player
	 */
	private Player nextPlayer(Player p){
		return null;
	}
	
	public void broadcastMessage(Message msg){
		
	}
	
	public void nextState(){
		
	}
	
	public synchronized void recieve(Message msg){
		
	}
}
