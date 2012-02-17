package com.cerspri.games.tapit.model;

import android.graphics.Bitmap;

public class TapITObject {

	private Bitmap bitmap;
	private Coordinates coordinates;
	private long lifeTime = 500;
	private boolean clicked = false;
	private long timeValue = 1000;

	public TapITObject(Bitmap bitmap) {
		this.bitmap = bitmap;
		coordinates = new Coordinates(bitmap.getWidth(), bitmap.getHeight());
	}

	public TapITObject(Bitmap bitmap, long lifeTime, long timeValue) {
		this(bitmap);
		this.lifeTime = lifeTime;
		this.timeValue = timeValue;
	}

	public long getTimeValue() {
		return timeValue;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public double getLifeTime() {
		return lifeTime;
	}

	public void updateLifeTime(long substract) {
		lifeTime -= substract;
	}

	public boolean isClicked() {
		return clicked;
	}

	public void click() {
		clicked = true;
	}

}
