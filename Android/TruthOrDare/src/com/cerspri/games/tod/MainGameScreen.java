package com.cerspri.games.tod;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cerspri.games.tod.model.Constants;
import com.cerspri.games.tod.model.Game;

public class MainGameScreen extends Activity{
	
	private Button playDareButton;
	private Button playTruthButton;
	private Button selectDirtinessButton;
	private TextView playerName;
	private TextView playerDirtiness;
	private Game game = Game.getInstance();
	private List<ImageView> lvls;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_game);
        
        playDareButton = (Button) findViewById(R.id.playDareButton);
        playTruthButton = (Button) findViewById(R.id.playTruthButton);
        selectDirtinessButton = (Button) findViewById(R.id.dirtinessSelection);
        playerName = (TextView) findViewById(R.id.playerNameInGame);
        playerDirtiness = (TextView) findViewById(R.id.playerDirtinessInGame);
        ImageView image = (ImageView)findViewById(R.id.level1img);
        lvls = new ArrayList<ImageView>();
        lvls.add((ImageView) findViewById(R.id.level1img));
        lvls.add((ImageView) findViewById(R.id.level2img));
        lvls.add((ImageView) findViewById(R.id.level3img));
        lvls.add((ImageView) findViewById(R.id.level4img));
        lvls.add((ImageView) findViewById(R.id.level5img));
        
        playerName.setText(game.getPlayer().getName());
        playerDirtiness.setText(game.getDirtinessString());
        this.setDirtinessIcons();
        
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
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {   	
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Constants.SELECT_DIRTINESS_ACTIVITY){
			playerDirtiness.setText(game.getDirtinessString());
			this.setDirtinessIcons();
		}
	}
	 
	private void setDirtinessIcons(){
		int dirtiness = game.getDirtiness();
		int level = game.getDirtinessLevel();
		for (int i = 0;i<5;i++){
			if(dirtiness==1){
				if(i<level){
					lvls.get(i).setBackgroundResource(R.drawable.funny_checked);
				} else {
					lvls.get(i).setBackgroundResource(R.drawable.funny_not_checked);
				}
			} else if (dirtiness==2){
				if(i<level){
					lvls.get(i).setBackgroundResource(R.drawable.tempting_checked);
				} else {
					lvls.get(i).setBackgroundResource(R.drawable.tempting_not_checked);
				}
			} else if (dirtiness==3){
				if(i<level){
					lvls.get(i).setBackgroundResource(R.drawable.sexy_checked);
				} else {
					lvls.get(i).setBackgroundResource(R.drawable.sexy_not_checked);
				}
			} else if (dirtiness==4){
				if(i<level){
					lvls.get(i).setBackgroundResource(R.drawable.dirty_checked);
				} else {
					lvls.get(i).setBackgroundResource(R.drawable.dirty_not_checked);
				}
			}
		}
	}
}
