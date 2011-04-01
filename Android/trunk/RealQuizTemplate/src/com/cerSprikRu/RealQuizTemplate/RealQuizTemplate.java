package com.cerSprikRu.RealQuizTemplate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class RealQuizTemplate extends Activity {
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        Button start = (Button) findViewById(R.id.startNewButton);
        start.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent startQuiz = new Intent(v.getContext(),
						QuizActivity.class);
				startActivity(startQuiz);
			}
		});
        
        Button highScores = (Button) findViewById(R.id.highScoreButton);
        highScores.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				Intent favoritesIntent = new Intent(v.getContext(),
//						DisplayFavorites.class);
//				startActivity(favoritesIntent);
			}
		});
        
        Button quit = (Button) findViewById(R.id.quitButton);
        quit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
//				Intent favoritesIntent = new Intent(v.getContext(),
//						DisplayFavorites.class);
//				startActivity(favoritesIntent);
			}
		});
    }       
}