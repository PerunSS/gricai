package com.cerspri.games.tapit;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.cerspri.games.tapit.model.SoundOptions;
import com.cerspri.games.tapit.model.TapITGame;

public class TapITPlayActivity extends Activity {

	private TapITPanel panel;
	private Dialog dialog;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		TapITGame.getInstance().clear();
		panel = new TapITPanel(this);
		if (SoundOptions.getInstance().isPlayMusic()) {
			panel.setMusicVolume(1f);
		} else {
			panel.setMusicVolume(0f);
		}
		if (SoundOptions.getInstance().isPlaySound()) {
			panel.setSoundVolume(1f);
		} else {
			panel.setSoundVolume(0f);
		}
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
	protected void onPause() {
		if (dialog != null && dialog.isShowing())
			dialog.dismiss();
		super.onPause();
	}

	@Override
	public void onBackPressed() {
		panel.pause();
		dialog = new Dialog(this,
				android.R.style.Theme_Black_NoTitleBar_Fullscreen) {
			@Override
			public boolean onKeyDown(int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					dismiss();
					panel.continiue();
					return true;
				}

				return super.onKeyDown(keyCode, event);
			}
		};
		dialog.setContentView(R.layout.pause_dialog);
		TextView gameScoreVeiw = (TextView) dialog
				.findViewById(R.id.game_score);
		gameScoreVeiw.setText(TapITGame.getInstance().getScore() + ".0");
		TextView maxTimeVeiw = (TextView) dialog.findViewById(R.id.max_time);
		maxTimeVeiw.setText(panel.getMaxTime() / 1000.0 + "");
		Button dismissButton = (Button) dialog.findViewById(R.id.back);
		dismissButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				TapITGame.getInstance().clear();
				panel.endGame(false, true);
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
		final Button soundButton = (Button) dialog.findViewById(R.id.sound);
		if (!SoundOptions.getInstance().isPlaySound()) {
			soundButton.setBackgroundResource(R.drawable.mute_sound);
		}
		soundButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				SoundOptions.getInstance().tooglePlaySound();
				if (SoundOptions.getInstance().isPlaySound()) {
					soundButton.setBackgroundResource(R.drawable.play_sound);
					panel.setSoundVolume(1f);
				} else {
					soundButton.setBackgroundResource(R.drawable.mute_sound);
					panel.setSoundVolume(0f);
				}
			}
		});

		final Button musicButton = (Button) dialog.findViewById(R.id.music);
		if (!SoundOptions.getInstance().isPlayMusic()) {
			musicButton.setBackgroundResource(R.drawable.mute_music);
		}
		musicButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				SoundOptions.getInstance().tooglePlayMusic();
				if (SoundOptions.getInstance().isPlayMusic()) {
					musicButton.setBackgroundResource(R.drawable.play_music);
					panel.setMusicVolume(1f);
				} else {
					musicButton.setBackgroundResource(R.drawable.mute_music);
					panel.setMusicVolume(0f);
				}

			}
		});
		final Button rateButton = (Button) dialog.findViewById(R.id.rate_us);
		
		rateButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri
						.parse("market://details?id=com.cerspri.games.tapit"));
				startActivity(intent);
			}
		});

		dialog.show();
	}

}
