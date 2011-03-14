package com.cerSprikRu.CocktailMixer.favorites;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;
import java.util.TreeSet;

import android.content.Context;

import com.cerSprikRu.CocktailMixer.model.drink.Cocktail;

public class FavoritesManager {

	private Context context;
	private Set<Cocktail> favorites = new TreeSet<Cocktail>();
	
	private static FavoritesManager instance;
	
	public static FavoritesManager getInstance(){
		return instance;
	}

	public FavoritesManager(Context context) {
		this.context = context;
		try {
			FileInputStream read = context.openFileInput("favorites");
			ObjectInputStream ois = new ObjectInputStream(read);
			while(true){
				try {
					Cocktail cocktail = (Cocktail) ois.readObject();
					favorites.add(cocktail);
				} catch (Exception e) {
					break;
				}
			}
			ois.close();
			read.close();
		} catch (FileNotFoundException e) {
			try {
				FileOutputStream write = context.openFileOutput("favorites", Context.MODE_PRIVATE);
				write.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e2) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		instance = this;
	}
	
	public void update(){
		try {
			FileOutputStream write = context.openFileOutput("favorites", Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(write);
			for(Cocktail cocktail:favorites){
				try {
					oos.writeObject(cocktail);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			oos.close();
			write.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void togleFavorite(Cocktail cocktail){
		System.out.println(cocktail);
		if(favorites.contains(cocktail))
			favorites.remove(cocktail);
		else
			favorites.add(cocktail);
		System.out.println(favorites.size());
		update();
	}
	
	public Set<Cocktail> getFavorites(){
		return favorites;
	}
}
