package old.game.model.effect;

import old.game.model.monster.Monster;

public abstract class Effect {

	private Monster target;

	public void setTarget(Monster target) {
		this.target = target;
	}

	public Monster getTarget() {
		return target;
	}
}
