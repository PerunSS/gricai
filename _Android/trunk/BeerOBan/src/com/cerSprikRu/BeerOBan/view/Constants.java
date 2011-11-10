package com.cerSprikRu.BeerOBan.view;

import android.content.res.Resources;

public class Constants {

	private static Constants instance = new Constants();
	private Resources resources;
	private int width;
	private int height;
	private int tileSize;
	private int startX, startY;
	private double ratio;
	private double moveStep;

	public static final int MAX_TILES_X = 10;
	public static final int MAX_TILES_Y = 6;
	public static final int MAX_TILE_SIZE = 70;
	public static final int ANIMATION_STEPS = 15;
	

	private Constants() {
	}

	public static Constants getInstance() {
		return instance;
	}

	public void setResources(Resources resources) {
		this.resources = resources;
		initConstants();
	}

	public void calculetaStartingPosition(int rows, int columns) {
		if (rows != -1 && columns != -1) {
			startX = (width - columns * tileSize) / 2;
			startY = (int) (0.06 * height + (0.9 * height - rows * tileSize) / 2);
		}

		System.out.println("screen: ["+width+"."+height+"], start x: [" + startX + ", " + startY + "]: " + tileSize);
	}

	private void initConstants() {
		width = resources.getDisplayMetrics().widthPixels;
		height = resources.getDisplayMetrics().heightPixels;
		tileSize = width / MAX_TILES_X < (int) (0.9 * height / MAX_TILES_Y) ? width
				/ MAX_TILES_X
				: (int) (0.9 * height / MAX_TILES_Y);
		if (tileSize > MAX_TILE_SIZE)
			tileSize = MAX_TILE_SIZE;
		ratio = ((double) height)/height;
		moveStep = ((double) tileSize)/ANIMATION_STEPS;
		System.out.println(ratio);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getTileSize() {
		return tileSize;
	}

	public int getStartX() {
		return startX;
	}

	public int getStartY() {
		return startY;
	}
	
	public double getRatio(){
		return ratio;
	}

	public double getMoveStep() {
		return moveStep;
	}
}
