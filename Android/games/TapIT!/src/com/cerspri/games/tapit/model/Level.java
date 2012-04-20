package com.cerspri.games.tapit.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Level {

	public static final long MILISECONDS = 1000;


	private double negativeOdd;
	private double negativeMedium, negativeHigh;
	private double positiveMedium, positiveHigh;
	private double lifeTime;
	private double spawn;
	private int level;
	private double minimumOdd = 1;

	public Level(double negativeOdd, int level, double negativeMedium,
			double negativeHigh, double positiveMedium, double positiveHigh,
			double lifeTime, double spawn) {
		super();
		this.level = level;
		this.negativeOdd = negativeOdd;
		this.negativeMedium = negativeMedium;
		if (negativeMedium < minimumOdd) {
			minimumOdd = negativeMedium;
		}
		this.negativeHigh = negativeHigh;
		if (negativeHigh < minimumOdd) {
			minimumOdd = negativeHigh;
		}
		this.positiveMedium = positiveMedium;
		if (positiveMedium < minimumOdd) {
			minimumOdd = positiveMedium;
		}
		this.positiveHigh = positiveHigh;
		if (positiveHigh < minimumOdd) {
			minimumOdd = positiveHigh;
		}
		this.lifeTime = lifeTime;
		this.spawn = spawn;
	}

	// length je trajanje jednog poena a racuna se kao lengthModificator +
	// Math.random() * valueModificator pri cemu valueModificator zavisi od
	// izvucenog broja (njegova verovatnoca pojavljivanja
	public TapITObject generateRandomObject(int width, int height, Resources res) {
		double random = Math.random();
		int value = 1;
		double lifeTime = this.lifeTime;
		if (random < negativeOdd) {
			random = Math.random();
			if (random < negativeHigh) {
				lifeTime += Math.random() * negativeHigh;
				value = ImageConstants.NEGATIVE_10;
			} else if (random < negativeMedium) {
				lifeTime += Math.random() * negativeMedium;
				value = ImageConstants.NEGATIVE_5;
			} else {
				lifeTime += Math.random();
				value = ImageConstants.NEGATIVE_2;
			}
		} else {
			random = Math.random();
			if (random < positiveHigh) {
				lifeTime += Math.random() * positiveHigh;
				value = ImageConstants.POSITIVE_5;
			} else if (random < positiveMedium) {
				lifeTime += Math.random() * positiveMedium;
				value = ImageConstants.POSITIVE_2;
			} else {
				lifeTime += Math.random();
				value = ImageConstants.POSITIVE_1;
			}
		}
		long lifeTimeMiliseconds = (long) (lifeTime * MILISECONDS);

		Bitmap icon = BitmapFactory.decodeResource(res, ImageConstants.getResource(level, value));

		int imageWidth = icon.getWidth();
		int imageHeight = icon.getHeight();
		int x = (int) (Math.random() * (width - imageWidth));
		// dodajemo 50 zbog gornjeg dela ekrana (mesto gde ce se ispisivati
				// score, itd.)
		int y = 50
				+ (int) (Math.random() * (height - imageHeight - 50));
		
		TapITObject object = new TapITObject(icon, lifeTimeMiliseconds,
				(long) (MILISECONDS * value));
		object.getCoordinates().setX(x);
		object.getCoordinates().setY(y);
		return object;
	}

	public long getSpawnTime() {
		return (long) ((spawn + Math.random() * minimumOdd) * MILISECONDS);
	}
}
