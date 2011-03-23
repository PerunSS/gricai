package com.cerSprikRu.RNBRumorsNFacts.favorites;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import android.content.Context;

import com.cerSprikRu.RNBRumorsNFacts.fact.Fact;

public class FavoritesManager {

	private Context context;
	private Set<Integer> favorites = new TreeSet<Integer>();
	
	public FavoritesManager(Context context) {
		
		this.context = context;
		try {
			FileInputStream read = context.openFileInput("favorites");
			Scanner sc = new Scanner(read);
			while(sc.hasNext()){
				favorites.add(Integer.parseInt(sc.nextLine()));
			}
			sc.close();
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
	}
	
	public void update(){
		try {
			FileOutputStream write = context.openFileOutput("favorites", Context.MODE_PRIVATE);
			for(int value:favorites){
				try {
					write.write((value+"\r\n").getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			write.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void togleFavorite(Fact fact){
		if(fact.isFavorite()){
			favorites.add(fact.getId());
		}else{
			favorites.remove(fact.getId());
		}
		update();
	}
	
	public Set<Integer> getFavorites(){
		return favorites;
	}
}
