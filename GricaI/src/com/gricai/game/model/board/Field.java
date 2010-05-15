package com.gricai.game.model.board;

import com.gricai.game.model.monster.Monster;
import com.gricai.game.model.obstacle.Obstacle;


public class Field {

	private Monster monster;
	private Obstacle obstacle;

	public Monster getMonster() {
		return monster;
	}

	public void setMonster(Monster monster) {
		this.monster = monster;
	}

	public Obstacle getObstacle() {
		return obstacle;
	}

	public void setObstacle(Obstacle obstacle) {
		this.obstacle = obstacle;
	}
}
