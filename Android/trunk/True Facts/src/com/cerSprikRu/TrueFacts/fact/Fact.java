package com.cerSprikRu.TrueFacts.fact;

public class Fact {

	private String text;
	private boolean favorite;
	private int id;
	private String publisher;
	
	public Fact(String text, boolean favorite, int id, String publisher){
		this.text = text;
		this.favorite = favorite;
		this.id = id;
		this.publisher = publisher;
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

	public String getPublisher() {
		return publisher;
	}

}
