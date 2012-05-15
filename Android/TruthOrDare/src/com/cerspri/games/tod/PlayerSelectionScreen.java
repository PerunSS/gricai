package com.cerspri.games.tod;

import com.cerspri.games.tod.model.Constants;

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
	        startGameButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent myIntent = new Intent(v.getContext(), SelectDirtinessScreen.class);
					startActivityForResult(myIntent, 0);
				}
			});
	 }
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {   	
			super.onActivityResult(requestCode, resultCode, data);
			if (resultCode == Constants.SELECT_DIRTINESS_ACTIVITY){
				Intent myIntent = new Intent(this, MainGameScreen.class);
				startActivityForResult(myIntent, 0);
			}
	 }
}
