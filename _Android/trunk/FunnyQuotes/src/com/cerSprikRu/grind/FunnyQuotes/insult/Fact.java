package com.cerSprikRu.grind.FunnyQuotes.insult;

public class Fact {

	private String text;
	private boolean favorite;
	private int id;
	
	public Fact(String text, boolean favorite, int id){
		this.text = text;
		this.favorite = favorite;
		this.id = id;
	}
	
	public String getText() {
		return text;
	}

	public boolean isFavorite() {
		return favorite;
	}

	public int getId() {
		return id;
	}

	public void togleFavorite() {
		favorite = !favorite;
	}
	
	@Override
	public String toString() {
		return text;
	}

}
