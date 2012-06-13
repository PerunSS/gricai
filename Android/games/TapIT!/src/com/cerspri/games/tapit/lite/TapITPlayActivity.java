package com.cerspri.games.tapit.lite;

import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.cerspri.games.tapit.lite.model.SoundOptions;
import com.cerspri.games.tapit.lite.model.TapITGame;

public class TapITPlayActivity extends Activity {

	private TapITPanel panel;
	public static final int CLICKIT_CONTINUE_GAME_CODE = 11111;
	public static final int CLICKIT_END_GAME_CODE = 11112;
	private boolean paused = false;
	private long backTime = 0;
//	private Dialog dialog;
//	private boolean dialogShowned = false;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		//TODO ovde samo ocistiti igru
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		if (!paused) {
			TapITGame.getInstance().clear();
		}
		System.out.println("GAME CREATE");
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
	protected void onResume() {
		//TODO rekreirati panel iz pocetka i nastaviti igru (ili ako tek pocinje igra onda je samo krenuti dalje)
		super.onResume();
		System.out.println("GAME RESUME");
		
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

//		*if (dialogShowned) {
//			showDialog();
//		} else*/ if (paused) {
//			setContentView(panel);
//			//panel.continiue();
//			paused = false;
//		}/
	}

	@Override
	protected void onPause() {
		//TODO zaustaviti panel kako valja
//		if (dialog != null && dialog.isShowing())
//			dialog.dismiss();
		super.onPause();
//		if (!panel.isEndGame())
//			if (!dialogShowned)
		panel.pause();
//		paused = true;
//		System.out.println("GAME PAUSE");
//		if (isFinishing()) {
//			System.out.println("GAME FINISHING");
//		}
		setContentView(new View(this));
		// finish();
	}

	@Override
	public void onBackPressed() {
//		panel.pause();
		//showDialog();
//		Intent intent = new Intent(this, TapITPauseActivity.class);
//		startActivityForResult(intent,CLICKIT_CONTINUE_GAME_CODE);
		long time = new Date().getTime();
		if(time - backTime < 1000){
			finish();
		}else {
			backTime = time;
			Toast.makeText(this, "Hit back one more time to exit", Toast.LENGTH_SHORT).show();
		}
	}

	/*
	private void showDialog() {
		setContentView(new View(this));
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
				setContentView(panel);
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
	}*/

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		//TODO posto ovde nece postojati panel onda samo zavrsiti aktiviti
		System.out.println("GAME RESULT CODE: "+resultCode);
		if (resultCode == CLICKIT_END_GAME_CODE) {
			panel.endGame(false, true);
			finish();
		} else {
			super.onActivityResult(requestCode, resultCode, data);
		}
	}

}
