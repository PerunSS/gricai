package com.cerSprikRu.TruthOrDare.model;

public class Player {

	public enum Gender{
		MALE, FEMALE
	}
	public enum Orientation {
		STRAIGHT, BISEXUAL, GAY
	}
	
	private Gender gender;
	private Orientation orientation;
	private String name;
	
	public Player(String name, Gender gender, Orientation orientation){
		this.name=name;
		this.gender=gender;
		this.orientation=orientation;
	}
	
	public Player(String name){
		this.name=name;
	}
	
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Orientation getOrientation() {
		return orientation;
	}
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
