package com.cerSprikRu.QuizTemplate.model;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
	private List<Question> questions = new ArrayList<Question>();
	private int numberOfQuestions = 0;
	
	public void addQuestion (Question question){
		questions.add(question);
		numberOfQuestions++;
	}
	
	public Question getQuestion(int i){
		if (i < numberOfQuestions){
		return questions.get(i);
		} else {
			return null;
		}
	}
	
	
}
