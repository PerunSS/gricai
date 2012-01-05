package com.cerspri.star.NickiMinaj;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

public class Disclaimer extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.disclaimer);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(
				"You need to load data from server for the first run ever (after that you can do it manually)")
				.setCancelable(false)
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						setResult(4374);
						finish();
					}
				})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								setResult(4373);
								finish();
							}
						});
		AlertDialog alert = builder.create();
		alert.show();
	}
}