package com.cerSprikRu.CocktailMixer.model.drink;

import java.util.ArrayList;
import java.util.List;

public class Cocktail {

	private List<Drink> drinks = new ArrayList<Drink>();
	private int amount = 0;
	
	public void addDrink(Drink drink){
		Drink newDrink = new Drink();
		newDrink.setName(drink.getName());
		newDrink.setAmount(drink.getAmount());
		boolean shouldAdd = true;
		for(Drink d:drinks){
			if(d.getName().equalsIgnoreCase(drink.getName())){
				d.incAmount(drink.getAmount());
				shouldAdd = false;
			}
		}
		if(shouldAdd)
			drinks.add(newDrink);
		recalculateAmount();
	}
	
	private void recalculateAmount() {
		amount = 0;
		for(Drink drink: drinks){
			amount+=drink.getAmount();
		}
	}

	public int getAmount(){
		return amount;
	}
	
	public List<Drink> getDrinks(){
		return drinks;
	}
	
	@Override
	public String toString() {
		String str="\namount: "+amount+" mesure(s)\n";
		if(amount>9)
			str+="mix in shaker wiht ice: ";
		else
			str+="put in glass: ";
		str+="\n";
		for(Drink drink:drinks){
			str+=drink+"\n";
		}
		return str;
	}
}
