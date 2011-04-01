package com.cerSprikRu.NikiFacts;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class NikiFacts extends Activity {
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
			R.drawable.b36, R.drawable.b37, };

	private int currentBck;
	private AlertDialog.Builder builder;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		facts = getResources().getStringArray(R.array.facts);
		fact = facts[(int) (Math.random() * facts.length)];
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
				String timestamp = Long.toString(System.currentTimeMillis());
				MediaStore.Images.Media.insertImage(getContentResolver(),
						BitmapFactory
								.decodeResource(getResources(), currentBck),
						timestamp, timestamp);

				builder.setMessage("image: " + timestamp + " saved");
				builder.setTitle("saved");
				builder.setNeutralButton("OK",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.cancel();
							}
						});
				AlertDialog dialog = builder.create();
				dialog.show();
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
						"Fact about Nicki Minaj: "
								+fact);

				startActivity(Intent.createChooser(intent, "share"));
			}
		});

	}
}