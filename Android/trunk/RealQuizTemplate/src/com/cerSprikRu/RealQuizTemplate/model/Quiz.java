package com.cerSprikRu.RealQuizTemplate.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Quiz {

	private static final int CORRECT_SCORE = 5;
	private static final int WRONG_SCORE = -1;
	// poenta je da nikad ne prikaze sva pitanja, da bi kviz bio drugaciji stalno
	// i da bi ljudi vise igrali, za to na hard prikazuje max brojPitanja/1.7
	private double difficultyModificator = 1.7;

	private List<Question> questions = new ArrayList<Question>();
	private int score;
	private int currentQuestion;

	public void addQuestion(Question question) {
		questions.add(question);
	}

	public Question getNextQuestion() {
		if (currentQuestion < questions.size() / difficultyModificator) {
			return questions.get(currentQuestion);
		} else {
			return null;
		}

	}

	public void tryAnswer(int answer) {
		if (questions.get(currentQuestion).getAnswer(answer).isCorrect()) {
			score += CORRECT_SCORE;
		} else {
			score += WRONG_SCORE;
		}
		currentQuestion++;
	}

	public void startQuiz() {
		Collections.shuffle(questions);
		for (Question question : questions) {
			question.shuffle();
		}
		score = 0;
		currentQuestion = 0;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void setDifficulty(Difficulty dif){
		switch (dif) {
		case HARD:
			difficultyModificator = 1.7;
			break;
		case MEDIUM:
			difficultyModificator = 2.5;
			break;
		case EASY:
			difficultyModificator = 3.3;
			break;

		default:
			break;
		}
	}

}
