package com.cerspri.zombie.nation.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;

import com.cerspri.zombie.nation.R;

public class Model {
	public static final String MICHAEL_JACKSON = "Michael Jackson";
	public static final String ABRAHAM_LINCOLN = "Abraham Lincoln";
	public static final String PREFERENCES_NAME = "ZOMBIE_NATION";
	public static final String FAVORITES = "FAVORITES";
	public static final String DISCLAMER = "DISCLAMER";
	private Map<String, List<Quote>> quotes = new HashMap<String, List<Quote>>();
	private Map<String, Integer> positions = new HashMap<String, Integer>();
	private Map<String, Integer> images = new HashMap<String, Integer>();
	private static Model instance = new Model();

	private String currentPerson;

	private Model() {
		images.put(MICHAEL_JACKSON, R.drawable.jackson);
		images.put(ABRAHAM_LINCOLN, R.drawable.lincoln);
	};

	public static Model getInstance() {
		return instance;
	}

	public void addQuotes(String key, String[] elements) {
		List<Quote> quotes = new ArrayList<Quote>();
		for (String element : elements) {
			Quote quote = new Quote();
			String tmp[] = element.split("\\|"); // id|trueFactor|text
			if (tmp.length != 3)
				continue;
			quote.setId(tmp[0]);
			quote.setTrueFactor(Integer.parseInt(tmp[1]));
			quote.setText(tmp[2]);
			quote.setAuthor(key);
			quotes.add(quote);
		}
		this.quotes.put(key, quotes);
		this.positions.put(key, 0);
	}

	public void setCurrentPerson(String currentPerson) {
		this.currentPerson = currentPerson;
	}

	public Quote nextQuote() {
		if (currentPerson == null || quotes.get(currentPerson).size() == 0)
			return null;
		int currentPosition = positions.get(currentPerson);
		currentPosition++;
		currentPosition %= quotes.get(currentPerson).size();
		positions.put(currentPerson, currentPosition);
		return quotes.get(currentPerson).get(currentPosition);
	}

	public Quote previousQuote() {
		if (currentPerson == null || quotes.get(currentPerson).size() == 0)
			return null;
		int currentPosition = positions.get(currentPerson);
		currentPosition--;
		if (currentPosition < 0)
			currentPosition = quotes.get(currentPerson).size() - 1;
		positions.put(currentPerson, currentPosition);
		return quotes.get(currentPerson).get(currentPosition);
	}

	public Quote randomQuote() {
		if (currentPerson == null || quotes.get(currentPerson).size() == 0)
			return null;
		int currentPosition = (int) (Math.random() * quotes.get(currentPerson)
				.size());
		positions.put(currentPerson, currentPosition);
		return quotes.get(currentPerson).get(currentPosition);
	}

	public Quote currentQuote() {
		if (currentPerson == null || quotes.get(currentPerson).size() == 0)
			return null;
		return quotes.get(currentPerson).get(positions.get(currentPerson));
	}

	public void loadFavorites(Context context) {
		SharedPreferences preferences = context.getSharedPreferences(
				PREFERENCES_NAME, Context.MODE_PRIVATE);
		String fav = preferences.getString(FAVORITES, "");
		if (fav == "")
			return;
		String indexes[] = fav.split(";");
		if (indexes.length > 0) {
			for (String index : indexes) {
				for (int i = 0; i < quotes.get(MICHAEL_JACKSON).size(); i++)
					if (quotes.get(MICHAEL_JACKSON).get(i).getId()
							.equals(index)) {
						quotes.get(MICHAEL_JACKSON).get(i).setFavorite(true);
					}
				for (int i = 0; i < quotes.get(ABRAHAM_LINCOLN).size(); i++)
					if (quotes.get(ABRAHAM_LINCOLN).get(i).getId()
							.equals(index)) {
						quotes.get(ABRAHAM_LINCOLN).get(i).setFavorite(true);
					}
			}
		}
	}

	public void updateCurrentFavorite(Context context) {
		SharedPreferences preferences = context.getSharedPreferences(
				PREFERENCES_NAME, Context.MODE_PRIVATE);
		String fav = preferences.getString(FAVORITES, "");
		if (currentQuote().isFavorite()) {
			fav += currentQuote().getId() + ";";
		} else {
			String indexes[] = fav.split(";");
			String newFav = "";
			for (String index : indexes) {
				if (index.equals(currentQuote().getId()))
					continue;
				newFav += index + ";";
			}
			fav = newFav;
		}
		SharedPreferences.Editor editor = context.getSharedPreferences(
				PREFERENCES_NAME, Context.MODE_PRIVATE).edit();
		editor.putString(FAVORITES, fav);
		editor.commit();

	}

	public String[] getFavorites() {
		List<String> favorites = new ArrayList<String>();
		for (Quote quote : quotes.get(MICHAEL_JACKSON)) {
			if (quote.isFavorite())
				favorites.add(MICHAEL_JACKSON + " zombie: " + quote.getText());
		}
		for (Quote quote : quotes.get(ABRAHAM_LINCOLN)) {
			if (quote.isFavorite())
				favorites.add(ABRAHAM_LINCOLN + " zombie: " + quote.getText());
		}
		if (favorites.size() > 0) {
			int i = 0;
			String favArray[] = new String[favorites.size()];
			for (String fav : favorites)
				favArray[i++] = fav;
			return favArray;
		}
		return null;
	}

	public int getCurrentImage() {
		return images.get(currentPerson);
	}

	public String getCurrentPerson() {
		return currentPerson;
	}
}
