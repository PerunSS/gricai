package com.cerSprikRu.CocktailMixer.model.drink;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cocktail implements Serializable, Comparable<Cocktail> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Drink> drinks = new ArrayList<Drink>();
	private int amount = 0;
	private String name;
	private String text;
	private String description;

	private static final int SHAKE = 1;
	private static final int NORMAL = 0;

	private int state;

	public void addDrink(Drink drink) {
		Drink newDrink = new Drink();
		newDrink.setName(drink.getName());
		newDrink.setAmount(drink.getAmount());
		newDrink.setType(drink.getType());
		newDrink.setTeaSpoon(drink.isTeaSpoon());
		newDrink.setAlcPercent(drink.getAlcPercent());
		newDrink.setCarbonated(drink.isCarbonated());
		newDrink.setWeight(drink.getWeight());
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
		if (str != null && str.startsWith("null"))
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

	public String getDescription() {
		return description;
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
		description = "";
		Collections.sort(drinks);
		double alcAmount = 0.0;
		int totalSize = 0;
		for (Drink drink : drinks) {
			alcAmount += (drink.getAlcPercent() * drink.getAmount());
			totalSize += drink.getAmount();
		}
		boolean strong = false;
		if (alcAmount / totalSize > 0.25) {
			strong = true;
		}
		double random = Math.random();
		double randomStir = Math.random();
		boolean shot = false;
		double stir = 0.4;
		if (drinks.size() > 5)
			stir = 0.8;
		boolean canSmall = drinks.size() <= 4;
		if (strong && Math.random()<.6) {
			if (RandomOptions.getInstance().isShotDrinks() && random > 0.45) {
				shot = true;
			}
			state = SHAKE;
		} else {

			if (random > .6) {
				state = SHAKE;
			} else {
				state = NORMAL;
			}
		}
		text = "Ingredients:\n";
		if (state == SHAKE) {
			int cubes = (int) (Math.random() * 5 + 5);
			text += cubes + " - " + (cubes + (int) (Math.random() * 3) + 1)
					+ " ice cubes\n";
		}

		List<Drink> carbonDrinks = new ArrayList<Drink>();
		boolean hasFruit = false;
		for (Drink drink : drinks) {
			if (state == SHAKE) {
				if (drink.isCarbonated()) {
					carbonDrinks.add(drink);
				}
			}
			if (drink.getName().equalsIgnoreCase("fruit"))
				hasFruit = true;
			text += drink + "\n";
		}
		if (state == SHAKE && carbonDrinks.size() < drinks.size() - 2) {
			description = "Put all ingredients ";
			if (carbonDrinks.size() > 0) {
				description += "except";
				for (Drink drink : carbonDrinks) {
					description += " "+drink.getName() + ",";
				}
				description = description
						.substring(0, description.length() - 1);
				description += " ";
			}
			description += "into shaker and shake";
			double shakeRandom = Math.random();
			if (shakeRandom < 0.5)
				description += " well";
			else if (shakeRandom > 0.65)
				description += " until a frost forms";
			description += ".";
			description += " Distribute in glasses";
			if (carbonDrinks.size() > 0) {
				description += " and then add";
				for (Drink drink : carbonDrinks) {
					description += " "+drink.getName() + ",";
				}
				description = description
						.substring(0, description.length() - 1);
			}
			description += ".";
			if (hasFruit){
				description += " Decorate with fruit.\n";
			}
			if (shot && !hasFruit) {
				description += " Drink as a shot.";
			}
		} else {
			int maxWeight = -2;
			int minWeight = 100;
			Map<Integer, List<Drink>> allDrinksByWeight = new HashMap<Integer, List<Drink>>();
			for (Drink drink : drinks) {
				List<Drink> weightDrinks = allDrinksByWeight.get(drink
						.getWeight());
				if (weightDrinks == null)
					weightDrinks = new ArrayList<Drink>();
				weightDrinks.add(drink);
				if (drink.getWeight() < minWeight)
					minWeight = drink.getWeight();
				if (drink.getWeight() > maxWeight)
					maxWeight = drink.getWeight();
				allDrinksByWeight.put(drink.getWeight(), weightDrinks);
			}
			boolean hasSugar = false;
			if (minWeight < 0) {
				hasSugar = true;
				minWeight = 0;
			}
			boolean isStirred = false;
			if (stir > randomStir) {
				description = "Put all ingredients into glass and stir.";
				isStirred = true;
			}
			if (!isStirred) {
				description = "Put ingredients";
				boolean ice = false;
				if(Math.random()>.55){
					description+= " over";
					if(Math.random()>.55)
						description+=" crushed";
					description+=" ice";
					ice = true;
				}
				description+=" into";
				if(canSmall && Math.random()<.6)
					description+=" small";
				description+=" glass";
				description+= " in next order - ";
	
				boolean sugarAdded = false;
				if (Math.random() > .5 && hasSugar) {
					description += " sugar,";
					sugarAdded = true;
				}
				for (int i = maxWeight; i >= minWeight; i--) {
					List<Drink> wDrinks = allDrinksByWeight.get(i);
					if (wDrinks != null){
						for (Drink drink : wDrinks) {
							description += " "+drink.getName() + ",";
						}
					}
				}
				description = description
					.substring(0, description.length() - 1);
				if(!ice && Math.random()<.5)
					description+=" and add few ice cubes";
				description+=".";
				if (!sugarAdded && hasSugar) {
					description += " Sprinkle sugar over it.";
				}
				
			}
			if (hasFruit)
				description += " Decorate with fruit.";

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
