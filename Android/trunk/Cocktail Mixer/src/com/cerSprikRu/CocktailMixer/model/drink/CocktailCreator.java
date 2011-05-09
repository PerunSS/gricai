package com.cerSprikRu.CocktailMixer.model.drink;

import java.util.ArrayList;
import java.util.List;

public class CocktailCreator {

	private List<Drink> strongDrinks = new ArrayList<Drink>();
	private List<Drink> liqueurs = new ArrayList<Drink>();
	private List<Drink> otherDrinks = new ArrayList<Drink>();
	private List<Drink> nonAlcDrinks = new ArrayList<Drink>();
	private int size;

	private static CocktailCreator instance = new CocktailCreator();
	private List<Cocktail> cocktails = new ArrayList<Cocktail>();

	private CocktailCreator() {

	}

	public void addDrink(Drink drink) {
		switch (drink.getType()) {
		case A_STRONG:
			strongDrinks.add(drink);
			break;
		case B_LIQUEUR:
			liqueurs.add(drink);
			break;
		case C_OTHER:
			otherDrinks.add(drink);
			break;
		case D_NON_ALC:
			nonAlcDrinks.add(drink);
			break;
		}
		size++;
	}

	public int size() {
		return size;
	}

	public void reset() {
		strongDrinks.clear();
		liqueurs.clear();
		otherDrinks.clear();
		nonAlcDrinks.clear();
		cocktails.clear();
		size = 0;

	}

	public static CocktailCreator getInstance() {
		return instance;
	}

	public void createCocktails(boolean fullRandom) {
		cocktails.clear();
		int cocktailCount = (int) (Math.random() * size);
		if (cocktailCount < 5)
			cocktailCount = 5;
		for (int i = 0; i < cocktailCount; i++) {
			int cocktailSize = (int) (Math.random() * 10) + 5;
			Cocktail cocktail = new Cocktail();
			Drink startDrink = getNextDrink(null, true);
			if (startDrink == null)
				return;
			cocktail.addDrink(startDrink);
			while (cocktail.getAmount() < cocktailSize) {
				Drink nextDrink = getNextDrink(startDrink, fullRandom);
				if (nextDrink != null) {
					cocktail.addDrink(nextDrink);
					startDrink = nextDrink;
				}
			}
			cocktail.generateRecipe();
			cocktails.add(cocktail);
		}
	}

	public List<Cocktail> getCocktails() {
		return cocktails;
	}

	private Drink getNextDrink(Drink drink, boolean fullRandom) {
		Drink nextDrink = null;
		if (fullRandom) {
			nextDrink = getRandomDrink();
		} else {
			nextDrink = getDrink(drink);
		}
		return nextDrink;
	}

