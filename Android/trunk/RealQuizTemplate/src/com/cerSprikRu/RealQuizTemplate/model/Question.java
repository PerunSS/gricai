package com.cerSprikRu.RealQuizTemplate.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question implements Serializable {

	private static final long serialVersionUID = 1L;

	private String question;
	private List<Answer> answers;

	public Question(String question) {
		this.question = question;
		this.answers = new ArrayList<Answer>();
	}

	public List<Answer> getAnswer() {
		return answers;
	}

	public void putAnswer(String answer, boolean correct) {
		Answer ans = new Answer();
		ans.setAnswer(answer);
		ans.setCorrect(correct);
		this.answers.add(ans);

	}

	public Answer getAnswer(int i) {
		return answers.get(i);
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	
	public void shuffle(){
		Collections.shuffle(answers);
	}

}
