package com.cerSprikRu.BeerOBan.model.board;

import com.cerSprikRu.BeerOBan.model.objects.GameObject;
import com.cerSprikRu.BeerOBan.model.objects.Player;

public class Tile {

	private int x, y;
	private boolean obstacle, destination;
	private GameObject gameObject;

	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
		System.out.println(x+","+y);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isObstacle() {
		return obstacle;
	}

	public boolean isDestination() {
		return destination;
	}

	public GameObject getObject() {
		return gameObject;
	}

	public GameObject getGameObject() {
		return gameObject;
	}

	public void setGameObject(GameObject gameObject) {
		this.gameObject = gameObject;
		if (gameObject != null)
			gameObject.setPosition(this);
	}

	public void setObstacle(boolean obstacle) {
		this.obstacle = obstacle;
	}

	public void setDestination(boolean destination) {
		this.destination = destination;
	}

	@Override
	public String toString() {
		if (gameObject != null) {
			if (gameObject instanceof Player)
				return "P";
			else
				return "O";
		}
		if (isDestination()) {
			return "X";
		}
		return " ";
	}
}
