package com.cerspri.games.tapit.model;

import java.util.ArrayList;
import java.util.List;

import android.content.res.Resources;

public class TapITGame {

	private Level currentLevel;

	private long score = 0;

	// private Map<Integer, Level> levels = new HashMap<Integer, Level>();

	private List<TapITObject> graphics = new ArrayList<TapITObject>();
	private static TapITGame instance = new TapITGame();

	private int lvl = 1;

	private TapITGame() {
		currentLevel = Constants.getLevel(lvl);
	}

	/**
	 * 
	 * @param width
	 *            - of screen
	 * @param height
	 *            - of screen
	 * @param res
	 */
	public void generateRandomObject(int width, int height, Resources res) {
		graphics.add(0, currentLevel.generateRandomObject(width, height, res));
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
		if (currentLevel.getNextLVL() < score) {
			lvl++;
			if (lvl > 10) {
				lvl = 10;
			}
			currentLevel = Constants.getLevel(lvl);
		}
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
		score = 0;
		currentLevel = Constants.getLevel(lvl);
	}

	public int getCurrentLevel() {
		return lvl;
	}

	public void updateScore(long value) {
		score += value;
	}

	public long getScore() {
		return score;
	}

}
