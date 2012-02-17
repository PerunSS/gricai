package com.cerspri.games.tapit;

import com.cerspri.games.tapit.model.TapITGame;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

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
	
}
