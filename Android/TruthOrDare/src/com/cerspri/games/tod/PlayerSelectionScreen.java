package com.cerspri.games.tod;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PlayerSelectionScreen extends Activity{
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.player_selection);
	        
	        Button addPlayerButton = (Button) findViewById(R.id.addPlayer);
	        Button startGameButton = (Button) findViewById(R.id.startGame);
	        
	        addPlayerButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent myIntent = new Intent(v.getContext(), PlayerCreationScreen.class);
					startActivityForResult(myIntent, 0);
				}
			});
	 }
}
