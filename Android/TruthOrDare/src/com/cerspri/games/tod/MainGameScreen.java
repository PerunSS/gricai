package com.cerspri.games.tod;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainGameScreen extends Activity{
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_game);
        
        Button playDareButton = (Button) findViewById(R.id.playDareButton);
        Button playTruthButton = (Button) findViewById(R.id.playTruthButton);
        Button selectDirtinessButton = (Button) findViewById(R.id.dirtinessSelection);
        
        selectDirtinessButton.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {	
					Intent myIntent = new Intent(v.getContext(), SelectDirtinessScreen.class);
					startActivityForResult(myIntent, 0);				
			}
		});
        
        playTruthButton.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {	
					Intent myIntent = new Intent(v.getContext(), QueryScreen.class);
					startActivityForResult(myIntent, 0);				
			}
		});
        
        playDareButton.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {	
					Intent myIntent = new Intent(v.getContext(), QueryScreen.class);
					startActivityForResult(myIntent, 0);				
			}
		});
	}
}
