package com.gricai.game;

public class Team {

	private int size;
	private Player[] players;

	public Team(int size) {
		this.size = size;
		players = new Player[size];
	}

	/**
	 * Adds player to team and returns index of player in that team if there is
	 * enough space in team, otherwise returns -1
	 * 
	 * @param player
	 * @return players position (in team), -1 otherwise
	 */
	public int addPlayer(Player player) {
		for (int i = 0; i < size; i++) {
			if (players[i] == null) {
				players[i] = player;
				return i;
			}
		}
		return -1;
	}

	/**
	 * Adds player in team in wanted position, returns that position if
	 * successful, -1 otherwise
	 * 
	 * @param player
	 * @param position
	 * @return
	 */
	public int addPlayer(Player player, int position) {
		if (players[position] == null) {
			players[position] = player;
			return position;
		}
		return -1;
	}

	/**
	 * Checks if player is already in team, and if it is in team returns players
	 * position in team, if player is not in team returns -1
	 * 
	 * @param player
	 * @return players position (if in a team), -1 otherwise
	 */
	public int checkPlayer(Player player) {
		for (int i = 0; i < size; i++) {
			if (players[i] == player) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * removes player at index from team
	 * 
	 * @param index
	 */
	public void removePlayer(int index) {
		players[index] = null;
	}
}
