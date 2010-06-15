package com.gricai.game;

import java.util.HashMap;
import java.util.Map;

import com.gricai.common.message.Message;
import com.gricai.common.message.common.ChatMessage;
import com.gricai.common.message.game.InfoToAllMessage;

public class Game {

	private Map<String, Player> players = new HashMap<String, Player>();
	private Player currentPlayer;
	private int size;
	private GameMode mode;
	private Team teams[];
	private int teamSize;

	private static final int DEFAULT_TEAM_SIZE = 1;
	public static final int FAIL_COMMAND = -1;

	enum GameState {
		PREPARE, STARTING, PLAY, ENDING
	}

	// private GameState gameState;

	public Game(int size, GameMode mode) {
		this(size, mode, DEFAULT_TEAM_SIZE);
	}

	public Game(int size, GameMode mode, int teamSize) {
		this.size = size;
		this.mode = mode;
		if (this.mode == GameMode.FFA) {
			teams = new Team[size];
			this.teamSize = teamSize;
			for (int i = 0; i < size; i++) {
				Team team = new Team(1);
				teams[i] = team;
			}
		} else {
			this.teamSize = teamSize;
			teams = new Team[size / teamSize];
			for (int i = 0; i < size / teamSize; i++) {
				Team team = new Team(teamSize);
				teams[i] = team;
			}
		}
		// gameState = GameState.PREPARE;
	}

	/**
	 * Adds player to first available team, and to first available spot in that
	 * team, returns index in game if successful, -1 otherwise
	 * 
	 * @param player
	 * @return index of player in game, -1 otherwise
	 */
	public int joinGame(Player player) {
		if (players.size() < size) {
			for (int i = 0; i < teams.length; i++) {
				int index = teams[i].addPlayer(player);
				if (index != FAIL_COMMAND) {
					index += i * teamSize;
					player.setGameIndex(index);
					return index;
				}
			}
		}
		return -1;
	}

	/**
	 * Changes player team and returns index of player in the game, -1 if not
	 * successful
	 * 
	 * @param player
	 * @param teamNumber
	 * @return player index in game, -1 otherwise
	 */
	public int changeTeam(Player player, int teamNumber) {
		int index = teams[teamNumber].checkPlayer(player);
		if (index == FAIL_COMMAND) {
			removePlayerFromTeam(player);
			index = teams[teamNumber].addPlayer(player);
		}
		if (index != FAIL_COMMAND) {
			index += teamNumber * teamSize;
		}
		player.setGameIndex(index);
		return index;

	}

	/**
	 * Changes player game position (also team) and returns that position if
	 * successful, -1 otherwise
	 * 
	 * @param player
	 * @param position
	 * @return wanted position, -1 otherwise
	 */
	public int changePosition(Player player, int position) {
		if (player.getGameIndex() == position)
			return position;

		int newTeamIndex = position / teamSize;
		int newPosition = position - newTeamIndex * teamSize;
		int index = teams[newTeamIndex].addPlayer(player, newPosition);
		if (index != FAIL_COMMAND) {
			removePlayerFromTeam(player);
			player.setGameIndex(position);
			return position;
		}
		return FAIL_COMMAND;
	}

	/**
	 * removes player from team and from game
	 * 
	 * @param player
	 */
	public void removePlayer(Player player) {
		removePlayerFromTeam(player);
		players.remove(player.getUsername());
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public synchronized void recieve(Message msg) {

	}

	/**
	 * sends current state of game to all players
	 */
	public void sendInfoToAll() {
		InfoToAllMessage message = new InfoToAllMessage();
		message.fillMessage(this);
		for (Map.Entry<String, Player> entry : players.entrySet()) {
			entry.getValue().getUser().send(message);
		}
	}

	private void removePlayerFromTeam(Player player) {
		int oldTeamIndex = player.getGameIndex() / teamSize;
		int positionInOldTeam = player.getGameIndex() - oldTeamIndex * teamSize;
		teams[oldTeamIndex].removePlayer(positionInOldTeam);
	}

	public void sendChatToAll(ChatMessage message) {
		for (Map.Entry<String, Player> entry : players.entrySet()) {
			entry.getValue().getUser().send(message);
		}
		
	}
}
