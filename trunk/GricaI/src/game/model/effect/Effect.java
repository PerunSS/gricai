package game.model.effect;

import game.model.monster.Monster;

public abstract class Effect {

	private Monster target;

	public void setTarget(Monster target) {
		this.target = target;
	}

	public Monster getTarget() {
		return target;
	}
}
