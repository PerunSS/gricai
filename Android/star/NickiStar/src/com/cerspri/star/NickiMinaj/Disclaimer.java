package com.cerspri.star.NickiMinaj;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

public class Disclaimer extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		checkForNetwork();
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
	
	private void checkForNetwork() {
		if (!isNetworkAvailable()) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage(
					"Internet connection is required for application to work. Try again?")
					.setCancelable(false)
					.setPositiveButton("Try again",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									checkForNetwork();
								}
							})
					.setNegativeButton("Close",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									setResult(4372);
									finish();
								}
							});
			AlertDialog alert = builder.create();
			alert.show();
		}
	}
	
	private boolean isNetworkAvailable() {
		try {
			ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo activeNetworkInfo = connectivityManager
					.getActiveNetworkInfo();
			return activeNetworkInfo.isConnected();
		} catch (Exception e) {
			return false;
		}
	}
}