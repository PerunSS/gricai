package com.cerSprikRu.CocktailMixer.model.drink;

import java.util.ArrayList;
import java.util.List;

public class Category {
	private String name;
	private List<Drink> drinks = new ArrayList<Drink>();
	private double percent;
	private double alcPercent;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void addDrink(Drink drink){
		drink.setAlcPercent(alcPercent);
		drinks.add(drink);
	}
	
	public List<Drink> getDrinks(){
		return drinks;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}

	public double getPercent() {
		return percent;
	}

	public void setAlcPercent(double alcPercent) {
		this.alcPercent = alcPercent;
	}

	public double getAlcPercent() {
		return alcPercent;
	}
}
