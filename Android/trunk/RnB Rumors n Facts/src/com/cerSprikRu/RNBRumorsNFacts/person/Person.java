package com.cerSprikRu.RNBRumorsNFacts.person;

import java.util.List;

public class Person {

	private List<String> rumorsAndFacts;
	private int currentIndex;
	private String name;
	
	private Person(){
		
	}
	
	public static Person readPerson( int personIndex, String name, List<String> rummors){
		Person person = new Person();
		person.currentIndex = -1;
		person.name = name;
		person.rumorsAndFacts = rummors;
		return person;
	}
	
	public String getName(){
		return name;
	}

	public String getNext(){
		currentIndex++;
		if(currentIndex>=rumorsAndFacts.size()){
			currentIndex = 0;
		}
		return rumorsAndFacts.get(currentIndex);
	}
	
	public String getPrevious(){
		currentIndex --;
		if(currentIndex < 0)
			currentIndex = rumorsAndFacts.size()-1;
		return rumorsAndFacts.get(currentIndex);
	}
	
	public String getRandom(){
		currentIndex = (int)(Math.random() *rumorsAndFacts.size());
		return rumorsAndFacts.get(currentIndex);
	}
}

