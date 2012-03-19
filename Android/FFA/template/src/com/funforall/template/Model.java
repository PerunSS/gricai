package com.funforall.template;

import java.util.ArrayList;
import java.util.List;

public class Model {

	private static Model instance = new Model();
	private List<Text> data = new ArrayList<Text>();
	private List<Text> searchResult;

	private Model() {
	}

	public static Model getInstance() {
		return instance;
	}

	public List<String> getFavorites() {
		List<String> favorites = new ArrayList<String>();
		for (Text element : data) {
			if (element.isFavorite)
				favorites.add(element.text);
		}
		return favorites;
	}

	public void toogleFavorite(int i) {
		i %= data.size();
		data.get(i).isFavorite = !data.get(i).isFavorite;
	}

	public Text getText(int i) {
		i %= data.size();
		return data.get(i);
	}
	
	public int getSize(){
		return data.size();
	}
	
	public void setData(List<Text> data){
		this.data = data;
	}

	public List<Text> getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(List<Text> searchResult) {
		this.searchResult = searchResult;
	}
	
}
