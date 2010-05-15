package com.gricai.game.model.magic;

import com.gricai.game.model.board.Field;

public abstract class Magic {

	private int actionPoints;
	
	protected Magic(int actionPoints){
		this.actionPoints = actionPoints;
	}
	
	public abstract void doMagic(Field field);

	public int getActionPoints() {
		return actionPoints;
	}
}
