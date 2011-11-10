package com.cerSprikRu.grind.HiltonQuotes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

public class HiltonQuotes extends Activity {
	private String[] facts;
	private TextView factsTextView;
	private String fact;
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
			R.drawable.b48, R.drawable.b49, R.drawable.b50,};

	private int currentBck;
	private AlertDialog.Builder builder;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		facts = getResources().getStringArray(R.array.facts);
		fact = facts[(int) (Math.random() * facts.length)];
		
		AdView adView1 = (AdView)this.findViewById(R.id.ad1);
	    adView1.loadAd(new AdRequest());

	    AdView adView2 = (AdView)this.findViewById(R.id.ad2);
	    adView2.loadAd(new AdRequest());
		
		final Button randomBtn = (Button) findViewById(R.id.random);

		randomBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				fact = facts[(int) (Math.random() * facts.length)];
				factsTextView = (TextView) findViewById(R.id.fact);
				if (fact.length() > 200)
					factsTextView.setTextSize(25);
				else if (fact.length() > 150)
					factsTextView.setTextSize(27);
				else if (fact.length() > 100)
					factsTextView.setTextSize(30);
				else
					factsTextView.setTextSize(35);
				currentBck = backgrounds[(int) (Math.random() * backgrounds.length)];
				factsTextView.setBackgroundResource(currentBck);
				factsTextView.setText(fact);
			}
		});
		builder = new AlertDialog.Builder(this);
		final Button saveBtn = (Button) findViewById(R.id.save);
		saveBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String externalDirectory = Environment
						.getExternalStorageDirectory().toString();
				String directory = HiltonQuotes.class.getSimpleName();
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
				Toast.makeText(HiltonQuotes.this, "Saved image: "+file.getAbsolutePath(), Toast.LENGTH_LONG).show();
			}
		});
		factsTextView = (TextView) findViewById(R.id.fact);
		if (fact.length() > 200)
			factsTextView.setTextSize(25);
		else if (fact.length() > 150)
			factsTextView.setTextSize(27);
		else if (fact.length() > 100)
			factsTextView.setTextSize(30);
		else
			factsTextView.setTextSize(35);
		currentBck = backgrounds[(int) (Math.random() * backgrounds.length)];
		factsTextView.setBackgroundResource(currentBck);
		factsTextView.setText(fact);
		
		final Button shareButton = (Button) findViewById(R.id.share);
		shareButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final Intent intent = new Intent(Intent.ACTION_SEND);

				intent.setType("text/plain");
				intent.putExtra(Intent.EXTRA_TEXT,
						"Nicki Minaj: "
								+fact);

				startActivity(Intent.createChooser(intent, "share"));
			}
		});


	}
}