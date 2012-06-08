package com.cerspri.games.tapit.lite.model;

import java.io.Serializable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.cerspri.games.tapit.lite.model.effect.Effect;

public class TapITObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Coordinates coordinates;

	private transient Effect effect;
	private transient Bitmap bitmap;

	private long lifeTime = 500;
	private boolean clicked = false;
	private long timeValue = 1000;

	public TapITObject(long lifeTime, long timeValue, Resources res) {
		this.bitmap = BitmapFactory.decodeResource(res, Constants.getResource(
				TapITGame.getInstance().getCurrentLevel(),
				(int) (timeValue / 1000)));
		coordinates = new Coordinates(bitmap.getWidth(), bitmap.getHeight());

		this.lifeTime = lifeTime;
		this.timeValue = timeValue;
		int imageWidth = bitmap.getWidth();
		int imageHeight = bitmap.getHeight();
		int x = (int) (Math.random() * (TapITGame.getInstance().getWidth() - imageWidth));
		// dodajemo 50 zbog gornjeg dela ekrana (mesto gde ce se ispisivati
		// score, itd.)
		int y = 50 + (int) (Math.random() * (TapITGame.getInstance()
				.getHeight() - imageHeight - 50));
		coordinates.setX(x);
		coordinates.setY(y);
	}

	public long getTimeValue() {
		return timeValue;
	}

	public Bitmap getBitmap() {
		if (effect != null)
			return effect.getBitmap();
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
