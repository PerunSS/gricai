package com.cerSprikRu.RNBRumorsNFacts.fact;

public class Fact {

	private String text;
	private String person;
	private boolean favourite;
	private int id;
	private int personID;
	
	public Fact(String text, String person, boolean favourite, int id, int personID){
		this.text = text;
		this.person = person;
		this.favourite = favourite;
		this.id = id;
		this.personID = personID;
	}
	
	public String getText() {
		return text;
	}

	public String getPerson() {
		return person;
	}

	public boolean isFavourite() {
		return favourite;
	}

	public int getId() {
		return id;
	}

	public int getPersonID() {
		return personID;
	}
	
	public void makeFavourite (){
		favourite = true;
	}
	
	public void removeFavourite (){
		favourite = false;
	}

}
