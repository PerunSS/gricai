package com.cerspri.games.tapit.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.content.res.Resources;

public class TapITGame implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final long START_GAME_TIME = 20000;
	
	private transient Level currentLevel;
	private int width, height;

	private long score = 0;
	private long time = START_GAME_TIME;
	private long maxTime = 0;

	private List<TapITObject> graphics = new ArrayList<TapITObject>();
	private static TapITGame instance = new TapITGame();

	private int lvl = 1;

	private TapITGame() {
		//currentLevel = Constants.getLevel(lvl);
	}

	/**
	 * 
	 * @param width
	 *            - of screen
	 * @param height
	 *            - of screen
	 * @param res
	 */
	public void generateRandomObject(Resources res) {
		graphics.add(0, currentLevel.generateRandomObject(res));
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
		time = START_GAME_TIME;
	}

	public int getCurrentLevel() {
		return lvl;
	}
	
	public void setLevel(int level){
		lvl = level;
		currentLevel = Constants.getLevel(lvl);
	}

	public void updateScore(long value) {
		score += value;
	}

	public long getScore() {
		return score;
	}
	
	public void setScore(long score){
		this.score = score;
	}

	public synchronized void updateTime(long diff){
		time += diff;
		if (time > TapITGame.START_GAME_TIME && time > maxTime) {
			maxTime = time;
		}

	}

	public synchronized long getTime() {
		return time;
	}
	
	public synchronized void setTime(long time){
		this.time = time;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public long getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(long maxTime) {
		this.maxTime = maxTime;
	}
}
