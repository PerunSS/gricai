package com.cerspri.games.tapit;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cerspri.games.tapit.model.SoundOptions;
import com.cerspri.games.tapit.model.TapITGame;

public class TapITPauseActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//mediaplayer.getcurrentposition
		//mediaplayer.seekto
		
		
		setContentView(R.layout.pause_dialog);
		TextView gameScoreVeiw = (TextView) findViewById(R.id.game_score);
		gameScoreVeiw.setText(TapITGame.getInstance().getScore() + ".0");
		TextView maxTimeVeiw = (TextView) findViewById(R.id.max_time);
		maxTimeVeiw.setText(TapITGame.getInstance().getMaxTime() / 1000.0 + "");
		Button dismissButton = (Button) findViewById(R.id.back);
		dismissButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				TapITGame.getInstance().clear();
				//panel.endGame(false, true);
				setResult(TapITPlayActivity.CLICKIT_END_GAME_CODE);
				finish();
			}
		});
		Button continueButton = (Button) findViewById(R.id.continue_game_button);
		continueButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
				//panel.continiue();
			}
		});
		final Button soundButton = (Button) findViewById(R.id.sound);
		if (!SoundOptions.getInstance().isPlaySound()) {
			soundButton.setBackgroundResource(R.drawable.mute_sound);
		}
		soundButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				SoundOptions.getInstance().tooglePlaySound();
				if (SoundOptions.getInstance().isPlaySound()) {
					soundButton.setBackgroundResource(R.drawable.play_sound);
//					panel.setSoundVolume(1f);
				} else {
					soundButton.setBackgroundResource(R.drawable.mute_sound);
//					panel.setSoundVolume(0f);
				}
			}
		});

		final Button musicButton = (Button) findViewById(R.id.music);
		if (!SoundOptions.getInstance().isPlayMusic()) {
			musicButton.setBackgroundResource(R.drawable.mute_music);
		}
		musicButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				SoundOptions.getInstance().tooglePlayMusic();
				if (SoundOptions.getInstance().isPlayMusic()) {
					musicButton.setBackgroundResource(R.drawable.play_music);
//					panel.setMusicVolume(1f);
				} else {
					musicButton.setBackgroundResource(R.drawable.mute_music);
//					panel.setMusicVolume(0f);
				}

			}
		});
		final Button rateButton = (Button) findViewById(R.id.rate_us);

		rateButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri
						.parse("market://details?id=com.cerspri.games.tapit"));
				startActivity(intent);
			}
		});
	}
}
