package com.cerspri.games.tapit.model;

import java.io.Serializable;

public class Coordinates implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private int width, height;

	public Coordinates(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String toString() {
		return "Coordinates: (" + x + "/" + y + ")";
	}

	public boolean contains(int x, int y) {

		if (x - this.x >= 0 && x - this.x <= width && y - this.y >= 0
				&& y - this.y <= height) {
			return true;
		}
		return false;
	}

}
