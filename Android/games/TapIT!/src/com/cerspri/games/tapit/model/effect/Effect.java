package com.cerspri.games.tapit.model.effect;

public abstract class Effect {

	private long duration;
	
	protected void setDuration(long duration){
		this.duration = duration;
	}

	public long getDuration() {
		return duration;
	}
	
	public abstract void doTap();
	public abstract int getModificator();
}
