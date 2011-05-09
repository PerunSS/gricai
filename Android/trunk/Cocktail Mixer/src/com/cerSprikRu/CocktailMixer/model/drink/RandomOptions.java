package com.cerSprikRu.CocktailMixer.model.drink;

public class RandomOptions {

	private static RandomOptions instance = new RandomOptions();
	
	private double strongRatio = 0.2;
	private double liquerRatio = 0.4;
	private double nonAlcRatio = 0.4;
	
	private boolean shortDrinks = true;
	private boolean shotDrinks = false;
	
	public static RandomOptions getInstance(){
		return instance;
	}
	
	private RandomOptions(){}

	public double getStrongRatio() {
		return strongRatio;
	}

	public void setStrongRatio(double strongRatio) {
		this.strongRatio = strongRatio;
	}

	public double getLiquerRatio() {
		return liquerRatio;
	}

	public void setLiquerRatio(double liquerRatio) {
		this.liquerRatio = liquerRatio;
	}

	public double getNonAlcRatio() {
		return nonAlcRatio;
	}

	public void setNonAlcRatio(double nonAlcRatio) {
		this.nonAlcRatio = nonAlcRatio;
	}

	public boolean isShortDrinks() {
		return shortDrinks;
	}

	public void setShortDrinks(boolean shortDrinks) {
		this.shortDrinks = shortDrinks;
	}

	public boolean isShotDrinks() {
		return shotDrinks;
	}

	public void setShotDrinks(boolean shotDrinks) {
		this.shotDrinks = shotDrinks;
	}
}
