package com.cerSprikRu.RealQuizTemplate.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Quiz {

	// poenta je da nikad ne prikaze sva pitanja, da bi kviz bio drugaciji
	// stalno i da bi ljudi vise igrali, za to na hard prikazuje max
	// brojPitanja/1.7
	private double difficultyModificator = 1;

	private List<Question> questions = new ArrayList<Question>();
	private int score;
	private int currentQuestion;
	//private int maxNumOfMistakes;
	private GameState currentState;

	public enum GameState {
		NORMAL_PLAY, TIME_PLAY, GAME_OVER, VICTORY;
	}

	public void addQuestion(Question question) {
		questions.add(question);
	}

	public Question getNextQuestion() {
		if (currentState != GameState.GAME_OVER) {
			System.out.println(currentQuestion + " of " + questions.size()
					/ difficultyModificator);
			if (currentQuestion < questions.size() / difficultyModificator) {
				return questions.get(currentQuestion);
			} else {
				currentState = GameState.VICTORY;
				return null;
			}
		}
		return null;

	}

	public synchronized boolean tryAnswer(int answer) {
		boolean correct = false;
		if(currentQuestion>=questions.size())
			return false;
		if (questions.get(currentQuestion).getAnswer(answer) != null
				&& questions.get(currentQuestion).getAnswer(answer).isCorrect()) {
			if (currentState == GameState.TIME_PLAY) {

			} else
				score += 1;
			correct = true;
		} /*else {
			if (maxNumOfMistakes >= 0)
				maxNumOfMistakes--;
			if (maxNumOfMistakes < 0) {
				currentState = GameState.GAME_OVER;
			}
		}*/
		currentQuestion++;
		return correct;
	}

	public void startQuiz() {
		Collections.shuffle(questions);
		for (Question question : questions) {
			question.shuffle();
		}
		currentState = GameState.NORMAL_PLAY;
		score = 0;
		currentQuestion = 0;
	}

	public int getScore() {
		return score;
	}

	public void setDifficulty(Difficulty dif) {
		switch (dif) {
		case MANIAC:
			difficultyModificator = 1;
			//maxNumOfMistakes = 0;
			break;
		case HARD:
			difficultyModificator = 1.7;
			//maxNumOfMistakes = 1;
			break;
		case MEDIUM:
			difficultyModificator = 2.55;
			//maxNumOfMistakes = 3;
			break;
		case EASY:
			difficultyModificator = 3;
			//maxNumOfMistakes = 5;
			break;

		default:
			break;
		}
	}

	public void setGameState(GameState state) {
		currentState = state;
	}

	public boolean isGameOver() {
		return currentState == GameState.GAME_OVER;
	}

	public boolean isVictory() {
		return currentState == GameState.VICTORY;
	}

}
