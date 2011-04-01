package com.cerSprikRu.RealQuizTemplate.model;

public class Answer {

	private String answer;
	private boolean correct;
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getAnswer() {
		return answer;
	}
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	public boolean isCorrect() {
		return correct;
	}
}
