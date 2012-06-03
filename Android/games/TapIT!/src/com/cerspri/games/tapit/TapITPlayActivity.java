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
	public static final int CLICKIT_END_GAME_CODE = 11112;
	private boolean paused = false;
	private Dialog dialog;
	private boolean dialogShowned = false;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		if (!paused) {
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
		System.out.println("GAME CREATE");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		System.out.println("GAME RESTART");
	}

	@Override
	protected void onStart() {
		super.onStart();
		System.out.println("GAME START");
	}

	@Override
	protected void onStop() {
		super.onStop();
		System.out.println("GAME STOP");
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
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		System.out.println("GAME SAVE INSTANCE STATE");
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		System.out.println("GAME RESTORE INSTANCE STATE");
	}

	@Override
	protected void onResume() {
		super.onResume();
		System.out.println("GAME RESUME");

		if (dialogShowned) {
			showDialog();
		} else if (paused) {
			panel.continiue();
			paused = false;
		}
	}

	@Override
	protected void onPause() {
		// if (dialog != null && dialog.isShowing())
		// dialog.dismiss();
		super.onPause();
		if (!dialogShowned)
			panel.pause();
		paused = true;
		System.out.println("GAME PAUSE");
		if(isFinishing()){
			System.out.println("GAME FINISHING");
		}
		//finish();
	}

	@Override
	public void onBackPressed() {
		panel.pause();
		showDialog();
		// Intent intent = new Intent(this, TapITPauseActivity.class);
		// startActivity(intent);
	}

	private void showDialog() {
		dialogShowned = true;
		dialog = new Dialog(this,
				android.R.style.Theme_Black_NoTitleBar_Fullscreen);

		dialog.setContentView(R.layout.pause_dialog);
		TextView gameScoreVeiw = (TextView) dialog
				.findViewById(R.id.game_score_pause);
		gameScoreVeiw.setText(TapITGame.getInstance().getScore() + ".0");
		TextView maxTimeVeiw = (TextView) dialog
				.findViewById(R.id.max_time_pause);
		maxTimeVeiw.setText(TapITGame.getInstance().getMaxTime() / 1000.0 + "");
		Button dismissButton = (Button) dialog.findViewById(R.id.back_pause);
		dismissButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				TapITGame.getInstance().clear();
				panel.endGame(false, true);
				setResult(TapITPlayActivity.CLICKIT_END_GAME_CODE);
				dialogShowned = false;
				dialog.dismiss();
				finish();
			}
		});
		Button continueButton = (Button) dialog
				.findViewById(R.id.continue_game_button_pause);
		continueButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				panel.continiue();
				dialog.dismiss();
				dialogShowned = false;
			}
		});
		final Button soundButton = (Button) dialog
				.findViewById(R.id.sound_pause);
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

		final Button musicButton = (Button) dialog
				.findViewById(R.id.music_pause);
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
		final Button rateButton = (Button) dialog
				.findViewById(R.id.rate_us_pause);
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

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == CLICKIT_END_GAME_CODE) {
			panel.endGame(false, true);
			finish();
		} else {
			super.onActivityResult(requestCode, resultCode, data);
		}
	}

}
