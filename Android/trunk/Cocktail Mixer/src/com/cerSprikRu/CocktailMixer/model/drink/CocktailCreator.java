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
	
	private CocktailCreator(){
		
	}
	
	public void addDrink(Drink drink){
		switch(drink.getType()){
		case STRONG:
			strongDrinks.add(drink);
			break;
		case LIQUEUR:
			liqueurs.add(drink);
			break;
		case OTHER:
			otherDrinks.add(drink);
			break;
		case NON_ALC:
			nonAlcDrinks.add(drink);
			break;
		}
		size++;
	}
	
	public int size(){
		return size;
	}
	
	public void reset(){
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
	
	public void createCocktails(){
		cocktails.clear();
		while(true){
			//strong drinks
			for(Drink strong:strongDrinks){
				Cocktail cocktail = new Cocktail();
				strong.setAmount((int)(Math.random()*2+1));
				cocktail.addDrink(strong);
				int cocktailSize = strong.getAmount()*(int)(Math.random()*2+3);
				int check = 0;
				while(cocktail.getAmount()<cocktailSize){
					int choice = (int)(Math.random()*10);
					switch(choice){
					case 0: case 1: case 2: case 3:
						if(liqueurs.size()>0){
							Drink liqueur = liqueurs.get((int)(Math.random()*liqueurs.size()));
							liqueur.setAmount(strong.getAmount()+(int)(Math.random()*3-1));
							cocktail.addDrink(liqueur);
						}
						else check++;
						break;
					case 4:
						if(otherDrinks.size()>0){
							Drink other = otherDrinks.get((int)(Math.random()*otherDrinks.size()));
							int a = strong.getAmount()+(int)(Math.random()*5-2);
							if(a<=0)
								a=1;
							other.setAmount(a);
							cocktail.addDrink(other);
						}else check++;
						break;
					case 5: case 6: case 7: case 8: case 9:
						if(nonAlcDrinks.size()>0){
							Drink nonAlc = nonAlcDrinks.get((int)(Math.random()*nonAlcDrinks.size()));
							int amount = strong.getAmount()+(int)(Math.random()*7-3);
							if(amount<=0)
								amount = 4;
							nonAlc.setAmount(amount);
							cocktail.addDrink(nonAlc);
						}else check++;
						break;
					}
					
					if(check >=5)
						break;
				}
				System.out.println("added strong cocktail: "+cocktail);
				cocktails.add(cocktail);
			}
			if(cocktails.size()>size/2)
				return;
			//mixing withouth strong drinks
			for(Drink liquer:liqueurs){
				Cocktail cocktail = new Cocktail();
				liquer.setAmount((int)(Math.random()*2+2));
				cocktail.addDrink(liquer);
				int cocktailSize = liquer.getAmount()*(int)(Math.random()*2+4);
				int check = 0;
				while(cocktail.getAmount()<cocktailSize){
					int choice = (int)(Math.random()*10);
					switch(choice){
					case 0: case 1: case 2: case 3:
						if(liqueurs.size()>0){
							Drink liq = liqueurs.get((int)(Math.random()*liqueurs.size()));
							if(liq.getName().equalsIgnoreCase(liquer.getName()))
								continue;
							liq.setAmount(liquer.getAmount()+(int)(Math.random()*3-1));
							cocktail.addDrink(liq);
						}
						else check++;
						break;
					case 4:
						if(otherDrinks.size()>0){
							Drink other = otherDrinks.get((int)(Math.random()*otherDrinks.size()));
							other.setAmount(liquer.getAmount()+(int)(Math.random()*3-1));
							cocktail.addDrink(other);
						}else check++;
						break;
					case 5: case 6: case 7: case 8: case 9:
						if(nonAlcDrinks.size()>0){
							Drink nonAlc = nonAlcDrinks.get((int)(Math.random()*nonAlcDrinks.size()));
							int amount = liquer.getAmount()+(int)(Math.random()*5-2);
							if(amount<=0)
								amount = 4;
							nonAlc.setAmount(amount);
							cocktail.addDrink(nonAlc);
						}else check++;
						break;
					}
					
					if(check >=5)
						break;
				}
				System.out.println("added liqueur cocktail: "+cocktail);
				cocktails.add(cocktail);
			}
			if(cocktails.size()>size/2)
				return;
			//mixing withouth liquers and strong drinks
			for(Drink otherDrink:otherDrinks){
				Cocktail cocktail = new Cocktail();
				otherDrink.setAmount((int)(Math.random()*2+2));
				cocktail.addDrink(otherDrink);
				int cocktailSize = otherDrink.getAmount()*(int)(Math.random()*2+5);
				int check = 0;
				while(cocktail.getAmount()<cocktailSize){
					int choice = (int)(Math.random()*10);
					switch(choice){
					case 1: case 2: case 3:
						if(otherDrinks.size()>0){
							Drink other = otherDrinks.get((int)(Math.random()*otherDrinks.size()));
							if(other.getName().equalsIgnoreCase(otherDrink.getName()))
								continue;
							other.setAmount(otherDrink.getAmount()+(int)(Math.random()*3-1));
							cocktail.addDrink(other);
						}else check++;
						break;
					case 5: case 6: case 7: case 8: case 9: case 0: case 4:
						if(nonAlcDrinks.size()>0){
							Drink nonAlc = nonAlcDrinks.get((int)(Math.random()*nonAlcDrinks.size()));
							int amount = otherDrink.getAmount()+(int)(Math.random()*7-3);
							if(amount<=0)
								amount = 4;
							nonAlc.setAmount(amount);
							cocktail.addDrink(nonAlc);
						}else check++;
						break;
					}
					
					if(check >=5)
						break;
				}
				System.out.println("added other cocktail: "+cocktail);
				cocktails.add(cocktail);
			}
			if(cocktails.size()>size/2)
				return;
			//mixing only non alc
			for(Drink nonAlc:nonAlcDrinks){
				Cocktail cocktail = new Cocktail();
				nonAlc.setAmount((int)(Math.random()*2+2));
				cocktail.addDrink(nonAlc);
				int cocktailSize = nonAlc.getAmount()*(int)(Math.random()*2+6);
				int check = 0;
				while(cocktail.getAmount()<cocktailSize){
					if(nonAlcDrinks.size()>0){
							Drink nonAlcdr = nonAlcDrinks.get((int)(Math.random()*nonAlcDrinks.size()));
							nonAlcdr.setAmount(nonAlc.getAmount()/2);
							cocktail.addDrink(nonAlcdr);
						}else check++;
					if(check >=5)
						break;
				}
				System.out.println("added non alc cocktail: "+cocktail);
				cocktails.add(cocktail);
			}
			if(cocktails.size()>size/2)
				return;
		}
		
	}
	
	public void printCocktails(){
		for(Cocktail cocktail:cocktails)
			System.out.println(cocktail);
	}

	public List<Cocktail> getCocktails() {
		return cocktails;
	}
	
}
