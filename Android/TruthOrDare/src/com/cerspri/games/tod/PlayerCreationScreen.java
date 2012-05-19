package com.cerspri.games.tod;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.cerspri.games.tod.model.Constants;
import com.cerspri.games.tod.model.Game;
import com.cerspri.games.tod.model.Player;

public class PlayerCreationScreen extends Activity{
	
	public EditText playerNameField;
	public RadioGroup selectGender;
	public RadioGroup selectAffinity;
	public Context myContext = this;
	
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
				String affinity = "";
				String gender = "";
				String name = playerNameField.getText().toString();
				RadioButton genderButton = (RadioButton)findViewById(selectGender.getCheckedRadioButtonId());
				if (genderButton != null){
					gender = genderButton.getText().toString();
				}
				RadioButton affinityButton = (RadioButton) findViewById(selectAffinity.getCheckedRadioButtonId());
				if (affinityButton != null){
					affinity = genderButton.getText().toString();
				}
				if(affinity!="" && gender!="" && name.length()>1){
					Player player =  new Player(name, gender, affinity);
					Game.getInstance().addPlayer(player);
					setResult(Constants.CREATE_PLAYER_ACTIVITY);
					finish();
				} else {
					AlertDialog.Builder builder = new AlertDialog.Builder(myContext);
					builder.setMessage("You must input your name, and select gender and affinity")
							.setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									
								}
							});
					AlertDialog alert = builder.create();
					alert.show();
				}
			}
		});
        
        
        
    }
}
