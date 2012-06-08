package com.cerspri.games.tapit.lite.model;

import android.content.res.Resources;

public class Level {

	public static final long MILISECONDS = 1000;

	private final long nextLVL; 
	private double negativeOdd;
	private double negativeMedium, negativeHigh;
	private double positiveMedium, positiveHigh;
	private double lifeTime;
	private double spawn;
	private int level;
	private double minimumOdd = 1;

	public Level(double negativeOdd, int level, double negativeMedium,
			double negativeHigh, double positiveMedium, double positiveHigh,
			double lifeTime, double spawn, double nextLVL) {
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
		this.nextLVL = (long)nextLVL;
	}

	// length je trajanje jednog poena a racuna se kao lengthModificator +
	// Math.random() * valueModificator pri cemu valueModificator zavisi od
	// izvucenog broja (njegova verovatnoca pojavljivanja
	public TapITObject generateRandomObject(Resources res) {
		double random = Math.random();
		int value = 1;
		double lifeTime = this.lifeTime;
		if (random < negativeOdd) {
			random = Math.random();
			if (random < negativeHigh) {
				lifeTime += Math.random() * negativeHigh;
				value = Constants.NEGATIVE_10;
			} else if (random < negativeMedium) {
				lifeTime += Math.random() * negativeMedium;
				value = Constants.NEGATIVE_5;
			} else {
				lifeTime += Math.random();
				value = Constants.NEGATIVE_2;
			}
		} else {
			random = Math.random();
			if (random < positiveHigh) {
				lifeTime += Math.random() * positiveHigh;
				value = Constants.POSITIVE_5;
			} else if (random < positiveMedium) {
				lifeTime += Math.random() * positiveMedium;
				value = Constants.POSITIVE_2;
			} else {
				lifeTime += Math.random();
				value = Constants.POSITIVE_1;
			}
		}
		long lifeTimeMiliseconds = (long) (lifeTime * MILISECONDS);

		
		TapITObject object = new TapITObject(lifeTimeMiliseconds,
				(long) (MILISECONDS * value),res);
		return object;
	}

	public long getSpawnTime() {
		return (long) ((spawn + Math.random() * minimumOdd) * MILISECONDS);
	}
	
	@Override
	public String toString() {
		return "level: "+level+", neg: "+negativeOdd+", neg(m,h) = ("+negativeMedium+","+negativeHigh+"), pos(m,h) = ("+positiveMedium+","+positiveHigh+"), lt = "+lifeTime+", spawn = "+spawn;
	}

	public long getNextLVL() {
		return nextLVL;
	}

}
