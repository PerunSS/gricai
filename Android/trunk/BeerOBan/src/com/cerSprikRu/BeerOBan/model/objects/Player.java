package com.cerSprikRu.BeerOBan.model.objects;

import android.content.res.Resources;

import com.cerSprikRu.BeerOBan.model.board.Tile;
import com.cerSprikRu.BeerOBan.model.enums.Condition;
import com.cerSprikRu.BeerOBan.view.graphicobjects.PlayerEntityGraphicObject;

public class Player extends GameObject {

	private int energy;
	private Condition condition;

	public Player(Tile position, int energy, Resources res) {
		super();
		super.setPosition(position);
		this.energy = energy;
		setGraphics(new PlayerEntityGraphicObject(res));
	}

	public int getEnergy() {
		return energy;
	}

	public Condition getCondition() {
		return condition;
	}

	// public boolean move(Direction direction){
	// if(energy > 0)
	// return Board.getInstance().move(this, direction);
	// return false;
	// }

	public boolean canMove(BeerCrate crate) {
		calculateCondition();
		int weight = 0;
		if(crate!=null)
			weight = crate.getWeight();
		int move = 1;
		switch (condition) {
		case DRUNK:
			move *= 2;
			break;
		case TIRED:
			weight *= 2;
			break;
		}
		int total = weight + move;
		if (total <= energy) {
			energy -= total;
			return true;
		}
		return false;
	}

	public void increseEnergy(int amount) {
		energy += amount;
	}

	private void calculateCondition() {
		if (energy < 10)
			condition = Condition.TIRED;
		else if (energy <= 20)
			condition = Condition.NORMAL;
		else if (energy <=30)
			condition = Condition.DRUNK;
		else
			condition = Condition.WASTED;
	}
}
