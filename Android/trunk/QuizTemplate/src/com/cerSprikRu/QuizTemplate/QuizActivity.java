package com.cerSprikRu.QuizTemplate;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class QuizActivity extends Activity{
	
	private TextView question;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.quiz);
		question = (TextView)findViewById(R.id.QuestionText);
		question.setText("sisaj ga matori");
		
		final Button commitButton = (Button) findViewById(R.id.AnswerButton);
		commitButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {			
			}
		});
	}
}