	private Drink getDrink(Drink drink) {
		double random;
		DrinkType nextType = DrinkType.D_NON_ALC;
		Drink nextDrink = null;
		int modificator = 1;
		switch (drink.getType()) {
		case A_STRONG:
			random = Math.random();
			if (random < RandomOptions.getInstance().getLiquerRatio())
				nextType = DrinkType.B_LIQUEUR;
			else if (random > 1 - RandomOptions.getInstance().getNonAlcRatio())
				nextType = DrinkType.D_NON_ALC;
			else
				nextType = DrinkType.C_OTHER;
			break;
		case B_LIQUEUR:
			random = Math.random();
			if (random < RandomOptions.getInstance().getStrongRatio())
				nextType = DrinkType.A_STRONG;
			else if (random > 1 - RandomOptions.getInstance().getLiquerRatio()
					+ RandomOptions.getInstance().getStrongRatio())
				nextType = DrinkType.B_LIQUEUR;
			else if (random > RandomOptions.getInstance().getNonAlcRatio())
				nextType = DrinkType.D_NON_ALC;
			else
				nextType = DrinkType.C_OTHER;
			break;
		case C_OTHER:
			random = Math.random();
			if (random < RandomOptions.getInstance().getStrongRatio() - 0.05)
				nextType = DrinkType.A_STRONG;
			else if (random > 1 - RandomOptions.getInstance().getLiquerRatio()
					+ RandomOptions.getInstance().getStrongRatio() - 0.05)
				nextType = DrinkType.B_LIQUEUR;
			else if (random > RandomOptions.getInstance().getNonAlcRatio() - 0.1)
				nextType = DrinkType.D_NON_ALC;
			else
				nextType = DrinkType.C_OTHER;
			break;
		case D_NON_ALC:
			random = Math.random();
			if (random < RandomOptions.getInstance().getStrongRatio() + 0.05)
				nextType = DrinkType.A_STRONG;
			else if (random > 1 - RandomOptions.getInstance().getLiquerRatio()
					+ RandomOptions.getInstance().getStrongRatio())
				nextType = DrinkType.B_LIQUEUR;
			else if (random > RandomOptions.getInstance().getNonAlcRatio())
				nextType = DrinkType.D_NON_ALC;
			else
				nextType = DrinkType.C_OTHER;
			break;
		}
		switch (nextType) {
		case A_STRONG:
			if (strongDrinks.size() > 0)
				nextDrink = strongDrinks
						.get((int) (Math.random() * strongDrinks.size()));
			break;
		case B_LIQUEUR:
			if (liqueurs.size() > 0)
				nextDrink = liqueurs
						.get((int) (Math.random() * liqueurs.size()));
			modificator = 2;
			break;
		case C_OTHER:
			if (otherDrinks.size() > 0)
				nextDrink = otherDrinks.get((int) (Math.random() * otherDrinks
						.size()));
			break;
		case D_NON_ALC:
			if (nonAlcDrinks.size() > 0)
				nextDrink = nonAlcDrinks
						.get((int) (Math.random() * nonAlcDrinks.size()));
			modificator = 2;
			break;

		}
		if (nextDrink != null) {
			nextDrink.setAmount((int) (Math.random() * drink.getAmount())
					+ modificator);
			if (nextDrink.isTeaSpoon())
				nextDrink.setAmount((int) (Math.random() * 2) + 1);
		} else {
			switch (nextType) {
			case A_STRONG:
				RandomOptions.getInstance().setStrongRatio(0);
				break;
			case B_LIQUEUR:
				RandomOptions.getInstance().setLiquerRatio(0);
				break;
			case D_NON_ALC:
				RandomOptions.getInstance().setNonAlcRatio(0);
				break;
			default:
				RandomOptions.getInstance().setStrongRatio(0.25);
				RandomOptions.getInstance().setLiquerRatio(0.35);
				RandomOptions.getInstance().setNonAlcRatio(0.40);
			}
		}

		return nextDrink;
	}

	private Drink getRandomDrink() {
		Drink nextDrink;
		int nextIndex = (int) (Math.random() * size);
		if (nextIndex >= strongDrinks.size()) {
			nextIndex -= strongDrinks.size();
			if (nextIndex >= liqueurs.size()) {
				nextIndex -= liqueurs.size();
				if (nextIndex >= otherDrinks.size()) {
					nextIndex -= otherDrinks.size();
					if (nextIndex >= nonAlcDrinks.size()) {
						return null;
					}
					nextDrink = nonAlcDrinks.get(nextIndex);
					nextDrink.setAmount((int) (Math.random() * 4) + 3);
				} else {
					nextDrink = otherDrinks.get(nextIndex);
					nextDrink.setAmount((int) (Math.random() * 3) + 2);
				}
			} else {
				nextDrink = liqueurs.get(nextIndex);
				nextDrink.setAmount((int) (Math.random() * 2) + 1);
			}
		} else {
			nextDrink = strongDrinks.get(nextIndex);
			nextDrink.setAmount((int) (Math.random() * 1) + 1);
		}
		return nextDrink;
	}

	public boolean canMix() {
		if (liqueurs.isEmpty() && otherDrinks.isEmpty()
				&& nonAlcDrinks.isEmpty())
			return false;
		return true;
	}

}
