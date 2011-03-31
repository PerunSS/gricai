package com.cerSprikRu.QuizTemplate.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Question implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String question;
	private List<String> answers;
	private List<Boolean> validity;
	
	public Question(String question){
		this.question=question;
		this.answers= new ArrayList<String>();
		this.validity = new ArrayList<Boolean>();
	}
	
	
	public List<String> getAnswer() {
		return answers;
	}
	public void putAnswer(String answer, boolean validity) {
		this.answers.add(answer);
		this.validity.add(new Boolean(validity));
	}
	public String getAnswer(int i){
		return answers.get(i);
	}
	public boolean getValidity(int i){
		return validity.get(i);
	}
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
}
