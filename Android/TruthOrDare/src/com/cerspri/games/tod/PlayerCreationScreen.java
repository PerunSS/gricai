package com.cerspri.games.tod;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PlayerCreationScreen extends Activity{
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_creation);
        
        Button createPlayerButton = (Button) findViewById(R.id.createPlayerButton);
        
        createPlayerButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
        
    }
}
