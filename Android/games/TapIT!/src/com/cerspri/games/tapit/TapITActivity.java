package com.cerspri.games.tapit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.cerspri.games.tapit.adapter.HighScoreAdapter;
import com.cerspri.games.tapit.model.HighScore;
import com.cerspri.games.tapit.model.SoundOptions;
import com.cerspri.games.tapit.network.NetworkUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TapITActivity extends Activity {
	public static final int CLICKIT_PLAY_CODE = 12345;
	private MediaPlayer displayScore;
	private Dialog dialog; // endGameDialog

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
				submited = false;
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
						android.R.style.Theme_Black);
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
		if(!SoundOptions.getInstance().isPlaySound()){
			soundButton.setBackgroundResource(R.drawable.mute_sound);
		}
		soundButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SoundOptions.getInstance().tooglePlaySound();
				if(SoundOptions.getInstance().isPlaySound()){
					soundButton.setBackgroundResource(R.drawable.play_sound);
				}else{
					soundButton.setBackgroundResource(R.drawable.mute_sound);
				}
			}
		});
		
		final Button musicButton = (Button) findViewById(R.id.music);
		if(!SoundOptions.getInstance().isPlayMusic()){
			musicButton.setBackgroundResource(R.drawable.mute_music);
		}
		musicButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SoundOptions.getInstance().tooglePlayMusic();
				if(SoundOptions.getInstance().isPlayMusic()){
					musicButton.setBackgroundResource(R.drawable.play_music);
				}else{
					musicButton.setBackgroundResource(R.drawable.mute_music);
				}
			}
		});
	}

	@Override
	protected void onPause() {
		System.out.println(displayScore);
		if (displayScore != null) {
			displayScore.stop();
			displayScore.release();
			displayScore = null;
		}
		// TODO save state, and on resume build state again
		super.onPause();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == CLICKIT_PLAY_CODE) {
			displayEndGameDialog(data);
		}
	}

	private void displayEndGameDialog(Intent data) {
		displayScore = MediaPlayer.create(this, R.raw.display_score_end);
		displayScore.setLooping(true);
		if(!SoundOptions.getInstance().isPlayMusic()){
			displayScore.setVolume(0f, 0f);
		}else{
			displayScore.setVolume(1f, 1f);
		}
		displayScore.start();
		long score = data.getExtras().getLong("score");
		double max = data.getExtras().getDouble("max");
		dialog = new Dialog(this,
				android.R.style.Theme_Black_NoTitleBar_Fullscreen) {
			@Override
			public boolean onKeyDown(int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					if (displayScore != null) {
						displayScore.stop();
						displayScore.release();
						displayScore = null;
					}
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
		TextView maxTimeVeiw = (TextView) dialog.findViewById(R.id.max_time);
		maxTimeVeiw.setText(max + "");
		TextView totalScoreVeiw = (TextView) dialog
				.findViewById(R.id.total_score);
		final double total = score + max;
		totalScoreVeiw.setText(total + "");
		Button dismissButton = (Button) dialog.findViewById(R.id.back);
		dismissButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (displayScore != null) {
					displayScore.stop();
					displayScore.release();
					displayScore = null;
				}
				dialog.dismiss();
				dialog = null;
			}
		});
		Button submitScoreButton = (Button) dialog
				.findViewById(R.id.submit_score);
		submitScoreButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!submited) {
					AlertDialog.Builder alert = new AlertDialog.Builder(
							TapITActivity.this);

					alert.setTitle("Submit High Score");
					alert.setMessage("Your Score is: " + total
							+ ", enter Name below.");

					// Set an EditText view to get user input
					final EditText input = new EditText(TapITActivity.this);
					alert.setView(input);

					alert.setPositiveButton(R.string.submit_dialog,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									String name = input.getText().toString();
									if (name.length() < 2) {
										Toast.makeText(
												TapITActivity.this,
												"Minimal number of characters is 2!",
												Toast.LENGTH_LONG).show();
									} else {
										Map<String, String> params = new HashMap<String, String>();
										params.put("name", name);
										params.put("score", total + "");
										StringBuilder result = NetworkUtils
												.updateToLink(
														HIGH_SCORES_SUBMIT_URL,
														params);
										if (result.toString().contains("ok")) {
											Toast.makeText(TapITActivity.this,
													"Score submited",
													Toast.LENGTH_LONG).show();
											submited = true;
										} else {
											Toast.makeText(TapITActivity.this,
													"No internet connection.",
													Toast.LENGTH_LONG).show();
										}
									}
								}
							});

					alert.setNegativeButton(R.string.cancel,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									// Canceled.
								}
							});

					alert.show();
				} else {
					Toast.makeText(TapITActivity.this,
							"This Score is allready submitted",
							Toast.LENGTH_LONG).show();
				}
			}
		});
		dialog.show();
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
			try {
				highScores = HighScore.parseScores(new JSONObject(networkData
						.toString()));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			progressDialog.dismiss();
			System.out.println(highScores);
			Dialog highScoresDialog = new Dialog(TapITActivity.this);
			highScoresDialog.setTitle("HIGH SCORES");
			highScoresDialog.setContentView(R.layout.high_score_dialog);
			ListView view = (ListView) highScoresDialog
					.findViewById(R.id.high_scores_list);
			view.setAdapter(new HighScoreAdapter(TapITActivity.this,
					R.id.high_scores_list, highScores));
			highScoresDialog.show();
		}

	}

	private ProgressDialog progressDialog;
	private static final String HIGH_SCORES_URL = "http://www.cerspri.com/api/tap_it/get_highscores.php";
	private static final String HIGH_SCORES_SUBMIT_URL = "http://www.cerspri.com/api/tap_it/update_score.php";
	private boolean submited = false;
	private List<HighScore> highScores = null;
}