package com.cerspri.games.tapit.model.effect;

import android.graphics.Bitmap;

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
	public abstract Bitmap getBitmap();
}
