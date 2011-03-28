package com.cerSprikRu.QuizTemplate;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class QuizActivity extends Activity{
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.quiz);
	}
}
