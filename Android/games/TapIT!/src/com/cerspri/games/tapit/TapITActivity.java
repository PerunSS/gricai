package com.cerspri.games.tapit;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class TapITActivity extends Activity {
	public static final int CLICKIT_PLAY_CODE = 12345;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        final Button newGameButton = (Button)findViewById(R.id.new_game);
        newGameButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(TapITActivity.this, TapITPlayActivity.class);
				startActivityForResult(intent, CLICKIT_PLAY_CODE);
			}
		});
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if(resultCode == CLICKIT_PLAY_CODE){
    		long score = data.getExtras().getLong("score");
    		double max = data.getExtras().getDouble("max");
    		final Dialog dialog = new Dialog(this);
    		dialog.setContentView(R.layout.game_over_dialog);
    		dialog.setTitle("SCORE");
    		TextView gameScoreVeiw = (TextView)dialog.findViewById(R.id.game_score);
    		gameScoreVeiw.setText(score+".0");
    		TextView maxTimeVeiw = (TextView)dialog.findViewById(R.id.max_time);
    		maxTimeVeiw.setText(max+"");
    		TextView totalScoreVeiw = (TextView)dialog.findViewById(R.id.total_score);
    		totalScoreVeiw.setText((score+max)+"");
    		Button dismissButton = (Button)dialog.findViewById(R.id.back);
    		dismissButton.setOnClickListener(new View.OnClickListener() {
    			
    			@Override
    			public void onClick(View v) {
    				dialog.dismiss();
    			}
    		});
    		Button submitScoreButton = (Button)dialog.findViewById(R.id.submit_score);
    		submitScoreButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
				}
			});
    		dialog.show();

    	}
    }
}