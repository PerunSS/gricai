package com.cerSprikRu.CocktailMixer.model.drink;

public  class Drink {

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
