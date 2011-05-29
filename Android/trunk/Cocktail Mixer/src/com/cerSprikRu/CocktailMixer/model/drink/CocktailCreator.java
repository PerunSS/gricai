package com.cerSprikRu.CocktailMixer.model.drink;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CocktailCreator {

	private List<Drink> strongDrinks = new ArrayList<Drink>();
	private List<Drink> liqueurs = new ArrayList<Drink>();
	private List<Drink> flavoredLiqueurs = new ArrayList<Drink>();
	private List<Drink> otherDrinks = new ArrayList<Drink>();
	private List<Drink> nonAlcDrinks = new ArrayList<Drink>();
	private List<Drink> additions = new ArrayList<Drink>();
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
		case C_FLAVORED_LIQUEUR:
			flavoredLiqueurs.add(drink);
			break;
		case D_OTHER:
			otherDrinks.add(drink);
			break;
		case E_NON_ALC:
			nonAlcDrinks.add(drink);
			break;
		case F_ADDITIONS:
			additions.add(drink);
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
		flavoredLiqueurs.clear();
		otherDrinks.clear();
		nonAlcDrinks.clear();
		additions.clear();
		cocktails.clear();
		size = 0;

	}

	public static CocktailCreator getInstance() {
		return instance;
	}

	public void createCocktails(boolean fullRandom) {
		cocktails.clear();
		if (strongDrinks.isEmpty())
			RandomOptions.getInstance().setStrongRatio(0);
		if (liqueurs.isEmpty())
			RandomOptions.getInstance().setLiquerRatio(0);
		if (flavoredLiqueurs.isEmpty())
			RandomOptions.getInstance().setFlavouredLiquerRatio(0);
		if (otherDrinks.isEmpty())
			RandomOptions.getInstance().setOtherAlcRatio(0);
		if (nonAlcDrinks.isEmpty())
			RandomOptions.getInstance().setNonAlcRatio(0);
		if (additions.isEmpty())
			RandomOptions.getInstance().setOtherRatio(0);
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
		// double random;
		DrinkType nextType = getNextType(drink);
		Drink nextDrink = null;
		int modificator = 1;
		/*
		 * switch (drink.getType()) { case A_STRONG: random = Math.random(); if
		 * (random < RandomOptions.getInstance().getLiquerRatio()) nextType =
		 * DrinkType.B_LIQUEUR; else if (random > 1 -
		 * RandomOptions.getInstance().getNonAlcRatio()) nextType =
		 * DrinkType.E_NON_ALC; else nextType = DrinkType.D_OTHER; break; case
		 * B_LIQUEUR: random = Math.random(); if (random <
		 * RandomOptions.getInstance().getStrongRatio()) nextType =
		 * DrinkType.A_STRONG; else if (random > 1 -
		 * RandomOptions.getInstance().getLiquerRatio() +
		 * RandomOptions.getInstance().getStrongRatio()) nextType =
		 * DrinkType.B_LIQUEUR; else if (random >
		 * RandomOptions.getInstance().getNonAlcRatio()) nextType =
		 * DrinkType.E_NON_ALC; else nextType = DrinkType.D_OTHER; break; case
		 * D_OTHER: random = Math.random(); if (random <
		 * RandomOptions.getInstance().getStrongRatio() - 0.05) nextType =
		 * DrinkType.A_STRONG; else if (random > 1 -
		 * RandomOptions.getInstance().getLiquerRatio() +
		 * RandomOptions.getInstance().getStrongRatio() - 0.05) nextType =
		 * DrinkType.B_LIQUEUR; else if (random >
		 * RandomOptions.getInstance().getNonAlcRatio() - 0.1) nextType =
		 * DrinkType.E_NON_ALC; else nextType = DrinkType.D_OTHER; break; case
		 * E_NON_ALC: random = Math.random(); if (random <
		 * RandomOptions.getInstance().getStrongRatio() + 0.05) nextType =
		 * DrinkType.A_STRONG; else if (random > 1 -
		 * RandomOptions.getInstance().getLiquerRatio() +
		 * RandomOptions.getInstance().getStrongRatio()) nextType =
		 * DrinkType.B_LIQUEUR; else if (random >
		 * RandomOptions.getInstance().getNonAlcRatio()) nextType =
		 * DrinkType.E_NON_ALC; else nextType = DrinkType.D_OTHER; break; }
		 */
		nextDrink = getNextDrink(nextType);
		/*
		 * switch (nextType) { case A_STRONG: if (strongDrinks.size() > 0)
		 * nextDrink = strongDrinks .get((int) (Math.random() *
		 * strongDrinks.size())); break; case B_LIQUEUR: if (liqueurs.size() >
		 * 0) nextDrink = liqueurs .get((int) (Math.random() *
		 * liqueurs.size())); modificator = 2; break; case D_OTHER: if
		 * (otherDrinks.size() > 0) nextDrink = otherDrinks.get((int)
		 * (Math.random() * otherDrinks .size())); break; case E_NON_ALC: if
		 * (nonAlcDrinks.size() > 0) nextDrink = nonAlcDrinks .get((int)
		 * (Math.random() * nonAlcDrinks.size())); modificator = 2; break;
		 * 
		 * }
		 */
		if (nextType == DrinkType.B_LIQUEUR || nextType == DrinkType.E_NON_ALC)
			modificator = 2;
		if (nextDrink != null) {
			nextDrink.setAmount((int) (Math.random() * drink.getAmount())
					+ modificator);
			if (nextDrink.isTeaSpoon())
				nextDrink.setAmount((int) (Math.random() * 2) + 1);
		} /*
		 * else { switch (nextType) { case A_STRONG:
		 * RandomOptions.getInstance().setStrongRatio(0); break; case B_LIQUEUR:
		 * RandomOptions.getInstance().setLiquerRatio(0); break; case E_NON_ALC:
		 * RandomOptions.getInstance().setNonAlcRatio(0); break; default:
		 * RandomOptions.getInstance().setStrongRatio(0.25);
		 * RandomOptions.getInstance().setLiquerRatio(0.35);
		 * RandomOptions.getInstance().setNonAlcRatio(0.40); } }
		 */

		return nextDrink;
	}

	private Drink getNextDrink(DrinkType nextType) {
		List<Drink> nextDrinks = null;
		switch (nextType) {
		case A_STRONG:
			nextDrinks = new ArrayList<Drink>(strongDrinks);
			break;
		case B_LIQUEUR:
			nextDrinks = new ArrayList<Drink>(liqueurs);
			break;
		case C_FLAVORED_LIQUEUR:
			nextDrinks = new ArrayList<Drink>(flavoredLiqueurs);
			break;
		case D_OTHER:
			nextDrinks = new ArrayList<Drink>(otherDrinks);
			break;
		case E_NON_ALC:
			nextDrinks = new ArrayList<Drink>(nonAlcDrinks);
			break;
		case F_ADDITIONS:
			nextDrinks = new ArrayList<Drink>(additions);
			break;
		}
		if (nextDrinks != null) {
			Drink nextDrink = null;
			while (nextDrink == null) {
				double random = 75 * Math.random();
				List<Drink> possibleDrinks = new ArrayList<Drink>();
				for (Drink drink : nextDrinks) {
					if (drink.getPercent() > random) {
						possibleDrinks.add(drink);
					}
				}
				if (possibleDrinks.size() > 0) {
					nextDrink = possibleDrinks
							.get((int) (Math.random() * possibleDrinks.size()));
					if (nextDrink.getSubDrinks() != null
							&& nextDrink.getSubDrinks().size() > 0) {
						possibleDrinks.clear();
						for (Drink subDrink : nextDrink.getSubDrinks()) {
							if (subDrink.isPresent())
								possibleDrinks.add(subDrink);
						}
						if (possibleDrinks.size() > 0) {
							random = 50 * Math.random();
							List<Drink> possibleSubDrinks = new ArrayList<Drink>();
							for (Drink subDrink : possibleDrinks) {
								if (subDrink.getPercent() > random)
									possibleSubDrinks.add(subDrink);
							}
							if(possibleSubDrinks.size()>0){
								possibleSubDrinks.add(nextDrink);
								nextDrink = possibleSubDrinks.get((int)(possibleSubDrinks.size()*Math.random()));
							}
						}
					}
				}
			}
			return nextDrink;
		}
		return null;
	}

	private DrinkType getNextType(Drink drink) {
		List<DrinkType> types = RandomOptions.getInstance().getTypes();
		Map<DrinkType, Double> ratios = new HashMap<DrinkType, Double>();
		for (DrinkType type : types) {
			ratios.put(type, RandomOptions.getInstance().getRatio(type));
		}
		DrinkType returnType = null;
		while (returnType == null) {
			double random = 100 * Math.random();
			List<DrinkType> possibleTypes = new ArrayList<DrinkType>();
			for (Map.Entry<DrinkType, Double> entry : ratios.entrySet()) {
				if (entry.getValue() > random)
					possibleTypes.add(entry.getKey());
			}
			if (possibleTypes.size() > 0) {
				returnType = possibleTypes
						.get((int) (Math.random() * possibleTypes.size()));
			}
		}
		return returnType;
	}

	private Drink getRandomDrink() {
		List<Drink> allDrinks = new ArrayList<Drink>();
		allDrinks.addAll(strongDrinks);
		allDrinks.addAll(liqueurs);
		allDrinks.addAll(flavoredLiqueurs);
		allDrinks.addAll(otherDrinks);
		allDrinks.addAll(nonAlcDrinks);
		allDrinks.addAll(additions);
		Drink nextDrink = allDrinks.get((int)(Math.random()*allDrinks.size()));
		nextDrink.setAmount(1+(int)(Math.random()*4));
		return nextDrink;
	}

	public boolean canMix() {
		if (liqueurs.isEmpty() && otherDrinks.isEmpty()
				&& nonAlcDrinks.isEmpty())
			return false;
		return true;
	}

}
