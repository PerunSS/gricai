package com.cerSprikRu.CocktailMixer.model.drink;

import java.util.ArrayList;
import java.util.List;

public class RandomOptions {

	private static RandomOptions instance = new RandomOptions();

	private double strongRatio = 0.2;
	private double liquerRatio = 0.2;
	private double flavouredLiquerRatio = 0.2;
	private double nonAlcRatio = 0.2;
	private double otherAlcRatio = 0.2;
	private double otherRatio = 0.4;

	private boolean shortDrinks = true;
	private boolean shotDrinks = true;

	private List<DrinkType> types;

	public static RandomOptions getInstance() {
		return instance;
	}

	private RandomOptions() {
		types = new ArrayList<DrinkType>();
		for (DrinkType type : DrinkType.values()) {
			types.add(type);
		}
	}

	public double getStrongRatio() {
		return strongRatio;
	}

	public void setStrongRatio(double strongRatio) {
		this.strongRatio = strongRatio;
		if (strongRatio == 0) {
			types.remove(DrinkType.A_STRONG);
		}
	}

	public double getLiquerRatio() {
		return liquerRatio;
	}

	public void setLiquerRatio(double liquerRatio) {
		this.liquerRatio = liquerRatio;
		if (liquerRatio == 0) {
			types.remove(DrinkType.B_LIQUEUR);
		}
	}

	public double getNonAlcRatio() {
		return nonAlcRatio;
	}

	public void setNonAlcRatio(double nonAlcRatio) {
		this.nonAlcRatio = nonAlcRatio;
		if (nonAlcRatio == 0) {
			types.remove(DrinkType.E_NON_ALC);
		}
	}

	public boolean isShortDrinks() {
		return shortDrinks;
	}

	public void setShortDrinks(boolean shortDrinks) {
		this.shortDrinks = shortDrinks;
	}

	public boolean isShotDrinks() {
		return shotDrinks;
	}

	public void setShotDrinks(boolean shotDrinks) {
		// if(shotDrinks){
		// //TODO fix
		// nonAlcRatio*=0.5;
		// strongRatio = 1 - nonAlcRatio - liquerRatio;
		// }
		this.shotDrinks = shotDrinks;
	}

	public double getFlavouredLiquerRatio() {
		return flavouredLiquerRatio;
	}

	public void setFlavouredLiquerRatio(double flavouredLiquerRatio) {
		this.flavouredLiquerRatio = flavouredLiquerRatio;
		if (flavouredLiquerRatio == 0) {
			types.remove(DrinkType.C_FLAVORED_LIQUEUR);
		}
	}

	public double getOtherAlcRatio() {
		return otherAlcRatio;
	}

	public void setOtherAlcRatio(double otherAlcRatio) {
		this.otherAlcRatio = otherAlcRatio;
		if (otherAlcRatio == 0) {
			types.remove(DrinkType.D_OTHER);
		}
	}

	public double getOtherRatio() {
		return otherRatio;
	}

	public void setOtherRatio(double otherRatio) {
		this.otherRatio = otherRatio;
		if (otherRatio == 0) {
			types.remove(DrinkType.F_ADDITIONS);
		}
	}

	public void setRatio(double percent, String categoryName) {
		if (categoryName.equalsIgnoreCase("strong"))
			strongRatio = percent;
		else if (categoryName.equalsIgnoreCase("liqueur"))
			liquerRatio = percent;
		else if (categoryName.equalsIgnoreCase("flavored liqueur"))
			flavouredLiquerRatio = percent;
		else if (categoryName.equalsIgnoreCase("other alcoholic"))
			otherAlcRatio = percent;
		else if (categoryName.equalsIgnoreCase("non-alcoholic"))
			nonAlcRatio = percent;
		else {
			otherRatio = percent;
		}
	}

	public double getRatio(DrinkType type) {
		switch (type) {
		case A_STRONG:
			return strongRatio;
		case B_LIQUEUR:
			return liquerRatio;
		case C_FLAVORED_LIQUEUR:
			return flavouredLiquerRatio;
		case E_NON_ALC:
			return nonAlcRatio;
		case D_OTHER:
			return otherAlcRatio;
		case F_ADDITIONS:
			return otherRatio;
		default:
			return -1;
		}
	}

	public List<DrinkType> getTypes() {
		return types;
	}
}
