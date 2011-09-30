package com.cerSprikRu.RihannaQuotes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

public class RihannaQuotes extends Activity {
	private List<String> facts;
	private TextView factsTextView;
	private ScrollView scrollView;
	private Context myContext = this;

	private float fontSize;
	private int fontColor;
	private float shadowSize;
	private int shadowColor;

	private String fact;
	private int current = 0;
	private int[] backgrounds = { R.drawable.b1, R.drawable.b2, R.drawable.b3,
			R.drawable.b4, R.drawable.b5, R.drawable.b6, R.drawable.b7,
			R.drawable.b8, R.drawable.b9, R.drawable.b10, R.drawable.b11,
			R.drawable.b12, R.drawable.b13, R.drawable.b14, R.drawable.b15,
			R.drawable.b16, R.drawable.b17, R.drawable.b18, R.drawable.b19,
			R.drawable.b20, R.drawable.b21, R.drawable.b22, R.drawable.b23,
			R.drawable.b24, R.drawable.b25, R.drawable.b26, R.drawable.b27,
			R.drawable.b28, R.drawable.b29, R.drawable.b30, R.drawable.b31,
			R.drawable.b32, R.drawable.b33, R.drawable.b34, R.drawable.b35,
			R.drawable.b36, R.drawable.b37, R.drawable.b38, R.drawable.b39,
			R.drawable.b40, R.drawable.b41, R.drawable.b42, R.drawable.b43,
			R.drawable.b44, R.drawable.b45, R.drawable.b46, R.drawable.b47,
			R.drawable.b48, R.drawable.b49, R.drawable.b50, R.drawable.b51,
			R.drawable.b52, };

	private int currentBck;

	// private AlertDialog.Builder builder;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SharedPreferences sPrefs = this.getPreferences(MODE_WORLD_READABLE);
		fontSize = sPrefs.getFloat("font_size", 22.0f);
		fontColor = sPrefs.getInt("font_color", 0xFFCCCC00);
		shadowSize = sPrefs.getFloat("shadow_size", 10f);
		shadowColor = sPrefs.getInt("shadow_color", 0xFFFF0000);

		setContentView(R.layout.main);
		scrollView = (ScrollView) findViewById(R.id.scroll_view);
		String elements[] = getResources().getStringArray(R.array.facts);
		facts = new ArrayList<String>();
		for (String element : elements)
			facts.add(element);
		Collections.shuffle(facts);
		fact = facts.get(current);
		AdView adView1 = (AdView) this.findViewById(R.id.ad1);
		adView1.loadAd(new AdRequest());

		AdView adView2 = (AdView) this.findViewById(R.id.ad2);
		adView2.loadAd(new AdRequest());

		final Button randomBtn = (Button) findViewById(R.id.random);

		randomBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				fact = facts.get((++current) % facts.size());
				factsTextView = (TextView) findViewById(R.id.fact);
				currentBck = backgrounds[(int) (Math.random() * backgrounds.length)];
				scrollView.setBackgroundResource(currentBck);
				factsTextView.setText(fact);
			}
		});
		// builder = new AlertDialog.Builder(this);
		final Button saveBtn = (Button) findViewById(R.id.save);
		saveBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String externalDirectory = Environment
						.getExternalStorageDirectory().toString();
				String directory = RihannaQuotes.class.getSimpleName();
				File file = new File(externalDirectory + File.separator
						+ directory);
				if (!file.isDirectory())
					file.mkdir();
				String timestamp = Long.toString(System.currentTimeMillis());
				file = new File(externalDirectory + File.separator + directory,
						timestamp);
				OutputStream out = null;
				try {
					out = new FileOutputStream(file);
					Bitmap bm = BitmapFactory.decodeResource(getResources(),
							currentBck);
					bm.compress(Bitmap.CompressFormat.JPEG, 100, out);
					out.flush();
				} catch (FileNotFoundException e) {
				} catch (IOException e) {
				} finally {
					if (out != null)
						try {
							out.close();
						} catch (IOException e) {
						}
				}
				Toast.makeText(RihannaQuotes.this,
						"Saved image: " + file.getAbsolutePath(),
						Toast.LENGTH_LONG).show();
			}
		});
		factsTextView = (TextView) findViewById(R.id.fact);
		currentBck = backgrounds[(int) (Math.random() * backgrounds.length)];

		scrollView.setBackgroundResource(currentBck);
		factsTextView.setText(fact);

		final Button settingsButton = (Button) findViewById(R.id.settings_button);
		settingsButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SettingsDialog dialog = new SettingsDialog(myContext, fontSize,
						shadowSize, fontColor, shadowColor,
						new SettingsDialog.SettingsListener() {

							@Override
							public void changeSettings(float fontSize,
									float shadowSize, int textColor,
									int shadowColor) {
								boolean cancel = false;
								if (fontSize != Float.NaN) {
									RihannaQuotes.this.fontSize = fontSize;
								} else {
									cancel = true;
								}
								if (shadowSize != Float.NaN) {
									RihannaQuotes.this.shadowSize = shadowSize;
								}
								if (textColor != Integer.MAX_VALUE) {
									RihannaQuotes.this.fontColor = textColor;
								}
								if (shadowColor != Integer.MAX_VALUE) {
									RihannaQuotes.this.shadowColor = shadowColor;
								}
								if (!cancel)
									changeColors();
							}
						});
				dialog.show();
			}
		});

		final Button shareButton = (Button) findViewById(R.id.share);
		shareButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final Intent intent = new Intent(Intent.ACTION_SEND);

				intent.setType("text/plain");
				intent.putExtra(Intent.EXTRA_TEXT, "Rihanna: " + fact);

				startActivity(Intent.createChooser(intent, "share"));
			}
		});
		changeColors();
		
		//link to android makret search cerspri.kru
		final Button cerspriButton = (Button)findViewById(R.id.cerspri_button);
		cerspriButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("market://search?q=cerspri.kru"));
				startActivity(intent);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.facts_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.settings:
			SettingsDialog dialog = new SettingsDialog(this, factsTextView
					.getTextSize(), 10, 0xFFFF0000, 0xFFFF0000,
					new SettingsDialog.SettingsListener() {

						@Override
						public void changeSettings(float fontSize,
								float shadowSize, int textColor, int shadowColor) {
							boolean cancel = false;
							if (fontSize != Float.NaN) {
								RihannaQuotes.this.fontSize = fontSize;
							} else {
								cancel = true;
							}
							if (shadowSize != Float.NaN) {
								RihannaQuotes.this.shadowSize = shadowSize;
							}
							if (textColor != Integer.MAX_VALUE) {
								RihannaQuotes.this.fontColor = textColor;
							}
							if (shadowColor != Integer.MAX_VALUE) {
								RihannaQuotes.this.shadowColor = shadowColor;
							}
							if (!cancel)
								changeColors();
						}
					});
			dialog.show();

			/*
			 * new ColorPickerDialog(this, new OnColorChangedListener() {
			 * 
			 * @Override public void colorChanged(int color) {
			 * factsTextView.setTextColor(color); } }, 0xFFFF0000).show();
			 */
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void changeColors() {
		SharedPreferences sPrefs = this.getPreferences(MODE_WORLD_WRITEABLE);
		Editor editor = sPrefs.edit();
		editor.putFloat("font_size", fontSize);
		editor.putFloat("shadow_size", shadowSize);
		editor.putInt("font_color", fontColor);
		editor.putInt("shadow_color", shadowColor);
		factsTextView.setTextColor(fontColor);
		factsTextView.setTextSize(fontSize);
		factsTextView.setShadowLayer(shadowSize, 0, 0, shadowColor);
		editor.commit();
	}
}