package com.android.projectsforlearning.factstemplate.person;

import java.util.List;

public class Person {

	private List<String> rumorsAndFacts;
	private int currentIndex;
	
	private Person(){
		
	}
	
	public static Person readPerson( int personIndex, String rummors){
		Person person = new Person();
		person.currentIndex = -1;
		String[] rmrArr = rummors.split(";");
		for(String str:rmrArr){
			person.rumorsAndFacts.add(str);
		}
//		ctx.
//		DBManager manager = new DBManager(ctx);
//		Cursor rumors = manager.getAllTitles(personIndex);
//		while(rumors.moveToNext()){
//			person.rumorsAndFacts.add(rumors.getString(1));
//		}
		return person;
	}
	

	public String getNextRumor(){
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
