package com.cerSprikRu.QuizTemplate.model;

import java.io.Serializable;
import java.util.Map;

public class Question implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String question;
	private Map<String, Boolean> answers;
	
	public Question(String question, Map<String, Boolean> answers){
		this.question=question;
		this.answers=answers;
	}
	
	
	public Map<String, Boolean> getAnswers() {
		return answers;
	}
	public void setAnswers(Map<String, Boolean> answers) {
		this.answers = answers;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
}
