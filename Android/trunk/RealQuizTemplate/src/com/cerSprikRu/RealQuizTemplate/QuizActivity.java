package com.cerSprikRu.RealQuizTemplate;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.cerSprikRu.RealQuizTemplate.db.customManager.DBManager;
import com.cerSprikRu.RealQuizTemplate.model.Question;
import com.cerSprikRu.RealQuizTemplate.model.Quiz;

public class QuizActivity extends Activity {

	private DBManager manager;
	private Quiz quiz;
	private Question question;
	private TextView questionText;
	private RadioButton firstAnswer;
	private RadioButton secondAnswer;
	private RadioButton thirdAnswer;
	private TextView result;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.quiz);
		manager = new DBManager(this);
		quiz = manager.read();
		quiz.startQuiz();
		next();
	}

	private void next() {
		question = quiz.getNextQuestion();

		if (question == null) {
			openResultPage();
			return;
		}

		questionText = (TextView) findViewById(R.id.QuestionText);
		questionText.setText(question.getQuestion());

		firstAnswer = (RadioButton) findViewById(R.id.answer1);
		firstAnswer.setText(question.getAnswer(0).getAnswer());

		secondAnswer = (RadioButton) findViewById(R.id.answer2);
		secondAnswer.setText(question.getAnswer(1).getAnswer());

		thirdAnswer = (RadioButton) findViewById(R.id.answer3);
		thirdAnswer.setText(question.getAnswer(2).getAnswer());

		final Button commitButton = (Button) findViewById(R.id.AnswerButton);
		commitButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int checked = -1;
				if (firstAnswer.isChecked() == true) {
					checked = 0;
				} else if (secondAnswer.isChecked() == true) {
					checked = 1;
				} else if (thirdAnswer.isChecked() == true) {
					checked = 2;
				}
				quiz.tryAnswer(checked);
				next();
			}
		});
	}

	private void openResultPage() {
		setContentView(R.layout.end_results);

		result = (TextView) findViewById(R.id.result);
		result.setText("your score is"
				+ new Integer(quiz.getScore()).toString());
	}

}
