package com.funforall.bakiapp;

import java.util.ArrayList;
import java.util.List;

public class Model {

	private static Model instance = new Model();
	private List<Text> data = new ArrayList<Text>();
	private List<Text> searchResult;
	private DBManager manager;

	private Model() {
	}

	public static Model getInstance() {
		return instance;
	}

	public List<Text> getFavorites() {
		List<Text> favorites = new ArrayList<Text>();
		for (Text element : data) {
			if (element.isFavorite)
				favorites.add(element);
		}
		return favorites;
	}

	public void toogleFavorite(int i) {
		i %= data.size();
		data.get(i).isFavorite = !data.get(i).isFavorite;
		manager.updateText(data.get(i));
	}

	public Text getText(int i) {
		if (data.size() > 0) {
			i %= data.size();
			return data.get(i);
		}
		return null;
	}

	public int getSize() {
		return data.size();
	}

	public void setData(List<Text> data) {
		this.data = data;
	}

	public List<Text> getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(List<Text> searchResult) {
		this.searchResult = searchResult;
	}

	public void setManager(DBManager manager) {
		this.manager = manager;
	}

}
