package old.game;

import java.util.ArrayList;
import java.util.List;

public class Game {

	private List<Player> players = new ArrayList<Player>();
	private Player currentPlayer;
	
	public void addPlayer(Player p){
		players.add(p);
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}
}
