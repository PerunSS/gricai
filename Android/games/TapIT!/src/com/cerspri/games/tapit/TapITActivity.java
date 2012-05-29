package com.cerspri.games.tapit;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.cerspri.games.tapit.adapter.HighScoreAdapter;
import com.cerspri.games.tapit.model.HighScore;
import com.cerspri.games.tapit.model.SoundOptions;
import com.cerspri.games.tapit.network.NetworkUtils;

public class TapITActivity extends Activity {
	public static final int CLICKIT_PLAY_CODE = 12345;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		SharedPreferences.Editor editor = getSharedPreferences("tapit",
//				MODE_WORLD_WRITEABLE)
//				.edit();
//		editor.putString(Constants.GAME_STATE, "");
//		editor.commit();
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		final Button newGameButton = (Button) findViewById(R.id.new_game);
		newGameButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(TapITActivity.this,
						TapITPlayActivity.class);
				startActivityForResult(intent, CLICKIT_PLAY_CODE);
			}
		});
		final Button instructionsButton = (Button) findViewById(R.id.instructions);
		instructionsButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final Dialog infoDialog = new Dialog(TapITActivity.this,
						android.R.style.Theme_Black_NoTitleBar_Fullscreen);
				infoDialog.setContentView(R.layout.instructions);
				infoDialog.setTitle(getString(R.string.title));
				infoDialog.getWindow().setBackgroundDrawable(
						new ColorDrawable(Color.BLACK));
				infoDialog.show();
			}
		});

		final Button highScoresButton = (Button) findViewById(R.id.highscores);
		highScoresButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				new HighScoreFetcherTask().execute();
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
				} else {
					soundButton.setBackgroundResource(R.drawable.mute_sound);
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
				} else {
					musicButton.setBackgroundResource(R.drawable.mute_music);
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

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == CLICKIT_PLAY_CODE) {
			Intent intent = new Intent(TapITActivity.this,
					TapITEndGameActivity.class);
			intent.putExtra("score", data.getExtras().getLong("score"));
			intent.putExtra("max", data.getExtras().getDouble("max"));
			startActivity(intent);
		}
	}

	private class HighScoreFetcherTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = ProgressDialog.show(TapITActivity.this, "",
					"Loading...");
		}

		@Override
		protected Void doInBackground(Void... params) {
			StringBuilder networkData = NetworkUtils
					.readFromLink(HIGH_SCORES_URL);
			badNetworkData = false;
			if (networkData != null) {
				try {
					highScores = HighScore.parseScores(new JSONObject(
							networkData.toString()));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				badNetworkData = true;
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			progressDialog.dismiss();
			if (badNetworkData) {
				Toast.makeText(TapITActivity.this, "No internet connection!",
						Toast.LENGTH_LONG).show();
			} else if (highScores != null && highScores.size() > 0) {
				Dialog highScoresDialog = new Dialog(TapITActivity.this);
				highScoresDialog.setTitle("HIGH SCORES");
				highScoresDialog.setContentView(R.layout.high_score_dialog);
				ListView view = (ListView) highScoresDialog
						.findViewById(R.id.high_scores_list);
				view.setAdapter(new HighScoreAdapter(TapITActivity.this,
						R.id.high_scores_list, highScores));
				highScoresDialog.show();
			} else {
				Toast.makeText(TapITActivity.this, "Server error!",
						Toast.LENGTH_LONG).show();
			}
		}

	}

	private ProgressDialog progressDialog;
	private static final String HIGH_SCORES_URL = "http://www.cerspri.com/api/tap_it/get_highscores.php";
	private List<HighScore> highScores = null;
	private boolean badNetworkData = false;
}