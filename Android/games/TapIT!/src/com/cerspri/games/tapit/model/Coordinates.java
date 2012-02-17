package com.cerspri.games.tapit.model;

public class Coordinates {
	private int x;
	private int y;
	private int width, height;

	public Coordinates(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public int getX() {
		return x + width / 2;
	}

	public void setX(int value) {
		x = value - width / 2;
	}

	public int getY() {
		return y + height / 2;
	}

	public void setY(int value) {
		y = value - height / 2;
	}
	
	public int getXForDraw(){
		return x;
	}
	
	public int getYForDraw(){
		return y;
	}

	public String toString() {
		return "Coordinates: (" + x + "/" + y + ")";
	}

	public boolean contains(int x, int y) {
		if (Math.abs(getX() - x) <= width / 2
				&& Math.abs(getY() - y) <= height / 2)
			return true;
		return false;
	}
}
