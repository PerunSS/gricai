package com.cerspri.games.tapit;

import com.cerspri.games.tapit.model.TapITGame;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class TapITPlayActivity extends Activity {

	private TapITPanel panel;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		TapITGame.getInstance().clear();
		panel = new TapITPanel(this);
		setContentView(panel);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			onBackPressed();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onBackPressed() {
		panel.pause();
		final Dialog dialog = new Dialog(this, android.R.style.Theme_Black){
			 @Override
	            public boolean onKeyDown(int keyCode, KeyEvent event) {
	                if(keyCode == KeyEvent.KEYCODE_BACK) {
	                	dismiss();
	    				panel.continiue();
	                    return true;
	                }

	                return super.onKeyDown(keyCode, event);
	            }
		};
		dialog.setContentView(R.layout.pause_dialog);
		dialog.setTitle("PAUSE");
		TextView gameScoreVeiw = (TextView) dialog
				.findViewById(R.id.game_score);
		gameScoreVeiw.setText(panel.getScore() + ".0");
		TextView maxTimeVeiw = (TextView) dialog.findViewById(R.id.max_time);
		maxTimeVeiw.setText(panel.getMaxTime()/1000.0 + "");
		Button dismissButton = (Button) dialog.findViewById(R.id.back);
		dismissButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
				finish();
			}
		});
		Button continueButton = (Button) dialog
				.findViewById(R.id.continue_game_button);
		continueButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
				panel.continiue();
			}
		});
		dialog.show();
	}

}
