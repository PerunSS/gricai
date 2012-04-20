package com.cerspri.games.tapit.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.res.Resources;

public class TapITGame {

	private Level currentLevel;

	private Map<Integer, Level> levels = new HashMap<Integer, Level>();

	private List<TapITObject> graphics = new ArrayList<TapITObject>();
	private static TapITGame instance = new TapITGame();

	private int lvl = 1;

	private TapITGame() {
		generateLevels();

	}

	private void generateLevels() {
		int levelCount = 10;
		double negativeBasic = 0.2;
		double negativeChange = 0.1;
		double changeStep = 0.01;
		// negativne vrednosti se racunaju kao linearna jednacina
		double negativeMediumK = 0.06;
		double negativeMediumN = 0.14;
		double negativeHighK = 0.085;
		double negativeHighN = -0.255;
		// pozitivne vrednosti za medium se racunaju kao linearna jednacina 
		double positiveMediumK = -0.044;
		double positiveMediumN = 0.644;
		// pozitivne vrednosti za high se racunaju kao kvadratna jednacina 
		double positiveHighA = -.015;
		double positiveHighB = 0.105;
		double positiveHighC = 0.12;
		//osnova za duzinu trajanja poena se racuna kao kvadratna jednacina 
		double lifeTImeA = 0.01;
		double lifeTimeB = -0.11;
		double lifeTimeC = 1.1;
		//osnova za ucestalost pojavljivanja se racuna kao kvadratna jednacina
		double spawnA = -0.019;
		double spawnB = 0.189;
		double spawnC = 0.13;
		
		for (int i = 0; i < levelCount; i++) {
			double negativeMedium = (i + 1) * negativeMediumK
					+ negativeMediumN;
			double negativeHigh = (i + 1) * negativeHighK
					+ negativeHighN;
			if (negativeHigh < 0)
				negativeHigh = 0;
			double positiveMedium = (i + 1) * positiveMediumK
					+ positiveMediumN;
			double positiveHigh = (i + 1) * (i + 1) * positiveHighA + (i + 1)
					* positiveHighB + positiveHighC;
			if (positiveHigh < 0) {
				positiveHigh = 0;
			}
			double lifeTime = (i + 1) * (i + 1) * lifeTImeA + (i + 1)
					* lifeTimeB + lifeTimeC;
			
			double spawn = (i + 1) * (i + 1) * spawnA + (i + 1)
					* spawnB + spawnC;
			Level level = new Level(negativeBasic + negativeChange, i + 1,
					negativeMedium, negativeHigh, positiveMedium, positiveHigh, lifeTime, spawn);
			negativeBasic += negativeChange;
			negativeChange -= changeStep;

			levels.put(i + 1, level);
		}
		
		currentLevel = levels.get(lvl);
	}

	/**
	 * 
	 * @param width - of screan
	 * @param height - of screan
	 * @param res
	 */
	public void generateRandomObject(int width, int height, Resources res) {
		graphics.add(0,currentLevel.generateRandomObject(width, height, res));
	}

	public void removeObjects() {
		List<TapITObject> existingObjects = new ArrayList<TapITObject>();
		for (TapITObject graphic : graphics) {
			if (graphic.getLifeTime() > 0 && !graphic.isClicked()) {
				existingObjects.add(graphic);
			}
		}
		graphics = existingObjects;
	}

	public void updateObjects(long time) {
		for (TapITObject object : graphics) {
			object.updateLifeTime(time);
		}
	}

	public static TapITGame getInstance() {
		return instance;
	}

	public void lvlUp() {
		lvl++;
		if(lvl > 10){
			lvl = 10;
		}
		currentLevel = levels.get(lvl);
	}

	public long getSpawnTime() {
		return currentLevel.getSpawnTime();
	}

	public List<TapITObject> getGraphics() {
		return graphics;
	}

	public void clear() {
		graphics.clear();
		lvl = 1;
	}

}
