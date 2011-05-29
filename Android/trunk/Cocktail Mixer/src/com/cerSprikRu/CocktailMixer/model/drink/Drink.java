package com.cerSprikRu.CocktailMixer.model.drink;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Drink implements Serializable, Comparable<Drink> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int amount;
	private String name;
	private List<Drink> subDrinks;
	private DrinkType type;
	private boolean teaSpoon;
	private double alcPercent = 0.0;
	private boolean isPresent;
	private double percent;

	public Drink() {
		teaSpoon = false;
		subDrinks = new ArrayList<Drink>();
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
	
	public List<Drink> getSubDrinks(){
		return subDrinks;
	}
	
	public void addSubDrink(Drink... subDrinks){
		for(Drink subDrink:subDrinks){
			subDrink.setType(getType());
			subDrink.setAlcPercent(getAlcPercent());
			subDrink.setTeaSpoon(isTeaSpoon());
			this.subDrinks.add(subDrink);
		}
	}
	
	public void setType(String category){
		if(category.equalsIgnoreCase("strong"))
			setType(DrinkType.A_STRONG);
		else if(category.equalsIgnoreCase("liqueur"))
			setType(DrinkType.B_LIQUEUR);
		else if(category.equalsIgnoreCase("flavored liqueur"))
			setType(DrinkType.C_FLAVORED_LIQUEUR);
		else if(category.equalsIgnoreCase("other alcoholic"))
			setType(DrinkType.D_OTHER);
		else if(category.equalsIgnoreCase("non-alcoholic"))
			setType(DrinkType.E_NON_ALC);
		else{
			setType(DrinkType.F_ADDITIONS);
			setTeaSpoon(true);
		}
			
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
		if(name.equalsIgnoreCase("fruit"))
			mesure = "piece";
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

	public void setAlcPercent(double alcAmount) {
		this.alcPercent = alcAmount;
	}

	public double getAlcPercent() {
		return alcPercent;
	}

	public void setPresent(boolean isPresent) {
		this.isPresent = isPresent;
	}

	public boolean isPresent() {
		return isPresent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}

	public double getPercent() {
		return percent;
	}
}
