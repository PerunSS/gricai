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
	private String text;

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
				if (drink1.getName().equalsIgnoreCase(drink2.getName())
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
		String str = text;
		if(str!=null && str.startsWith("null"))
			str = null;
		if (str == null) {
			str = "";
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
			text = str;
		}
		return str;
	}

	@Override
	public int compareTo(Cocktail another) {
		for (Drink drink1 : drinks) {
			boolean exist = false;
			for (Drink drink2 : another.drinks) {
				if (drink1.getName().equalsIgnoreCase(drink2.getName())
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

	public void generateRecipe() {
		Collections.sort(drinks);
		text = "";
		double random = Math.random();
		if (random < 0.45) {
			text += "Shake in shaker:\n" + (int) (Math.random() * 5 + 5)
					+ " ice cubes";
		} else if (random > 0.65)
			text += "Put in glass:";
		else
			text += "Put in glass:\n" + (int) (Math.random() * 3 + 3)
					+ " ice cubes";
		text += "\n";
		for (Drink drink : drinks) {
			text += drink + "\n";
		}
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
