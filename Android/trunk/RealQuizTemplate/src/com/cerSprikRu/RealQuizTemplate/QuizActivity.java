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
import android.widget.RadioGroup;
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
	private int correctInARow = 0;
	private Object mutex = new Object();
	
	private Runnable updateTask = new Runnable() {
		@Override
		public void run() {
			synchronized (mutex) {
				if(quiz.isGameOver()){
					handler.removeCallbacks(this);
					displayGameOver();
					return;
				}
				if(quiz.isVictory()){
					handler.removeCallbacks(this);
					displayVictory();
					return;
				}
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
		quizzTime = 15;
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
		if(question.getAnswer(0)!=null)
			firstAnswer.setText(question.getAnswer(0).getAnswer());
		else
			firstAnswer.setText("");

		secondAnswer = (RadioButton) findViewById(R.id.answer2);
		if(question.getAnswer(1)!=null)
			secondAnswer.setText(question.getAnswer(1).getAnswer());
		else
			secondAnswer.setText("");


		thirdAnswer = (RadioButton) findViewById(R.id.answer3);
		if(question.getAnswer(2)!=null)
			thirdAnswer.setText(question.getAnswer(2).getAnswer());
		else
			thirdAnswer.setText("");

		
		fourthAnswer = (RadioButton) findViewById(R.id.answer4);
		if(question.getAnswer(3)!=null)
			fourthAnswer.setText(question.getAnswer(3).getAnswer());
		else
			fourthAnswer.setText("");

		reset();
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
				System.out.println("checked answer: "+checked);
				if (checked>=0){
					if(quiz.tryAnswer(checked)){
						correctInARow++;
					}else{
						correctInARow = 0;
					}
					
					if(correctInARow == 3){
						synchronized (mutex) {
							quizzTime+=5;
						}
					}else if (correctInARow == 5){
						synchronized (mutex) {
							quizzTime+=15;
						}
					}else if (correctInARow>5){
						synchronized (mutex) {
							quizzTime+=5;
						}
					}
					System.out.println(correctInARow+", new time: "+quizzTime);
					next();
				}else{
					displayErrorDialog("You must check one answer");
				}
			}
		});
	}
	
	private void reset() {
		RadioGroup group = (RadioGroup)findViewById(R.id.answers);
		group.clearCheck();
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
