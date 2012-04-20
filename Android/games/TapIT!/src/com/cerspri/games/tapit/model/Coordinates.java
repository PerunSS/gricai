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
		
		if (Math.abs(x - this.x) <= width +2
				&& Math.abs(y - this.y) <= height+2){
			System.out.println("("+this.x+" "+this.y+") <= ("+x+","+y+")");
			return true;
		}
		return false;
	}

}
