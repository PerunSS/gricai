package com.cerspri.zombie.nation.model;

public class Quote {

	private String text;
	private int trueFactor;
	private String id;
	private String author;
	private boolean favorite;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getTrueFactor() {
		return trueFactor;
	}

	public void setTrueFactor(int trueFactor) {
		this.trueFactor = trueFactor;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public boolean isFavorite() {
		return favorite;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}
	
	public void toogleFavorite(){
		favorite = !favorite;
	}
}
