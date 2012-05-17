package com.cerspri.games.tod;

import com.cerspri.games.tod.model.Constants;
import com.cerspri.games.tod.model.Game;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SelectDirtinessScreen extends Activity{
	
	private RadioGroup dirtinessGroup;
	private RadioGroup levelSelection;
	
	public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.dirtiness_selection);
	        
	        Button dirtinessSelectedButton = (Button) findViewById(R.id.selectDirtinessButton);
	        dirtinessGroup = (RadioGroup) findViewById(R.id.dirtinessSelection);
	        levelSelection = (RadioGroup) findViewById(R.id.levelSelection);
	        
	        dirtinessSelectedButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					RadioButton dirtinessButton = (RadioButton)findViewById(dirtinessGroup.getCheckedRadioButtonId());
					String dirtinessString =  dirtinessButton.getText().toString();
					int dirtiness;
					if (dirtinessString=="Funny"){
						dirtiness = 1;
					} else if (dirtinessString=="Tempting"){
						dirtiness = 2;
					} else if (dirtinessString=="Sexy"){
						dirtiness = 3;
					} else if (dirtinessString=="Dirty"){
						dirtiness = 4;
					} else {
						dirtiness = 0;
					}
					Game.getInstance().setDirtiness(dirtiness);
					
					RadioButton levelButton = (RadioButton)findViewById(levelSelection.getCheckedRadioButtonId());
					int level =  new Integer(levelButton.getTag().toString());
					Game.getInstance().setDirtinessLevel(level);
					
					setResult(Constants.SELECT_DIRTINESS_ACTIVITY);
					finish();
				}
			});
	}
}
