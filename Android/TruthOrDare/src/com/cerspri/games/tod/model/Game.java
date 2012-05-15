package com.cerspri.games.tod.model;

import java.util.ArrayList;
import java.util.Map;

import android.content.Context;

import com.cerspri.games.tod.db.customManager.DBManager;

public class Game {
	private ArrayList<Player> players;
	private Map<Integer,ToDQuery> truths;
	private Map<Integer,ToDQuery> dares;
	private int currentPlayer;
	private static Game game;
	
	private Game(){
	}
	
	public static Game getInstance(){
		if (game == null){
			game = new Game();
		}
		return game;
	}
	
	public void loadQueries(Context context){
		DBManager dbman= new DBManager(context);
		this.truths = dbman.getTruths();
		this.dares = dbman.getDares();
	}
	
	public void addPlayer(Player player){
		this.players.add(player);
	}
	
	public Player getPlayer(){
		return this.players.get(this.currentPlayer);
	}
	
	public void changePlayer(int id, Player player){
		this.players.set(id, player);
	}
	
	public void removePlayer(int id){
		this.players.remove(id);
	}
	
	public String getTruth(int dirtiness){
		int playerMask = this.players.get(this.currentPlayer).getMask();
		/*TODO return random truth depending on the player mask and given dirtiness
		 *also it needs some reorganization of how truths and dares are kept 
		*/
		return null;
	}
	
	public String getDare(int dirtiness){
		int playerMask = this.players.get(this.currentPlayer).getMask();
		/*TODO return random truth depending on the player mask and given dirtiness
		 *also it needs some reorganization of how truths and dares are kept 
		*/
		return null;
	}
	
	public void prepareGame(Context context){
		this.loadQueries(context);
		//TODO add if something more is needed, like generating player targets for all players
	}
	
	public void startGame(){
		this.currentPlayer = (int)(Math.random() * this.players.size());
		//TODO dont know at this moment if anything more will be needed here
	}
	
	public void endTurn(){
		this.currentPlayer = (int)(Math.random() * this.players.size());
		//TODO getting next player needs some work (cant be the same one and some other factors)
	}
	
	public void finishGame(){
		//TODO nothing i can think of now should be done at that moment, maybe reset 
		//whatever is keeping track of who played when and what
	}
	
	public void restartGame(){
		this.finishGame();
		this.startGame();
		/*TODO but this above might not be good since maybe finish will completely destroy
		 *  the game class and this should just reset the game in some way*/
	}
}
