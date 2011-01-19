package com.android.projectsforlearning.factstemplate.person;

import java.util.List;

public class Person {

	private String name;
	private List<String> rumorsAndFacts;
	private int currentIndex;
	
	private Person(){
		
	}
	
	public static Person readPerson(int personIndex){
		Person person = new Person();
		person.currentIndex = -1;
		return person;
	}
	
	public String getName(){
		/*return name;*/
		return "name";
	}
	
	public String getNextRumor(){
		/*currentIndex++;
		if(currentIndex>=rumorsAndFacts.size()){
			currentIndex = 0;
		}
		return rumorsAndFacts.get(currentIndex);*/
		return "next";
	}
	
	public String getPrevious(){
		/*currentIndex --;
		if(currentIndex < 0)
			currentIndex = rumorsAndFacts.size()-1;
		return rumorsAndFacts.get(currentIndex);*/
		return "previous";
	}
	
	public String getRandom(){
		/*currentIndex = (int)(Math.random() *rumorsAndFacts.size());
		return rumorsAndFacts.get(currentIndex);*/
		return "random";
	}
}
