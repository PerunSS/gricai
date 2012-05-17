package com.cerspri.games.tapit;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.cerspri.games.tapit.model.HighScore;
import com.cerspri.games.tapit.network.NetworkUtils;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class TapITActivity extends Activity {
	public static final int CLICKIT_PLAY_CODE = 12345;
	private MediaPlayer displayScore;
	

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
				final Dialog infoDialog = new Dialog(TapITActivity.this, android.R.style.Theme_Black);
				infoDialog.setContentView(R.layout.instructions);
				infoDialog.setTitle(getString(R.string.title));
				infoDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
				infoDialog.show();
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == CLICKIT_PLAY_CODE) {
			displayScore = MediaPlayer.create(this, R.raw.display_score_end);
			displayScore.setLooping(true);
			displayScore.start();
			long score = data.getExtras().getLong("score");
			double max = data.getExtras().getDouble("max");
			final Dialog dialog = new Dialog(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen){
				@Override
				public boolean onKeyDown(int keyCode, KeyEvent event) {
					if(keyCode == KeyEvent.KEYCODE_BACK) {
						displayScore.stop();
						displayScore.release();
						dismiss();
						return true;
					}
					return super.onKeyDown(keyCode, event);
				}
			};
			dialog.setContentView(R.layout.game_over_dialog);
			dialog.setTitle("SCORE");
			TextView gameScoreVeiw = (TextView) dialog
					.findViewById(R.id.game_score);
			gameScoreVeiw.setText(score + ".0");
			TextView maxTimeVeiw = (TextView) dialog
					.findViewById(R.id.max_time);
			maxTimeVeiw.setText(max + "");
			TextView totalScoreVeiw = (TextView) dialog
					.findViewById(R.id.total_score);
			totalScoreVeiw.setText((score + max) + "");
			Button dismissButton = (Button) dialog.findViewById(R.id.back);
			dismissButton.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					displayScore.stop();
					displayScore.release();
					dialog.dismiss();
				}
			});
			Button submitScoreButton = (Button) dialog
					.findViewById(R.id.submit_score);
			submitScoreButton.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {

				}
			});
			dialog.show();

		}
	}
	
	private class HighScoreFetcherTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = ProgressDialog.show(getApplicationContext(), "", "Loading...");
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			StringBuilder networkData = NetworkUtils.readFromLink(HIGH_SCORES_URL);
			try {
				highScores = HighScore.parseScores(new JSONObject(networkData.toString()));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			dialog.dismiss();
		}
		
	}
	
	private ProgressDialog dialog;
	private static final String HIGH_SCORES_URL = "http://www.cerspri.com/api/tap_it/get_highscores.php";
	private List<HighScore> highScores = null;
}