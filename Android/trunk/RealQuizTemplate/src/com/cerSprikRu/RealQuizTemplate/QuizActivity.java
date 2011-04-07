package com.cerSprikRu.RealQuizTemplate;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.cerSprikRu.RealQuizTemplate.db.customManager.DBManager;
import com.cerSprikRu.RealQuizTemplate.model.Question;
import com.cerSprikRu.RealQuizTemplate.model.Quiz;
import com.cerSprikRu.RealQuizTemplate.model.Quiz.GameState;

public class QuizActivity extends Activity {

	private DBManager manager;
	private Quiz quiz;
	private Question question;
	private TextView questionText;
	private RadioButton firstAnswer;
	private RadioButton secondAnswer;
	private RadioButton thirdAnswer;
	private RadioButton fourthAnswer;
	private TextView result, resultTitle;
	private TextView time;
	private Handler handler;
	private int quizzTime;
	private Runnable updateTask = new Runnable() {
		@Override
		public void run() {
			quizzTime--;
			if(quizzTime == 0){
				quiz.setGameState(GameState.GAME_OVER);
				handler.removeCallbacks(this);
				displayGameOver();
				return;
			}
			
			setTime(quizzTime);
			long startTime = SystemClock.uptimeMillis();
			handler.postAtTime(this, startTime+1000);	
		}
	};
	
	public void setTime(int time){
		System.out.println(time);
		this.time = (TextView)findViewById(R.id.timer);
		this.time.setText("time left: "+(time<10?("0"+time):time));
		
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.quiz);
		manager = new DBManager(this);
		quiz = manager.read();
		handler = new Handler();
		quizzTime = 5;
		handler.removeCallbacks(updateTask);
		handler.postDelayed(updateTask, 1000);
		setTime(quizzTime);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		quiz.startQuiz();
		next();
	}

	private void next() {
		question = quiz.getNextQuestion();

		if (quiz.isGameOver()) {
			displayGameOver();
			return;
		}
		if (quiz.isVictory()){
			displayVictory();
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
		
		fourthAnswer = (RadioButton) findViewById(R.id.answer4);

		final Button commitButton = (Button) findViewById(R.id.AnswerButton);
		commitButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int checked = -1;
				if (firstAnswer.isChecked()) {
					checked = 0;
				} else if (secondAnswer.isChecked()) {
					checked = 1;
				} else if (thirdAnswer.isChecked()) {
					checked = 2;
				} else if (fourthAnswer.isChecked()) {
					checked = 3;
				}
				if (checked>=0){
					quiz.tryAnswer(checked);
					next();
				}else{
					displayErrorDialog("You must check one answer");
				}
			}
		});
	}
	
	private void displayErrorDialog(String text){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(text);
		builder.setTitle("error");
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
			
		});
		builder.create().show();
	}

	private void displayGameOver() {
		handler.removeCallbacks(updateTask);
		setContentView(R.layout.end_results);

		resultTitle = (TextView) findViewById(R.id.result_title);
		resultTitle.setText("GAME OVER");

		result = (TextView) findViewById(R.id.result);
		result.setText("your score is"
				+ new Integer(quiz.getScore()).toString());

	}

	private void displayVictory() {
		handler.removeCallbacks(updateTask);
		setContentView(R.layout.end_results);

		resultTitle = (TextView) findViewById(R.id.result_title);
		resultTitle.setText("VICTORY");

		result = (TextView) findViewById(R.id.result);
		result.setText("your score is"
				+ new Integer(quiz.getScore()).toString());
	}
	
	

}
