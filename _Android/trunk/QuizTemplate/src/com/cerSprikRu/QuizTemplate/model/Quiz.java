package com.cerSprikRu.QuizTemplate.model;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
	private List<Question> questions = new ArrayList<Question>();
	private int numberOfQuestions;
	private int score;
	private int currentQuestion;
	
	public void addQuestion(Question question){
		questions.add(question);
		numberOfQuestions++;
	}
	
	public Question getNextQuestion(){
		if (currentQuestion < numberOfQuestions){
			return questions.get(currentQuestion);
		} else {
			return null;
		}
		
	}
	
	public void tryAnswer(int answer){
		if (questions.get(currentQuestion).getValidity(answer)){
			score+=5;
		} else {
			score-=1;
		}
		currentQuestion++;
	}

	public void startQuiz(){
		numberOfQuestions = questions.size();
		setScore(0);
		setCurrentQuestion(0);
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getCurrentQuestion() {
		return currentQuestion;
	}

	public void setCurrentQuestion(int currentQuestion) {
		this.currentQuestion = currentQuestion;
	}
	
	public int getNumberOfQuestions() {
		return numberOfQuestions;
	}

	public void setNumberOfQuestions(int numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;
	}

	
}
