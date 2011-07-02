package com.cerSprikRu.TruthOrDare.model;

public class Player {

	enum Gender{
		MALE, FEMALE
	}
	enum Orientation {
		STRAIGHT, BISEXUAL, GAY
	}
	
	private Gender gender;
	private Orientation orientation;
	private String name;
	
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
