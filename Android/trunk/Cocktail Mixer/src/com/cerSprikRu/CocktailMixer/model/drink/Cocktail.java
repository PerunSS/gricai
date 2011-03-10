package com.cerSprikRu.CocktailMixer.model.drink;

import java.util.ArrayList;
import java.util.List;

public class Cocktail {

	private List<Drink> drinks = new ArrayList<Drink>();
	private int amount = 0;
	
	public void addDrink(Drink drink){
		boolean shouldAdd = true;
		for(int i=0;i<drinks.size();i++){
			if(drinks.get(i).getName().equalsIgnoreCase(drink.getName())){	
				drinks.get(i).setAmount(drinks.get(i).getAmount() + drink.getAmount());
				shouldAdd = false;
				break;
			}
		}
		if(shouldAdd)
			drinks.add(drink);
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
		String str="\namount: "+amount+"cl\n";
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
