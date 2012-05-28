package com.cerspri.games.tapit;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;

import com.cerspri.games.tapit.model.Constants;
import com.cerspri.games.tapit.model.SoundOptions;
import com.cerspri.games.tapit.model.TapITGame;

public class TapITPlayActivity extends Activity {

	private TapITPanel panel;
	private static final int CLICKIT_PAUSE_CODE = 11111;
	public static final int CLICKIT_END_GAME_CODE = 11112;

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
	protected void onResume() {
		super.onResume();
		SharedPreferences preferences = getSharedPreferences("tapit",
				MODE_WORLD_WRITEABLE);

		String gameState = preferences.getString(Constants.GAME_STATE, "");
		if (gameState != null && gameState.length() > 0) {
			panel = TapITPanel.continueState(gameState, this);
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
	}

	@Override
	protected void onPause() {
		// if (dialog != null && dialog.isShowing())
		// dialog.dismiss();
		super.onPause();
		SharedPreferences.Editor editor = getSharedPreferences("tapit",
				MODE_WORLD_WRITEABLE).edit();
		editor.putString(Constants.GAME_STATE, panel.saveState());
		editor.commit();
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(this, TapITPauseActivity.class);
		startActivityForResult(intent, CLICKIT_PAUSE_CODE);
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
