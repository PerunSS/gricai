package com.cerSprikRu.CocktailMixer.model.drink;

import java.io.Serializable;

public class Drink implements Serializable, Comparable<Drink> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int amount;
	private String name;
	private DrinkType type;
	private boolean teaSpoon;

	public Drink() {
		teaSpoon = false;
	}

	public Drink(boolean teaSpoon) {
		this.teaSpoon = teaSpoon;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getAmount() {
		return amount;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setType(DrinkType type) {
		this.type = type;
	}

	public DrinkType getType() {
		return type;
	}

	@Override
	public String toString() {
		String mesure = "messure";
		if (teaSpoon)
			mesure = "teaspon";
		if (amount > 1)
			mesure += "s";
		return name + " " + amount + " " + mesure;
	}

	public void incAmount(int amount2) {
		amount += amount2;
	}

	public boolean isTeaSpoon() {
		return teaSpoon;
	}

	@Override
	public int compareTo(Drink another) {
		return getType().toString().compareTo(another.getType().toString());
	}

	public void setTeaSpoon(boolean teaSpoon) {
		this.teaSpoon = teaSpoon;
	}
}
