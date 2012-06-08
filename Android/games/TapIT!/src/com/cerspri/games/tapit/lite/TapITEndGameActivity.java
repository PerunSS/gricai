package com.cerspri.games.tapit.lite;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cerspri.games.tapit.lite.model.SoundOptions;
import com.cerspri.games.tapit.lite.network.NetworkUtils;

public class TapITEndGameActivity extends Activity {

	private MediaPlayer displayScore;
	private static final String HIGH_SCORES_SUBMIT_URL = "http://www.cerspri.com/api/tap_it/update_score.php";
	private boolean submited = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent data = getIntent();
		displayScore = MediaPlayer.create(this, R.raw.display_score_end);
		displayScore.setLooping(true);
		if (!SoundOptions.getInstance().isPlayMusic()) {
			displayScore.setVolume(0f, 0f);
		} else {
			displayScore.setVolume(1f, 1f);
		}
		displayScore.start();
		long score = data.getExtras().getLong("score");
		double max = data.getExtras().getDouble("max");
		setContentView(R.layout.game_over);
		setTitle("SCORE");
		TextView gameScoreVeiw = (TextView) findViewById(R.id.game_score);
		gameScoreVeiw.setText(score + ".0");
		TextView maxTimeVeiw = (TextView) findViewById(R.id.max_time);
		maxTimeVeiw.setText(max + "");
		TextView totalScoreVeiw = (TextView) findViewById(R.id.total_score);
		final double total = score + max;
		totalScoreVeiw.setText(total + "");
		Button dismissButton = (Button) findViewById(R.id.back);
		dismissButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (displayScore != null) {
					displayScore.stop();
					displayScore.release();
					displayScore = null;
				}
				finish();
			}
		});
		Button submitScoreButton = (Button) findViewById(R.id.submit_score);
		submitScoreButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!submited) {
					AlertDialog.Builder alert = new AlertDialog.Builder(
							TapITEndGameActivity.this);

					alert.setTitle("Submit High Score");
					alert.setMessage("Your Score is: " + total
							+ ", enter Name below.");

					// Set an EditText view to get user input
					final EditText input = new EditText(
							TapITEndGameActivity.this);
					alert.setView(input);

					alert.setPositiveButton(R.string.submit_dialog,
							new DialogInterface.OnClickListener() {
								@SuppressWarnings("unchecked")
								public void onClick(DialogInterface dialog,
										int whichButton) {
									String name = input.getText().toString();
									if (name.length() < 2) {
										Toast.makeText(
												TapITEndGameActivity.this,
												"Minimal number of characters is 2!",
												Toast.LENGTH_LONG).show();
									} else {
										Map<String, String> params = new HashMap<String, String>();
										params.put("name", name);
										params.put("score", total + "");
										new UpdateHighScore().execute(params);
//										StringBuilder result = NetworkUtils
//												.updateToLink(
//														HIGH_SCORES_SUBMIT_URL,
//														params);
//										if (result != null
//												&& result.toString().contains(
//														"ok")) {
//											Toast.makeText(
//													TapITEndGameActivity.this,
//													"Score submited",
//													Toast.LENGTH_LONG).show();
//											submited = true;
//										} else {
//											Toast.makeText(
//													TapITEndGameActivity.this,
//													"No internet connection.",
//													Toast.LENGTH_LONG).show();
//										}
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
					Toast.makeText(TapITEndGameActivity.this,
							"This Score is allready submitted",
							Toast.LENGTH_LONG).show();
				}
			}
		});
		
		final Button rateButton = (Button) findViewById(R.id.rate_us);
		
		rateButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri
						.parse("market://details?id=com.cerspri.games.tapit.lite"));
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onPause() {
		if (displayScore != null) {
			displayScore.stop();
			displayScore.release();
			displayScore = null;
		}
		super.onPause();
	}
	
	private class UpdateHighScore extends AsyncTask<Map<String, String>, Void, StringBuilder>{

		@Override
		protected StringBuilder doInBackground(Map<String, String>... params) {
			StringBuilder result = NetworkUtils
					.updateToLink(
							HIGH_SCORES_SUBMIT_URL,
							params[0]);
			return result;
		}
		
		@Override
		protected void onPostExecute(StringBuilder result) {
			if (result != null
					&& result.toString().contains(
							"ok")) {
				Toast.makeText(
						TapITEndGameActivity.this,
						"Score submited",
						Toast.LENGTH_LONG).show();
				submited = true;
			} else {
				Toast.makeText(
						TapITEndGameActivity.this,
						"No internet connection.",
						Toast.LENGTH_LONG).show();
			}
		}
		
	}
}
