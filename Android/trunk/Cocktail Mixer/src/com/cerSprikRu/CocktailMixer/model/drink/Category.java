package com.cerSprikRu.CocktailMixer.model.drink;

import java.util.ArrayList;
import java.util.List;

public class Category {
	private String name;
	private List<Drink> drinks = new ArrayList<Drink>();

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void addDrink(Drink drink){
		drinks.add(drink);
	}
	
	public List<Drink> getDrinks(){
		return drinks;
	}
}
