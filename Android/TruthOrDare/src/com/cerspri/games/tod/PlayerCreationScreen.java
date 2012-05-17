package com.cerspri.games.tod;

import com.cerspri.games.tod.model.Constants;
import com.cerspri.games.tod.model.Game;
import com.cerspri.games.tod.model.Player;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class PlayerCreationScreen extends Activity{
	
	public EditText playerNameField;
	public RadioGroup selectGender;
	public RadioGroup selectAffinity;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_creation);
        
        Button createPlayerButton = (Button) findViewById(R.id.createPlayerButton);
        playerNameField = (EditText) findViewById(R.id.playerName);
        selectGender = (RadioGroup) findViewById(R.id.genderSelection);
        selectAffinity = (RadioGroup) findViewById(R.id.affinitySelection);
        
        
        createPlayerButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String name = playerNameField.getText().toString();
				RadioButton genderButton = (RadioButton)findViewById(selectGender.getCheckedRadioButtonId());
				String gender = genderButton.getText().toString();
				RadioButton affinityButton = (RadioButton) findViewById(selectAffinity.getCheckedRadioButtonId());
				String affinity = affinityButton.getText().toString();
				Player player =  new Player(name, gender, affinity);
				Game.getInstance().addPlayer(player);
				setResult(Constants.CREATE_PLAYER_ACTIVITY);
				finish();
			}
		});
        
        
        
    }
}
