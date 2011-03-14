package com.cerSprikRu.CocktailMixer.model.drink;

import java.io.Serializable;

public  class Drink implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int amount;
	private String name;
	private DrinkType type;

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
		return name+" "+amount+" messure(s)";
	}

	public void incAmount(int amount2) {
		amount+=amount2;
	}
}
