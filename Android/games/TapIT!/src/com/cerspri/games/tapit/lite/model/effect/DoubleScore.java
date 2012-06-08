package com.cerspri.games.tapit.lite.model.effect;

import android.graphics.Bitmap;

public class DoubleScore extends Effect {

	private static final int MAX_DURATION = 10;
	private static final int MODIFICATOR = 2;

	public DoubleScore() {
		long duration = (long) (Math.random() * MAX_DURATION) + 1;
		setDuration(duration);
	}

	@Override
	public void doTap() {
		setDuration(getDuration() - 1);
	}
	
	@Override
	public int getModificator() {
		return MODIFICATOR;
	}

	@Override
	public Bitmap getBitmap() {
		// TODO Auto-generated method stub
		return null;
	}

}
