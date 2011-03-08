package com.cerSprikRu.CocktailMixer.model.drink;

import java.util.ArrayList;
import java.util.List;

public class Cocktail {

	private List<Drink> drinks = new ArrayList<Drink>();
	private int amount = 0;
	
	public void addDrink(Drink drink){
		amount+=drink.getAmount();
		boolean shouldAdd = true;
		for(int i=0;i<drinks.size();i++){
			if(drinks.get(i).getName().equalsIgnoreCase(drink.getName())){	
				int amount = drinks.get(i).getAmount() + drink.getAmount();
				System.out.println("============================================");
				System.out.println(drink.getName()+" old amount: "+drinks.get(i).getAmount()+",added: "+drink.getAmount()+", new amount: "+amount);
				drinks.get(i).setAmount(amount);
				shouldAdd = false;
				break;
			}
		}
		if(shouldAdd)
			drinks.add(drink);
				
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
