package com.cerSprikRu.CocktailMixer.model.drink;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cocktail implements Serializable, Comparable<Cocktail> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Drink> drinks = new ArrayList<Drink>();
	private int amount = 0;
	private String name;

	public void addDrink(Drink drink) {
		Drink newDrink = new Drink();
		newDrink.setName(drink.getName());
		newDrink.setAmount(drink.getAmount());
		newDrink.setType(drink.getType());
		newDrink.setTeaSpoon(drink.isTeaSpoon());
		boolean shouldAdd = true;
		for (Drink d : drinks) {
			if (d.getName().equalsIgnoreCase(drink.getName())) {
				d.incAmount(drink.getAmount());
				shouldAdd = false;
			}
		}
		if (shouldAdd)
			drinks.add(newDrink);
		recalculateAmount();
	}

	private void recalculateAmount() {
		amount = 0;
		for (Drink drink : drinks) {
			amount += drink.getAmount();
		}
	}

	public int getAmount() {
		return amount;
	}

	public List<Drink> getDrinks() {
		return drinks;
	}

	@Override
	public boolean equals(Object o) {
		Cocktail c = (Cocktail) o;
		for (Drink drink1 : drinks) {
			boolean exist = false;
			for (Drink drink2 : c.drinks) {
				if (drink1.getName().equals(drink2.getName())
						&& drink1.getAmount() == drink2.getAmount()) {
					exist = true;
					break;
				}
			}
			if (!exist)
				return false;
		}
		return true;
	}

	@Override
	public String toString() {
		String str = "";
		double random = Math.random();
		if (random < 0.45) {
			str += "Shake in shaker:\n" + (int) (Math.random() * 5 + 5)
					+ " ice cubes";
		} else if (random > 0.65)
			str += "Put in glass:";
		else
			str += "Put in glass:\n" + (int) (Math.random() * 3 + 3)
					+ " ice cubes";
		str += "\n";
		for (Drink drink : drinks) {
			str += drink + "\n";
		}
		return str;
	}

	// public String toFavoritesString() {
	// String str= name+"\n";
	// str+="\namount: "+amount+" mesure(s)\n";
	// if(amount>9)
	// str+="mix in shaker wiht ice: ";
	// else
	// str+="put in glass: ";
	// str+="\n";
	// for(Drink drink:drinks){
	// str+=drink+"\n";
	// }
	// return str;
	// }

	@Override
	public int compareTo(Cocktail another) {
		for (Drink drink1 : drinks) {
			boolean exist = false;
			for (Drink drink2 : another.drinks) {
				if (drink1.getName().equals(drink2.getName())
						&& drink1.getAmount() == drink2.getAmount()) {
					exist = true;
					break;
				}
			}
			if (!exist)
				return -1;
		}
		return 0;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		if (name == null) {
			return "";
		}
		return name;
	}
}
