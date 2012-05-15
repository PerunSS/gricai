package com.cerspri.games.tod;

import com.cerspri.games.tod.model.Constants;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectDirtinessScreen extends Activity{
	public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.dirtiness_selection);
	        
	        Button dirtinessSelectedButton = (Button) findViewById(R.id.selectDirtinessButton);
	        
	        dirtinessSelectedButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					setResult(Constants.SELECT_DIRTINESS_ACTIVITY);
					finish();
				}
			});
	}
}
