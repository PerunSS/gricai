package com.gricai.game.model.effect;

import com.gricai.game.model.monster.Monster;

public abstract class Effect {

	private Monster target;

	public void setTarget(Monster target) {
		this.target = target;
	}

	public Monster getTarget() {
		return target;
	}
}
