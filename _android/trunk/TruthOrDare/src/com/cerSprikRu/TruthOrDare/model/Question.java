package com.cerSprikRu.TruthOrDare.model;

import java.util.ArrayList;
import java.util.List;

import com.cerSprikRu.TruthOrDare.model.Player.Gender;
import com.cerSprikRu.TruthOrDare.model.Player.Orientation;

public class Question {

	private List<Gender> genders;
	private List<Orientation> orientations;
	private String text;
	
	public Question(String text){
		this.text = text;
		genders = new ArrayList<Player.Gender>();
		orientations = new ArrayList<Player.Orientation>();
	}
	
	public List<Gender> getGenders() {
		return genders;
	}
	public void addGender(Gender gender) {
		genders.add(gender);
	}
	public List<Orientation> getOrientations() {
		return orientations;
	}
	public void addOrientation(Orientation orientation) {
		orientations.add(orientation);
	}
	public String getText() {
		return text;
	}
}
