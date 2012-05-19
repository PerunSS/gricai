package com.cerspri.games.tod;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.cerspri.games.tod.adapter.PlayerListAdapter;
import com.cerspri.games.tod.model.Constants;
import com.cerspri.games.tod.model.Game;

public class PlayerSelectionScreen extends Activity{
	private Game game = Game.getInstance();
	public PlayerListAdapter playerListAdapter;
	public ListView playerList;
	public Context myContext = this;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	        setContentView(R.layout.player_selection);
	        
	        Button addPlayerButton = (Button) findViewById(R.id.addPlayer);
	        Button startGameButton = (Button) findViewById(R.id.startGame);
	        playerList = (ListView) findViewById(R.id.playerList);
	        

	        playerListAdapter =  new PlayerListAdapter(this, game.getPlayers());
	        playerList.setAdapter(playerListAdapter);
	        
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
					if(Game.getInstance().getPlayers().size()>1){
						Game.getInstance().startGame();
						Intent myIntent = new Intent(v.getContext(), SelectDirtinessScreen.class);
						startActivityForResult(myIntent, 0);
					}else {
						AlertDialog.Builder builder = new AlertDialog.Builder(myContext);
						builder.setMessage("At least 2 players are needed for this game !!!")
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
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {   	
			super.onActivityResult(requestCode, resultCode, data);
			if (resultCode == Constants.SELECT_DIRTINESS_ACTIVITY){
				Intent myIntent = new Intent(this, MainGameScreen.class);
				startActivityForResult(myIntent, 0);
			} else if (resultCode == Constants.CREATE_PLAYER_ACTIVITY){
				this.playerListAdapter.refresh(Game.getInstance().getPlayers());
			}
	 }
}
