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

	private double strongRatio = 0.2;
	private double liquerRatio = 0.45;
	private double nonAlcRatio = 0.45;

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
		// while (true) {
		// // strong drinks
		// for (Drink strong : strongDrinks) {
		// Cocktail cocktail = new Cocktail();
		// strong.setAmount((int) (Math.random() * 2 + 1));
		// cocktail.addDrink(strong);
		// int cocktailSize = strong.getAmount()
		// * (int) (Math.random() * 2 + 3);
		// int check = 0;
		// while (cocktail.getAmount() < cocktailSize) {
		// int choice = (int) (Math.random() * 20);
		// switch (choice) {
		// case 0:
		// case 1:
		// case 2:
		// case 3:
		// case 10:
		// case 11:
		// case 12:
		// case 13:
		// if (liqueurs.size() > 0) {
		// Drink liqueur = liqueurs
		// .get((int) (Math.random() * liqueurs.size()));
		// liqueur.setAmount(strong.getAmount()
		// + (int) (Math.random() * 3 - 1));
		// cocktail.addDrink(liqueur);
		// } else
		// check++;
		// break;
		// case 4:
		// case 14:
		// if (otherDrinks.size() > 0) {
		// Drink other = otherDrinks
		// .get((int) (Math.random() * otherDrinks
		// .size()));
		// int a = strong.getAmount()
		// + (int) (Math.random() * 5 - 2);
		// if (a <= 0)
		// a = 1;
		// other.setAmount(a);
		// cocktail.addDrink(other);
		// } else
		// check++;
		// break;
		// case 5:
		// case 6:
		// case 7:
		// case 8:
		// case 9:
		// case 15:
		// case 16:
		// case 17:
		// case 18:
		// if (nonAlcDrinks.size() > 0) {
		// Drink nonAlc = nonAlcDrinks.get((int) (Math
		// .random() * nonAlcDrinks.size()));
		// int amount = strong.getAmount()
		// + (int) (Math.random() * 7 - 3);
		// if (amount <= 0)
		// amount = 4;
		// nonAlc.setAmount(amount);
		// cocktail.addDrink(nonAlc);
		// } else
		// check++;
		// break;
		// case 19:
		// Drink strong2 = strongDrinks
		// .get((int) (Math.random() * strongDrinks.size()));
		// strong2.setAmount(strong.getAmount());
		// cocktail.addDrink(strong2);
		// }
		//
		// if (check >= 5)
		// break;
		// }
		// cocktails.add(cocktail);
		// }
		// if (cocktails.size() > size / 2 || cocktails.size() >= 10)
		// return;
		// // mixing withouth strong drinks
		// for (Drink liquer : liqueurs) {
		// Cocktail cocktail = new Cocktail();
		// liquer.setAmount((int) (Math.random() * 2 + 2));
		// cocktail.addDrink(liquer);
		// int cocktailSize = liquer.getAmount()
		// * (int) (Math.random() * 2 + 4);
		// int check = 0;
		// while (cocktail.getAmount() < cocktailSize) {
		// int choice = (int) (Math.random() * 10);
		// switch (choice) {
		// case 0:
		// case 1:
		// case 2:
		// case 3:
		// if (liqueurs.size() > 0) {
		// Drink liq = liqueurs
		// .get((int) (Math.random() * liqueurs.size()));
		// if (liq.getName()
		// .equalsIgnoreCase(liquer.getName()))
		// continue;
		// liq.setAmount(liquer.getAmount()
		// + (int) (Math.random() * 3 - 1));
		// cocktail.addDrink(liq);
		// } else
		// check++;
		// break;
		// case 4:
		// if (otherDrinks.size() > 0) {
		// Drink other = otherDrinks
		// .get((int) (Math.random() * otherDrinks
		// .size()));
		// other.setAmount(liquer.getAmount()
		// + (int) (Math.random() * 3 - 1));
		// cocktail.addDrink(other);
		// } else
		// check++;
		// break;
		// case 5:
		// case 6:
		// case 7:
		// case 8:
		// case 9:
		// if (nonAlcDrinks.size() > 0) {
		// Drink nonAlc = nonAlcDrinks.get((int) (Math
		// .random() * nonAlcDrinks.size()));
		// int amount = liquer.getAmount()
		// + (int) (Math.random() * 5 - 2);
		// if (amount <= 0)
		// amount = 4;
		// nonAlc.setAmount(amount);
		// cocktail.addDrink(nonAlc);
		// } else
		// check++;
		// break;
		// }
		//
		// if (check >= 5)
		// break;
		// }
		// cocktails.add(cocktail);
		// }
		// if (cocktails.size() > size / 2 || cocktails.size() >= 10)
		// return;
		// // mixing withouth liquers and strong drinks
		// for (Drink otherDrink : otherDrinks) {
		// Cocktail cocktail = new Cocktail();
		// otherDrink.setAmount((int) (Math.random() * 2 + 2));
		// cocktail.addDrink(otherDrink);
		// int cocktailSize = otherDrink.getAmount()
		// * (int) (Math.random() * 2 + 5);
		// int check = 0;
		// while (cocktail.getAmount() < cocktailSize) {
		// int choice = (int) (Math.random() * 10);
		// switch (choice) {
		// case 1:
		// case 2:
		// case 3:
		// if (otherDrinks.size() > 0) {
		// Drink other = otherDrinks
		// .get((int) (Math.random() * otherDrinks
		// .size()));
		// if (other.getName().equalsIgnoreCase(
		// otherDrink.getName()))
		// continue;
		// other.setAmount(otherDrink.getAmount()
		// + (int) (Math.random() * 3 - 1));
		// cocktail.addDrink(other);
		// } else
		// check++;
		// break;
		// case 5:
		// case 6:
		// case 7:
		// case 8:
		// case 9:
		// case 0:
		// case 4:
		// if (nonAlcDrinks.size() > 0) {
		// Drink nonAlc = nonAlcDrinks.get((int) (Math
		// .random() * nonAlcDrinks.size()));
		// int amount = otherDrink.getAmount()
		// + (int) (Math.random() * 7 - 3);
		// if (amount <= 0)
		// amount = 4;
		// nonAlc.setAmount(amount);
		// cocktail.addDrink(nonAlc);
		// } else
		// check++;
		// break;
		// }
		//
		// if (check >= 5)
		// break;
		// }
		// cocktails.add(cocktail);
		// }
		// if (cocktails.size() > size / 2 || cocktails.size() >= 10)
		// return;
		// // mixing only non alc
		// for (Drink nonAlc : nonAlcDrinks) {
		// Cocktail cocktail = new Cocktail();
		// nonAlc.setAmount((int) (Math.random() * 2 + 2));
		// cocktail.addDrink(nonAlc);
		// int cocktailSize = nonAlc.getAmount()
		// * (int) (Math.random() * 2 + 6);
		// int check = 0;
		// while (cocktail.getAmount() < cocktailSize) {
		// if (nonAlcDrinks.size() > 0) {
		// Drink nonAlcdr = nonAlcDrinks
		// .get((int) (Math.random() * nonAlcDrinks.size()));
		// nonAlcdr.setAmount(nonAlc.getAmount() / 2);
		// cocktail.addDrink(nonAlcdr);
		// } else
		// check++;
		// if (check >= 5)
		// break;
		// }
		// cocktails.add(cocktail);
		// }
		// if (cocktails.size() > size / 2 || cocktails.size() >= 10)
		// return;
		// }

	}

	public void printCocktails() {
		for (Cocktail cocktail : cocktails)
			System.out.println(cocktail);
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
			if (random < liquerRatio)
				nextType = DrinkType.B_LIQUEUR;
			else if (random > 1 - nonAlcRatio)
				nextType = DrinkType.D_NON_ALC;
			else
				nextType = DrinkType.C_OTHER;
			break;
		case B_LIQUEUR:
			random = Math.random();
			if (random < strongRatio)
				nextType = DrinkType.A_STRONG;
			else if (random > 1 - liquerRatio + strongRatio)
				nextType = DrinkType.B_LIQUEUR;
			else if (random > nonAlcRatio)
				nextType = DrinkType.D_NON_ALC;
			else
				nextType = DrinkType.C_OTHER;
			break;
		case C_OTHER:
			random = Math.random();
			if (random < strongRatio - 0.05)
				nextType = DrinkType.A_STRONG;
			else if (random > 1 - liquerRatio + strongRatio - 0.05)
				nextType = DrinkType.B_LIQUEUR;
			else if (random > nonAlcRatio - 0.1)
				nextType = DrinkType.D_NON_ALC;
			else
				nextType = DrinkType.C_OTHER;
			break;
		case D_NON_ALC:
			random = Math.random();
			if (random < strongRatio + 0.05)
				nextType = DrinkType.A_STRONG;
			else if (random > 1 - liquerRatio + strongRatio)
				nextType = DrinkType.B_LIQUEUR;
			else if (random > nonAlcRatio)
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
				strongRatio = 0;
				break;
			case B_LIQUEUR:
				liquerRatio = 0;
				break;
			case D_NON_ALC:
				nonAlcRatio = 0;
				break;
			default:
				strongRatio = 0.25;
				liquerRatio = 0.35;
				nonAlcRatio = 0.40;
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

}
