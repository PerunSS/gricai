package com.cerSprikRu.grind.StupidInsults.insult;

public class Fact {

	private String text;
	private String person;
	private boolean favorite;
	private int id;
	private int personID;
	
	public Fact(String text, String person, boolean favorite, int id, int personID){
		this.text = text;
		this.person = person;
		this.favorite = favorite;
		this.id = id;
		this.personID = personID;
	}
	
	public String getText() {
		return text;
	}

	public String getPerson() {
		return person;
	}

	public boolean isFavorite() {
		return favorite;
	}

	public int getId() {
		return id;
	}

	public int getPersonID() {
		return personID;
	}
	
	public void togleFavorite() {
		favorite = !favorite;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}

}
