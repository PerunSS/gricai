package model.magic;

import model.board.Field;

public abstract class Magic {

	private int actionPoints;
	
	public Magic(int actionPoints){
		this.actionPoints = actionPoints;
	}
	
	public abstract void doMagic(Field field);

	public int getActionPoints() {
		return actionPoints;
	}
}
