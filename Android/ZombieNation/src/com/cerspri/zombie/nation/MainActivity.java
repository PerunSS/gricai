package com.cerspri.zombie.nation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.cerspri.zombie.nation.model.Model;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		loadData();
		setContentView(R.layout.activity_main);
		showDisclamer();
	}

	public void michaelClick(View v) {
		Model.getInstance().setCurrentPerson(Model.MICHAEL_JACKSON);
		startFactActivity();
	}

	public void lincolnClick(View v) {
		Model.getInstance().setCurrentPerson(Model.ABRAHAM_LINCOLN);
		startFactActivity();
	}

	public void comingSoonClick(View v) {
		Toast.makeText(this, "Coming soon", Toast.LENGTH_LONG).show();
	}

	public void showFavorites(View v) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Favorites");
		final String[] favorites = Model.getInstance().getFavorites();
		if (favorites != null && favorites.length > 0) {
			builder.setItems(favorites, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							MainActivity.this);
					builder.setMessage(favorites[which]);
					builder.create().show();
				}
			});
		} else {
			builder.setMessage("No favorites added!");
		}
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	public void rateUs(View v) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri
				.parse("market://details?id=com.cerspri.zombie.nation"));
		startActivity(intent);
	}

	private void loadData() {
		Model.getInstance().addQuotes(Model.MICHAEL_JACKSON,
				getResources().getStringArray(R.array.MichaelJackson));
		Model.getInstance().addQuotes(Model.ABRAHAM_LINCOLN,
				getResources().getStringArray(R.array.AbrahamLinkoln));
		Model.getInstance().loadFavorites(this);

	}

	private void startFactActivity() {
		Intent intent = new Intent(this, FactActivity.class);
		startActivity(intent);
	}

	private void showDisclamer() {
		SharedPreferences preferences = getSharedPreferences(
				Model.PREFERENCES_NAME, Context.MODE_PRIVATE);
		boolean disclamer = preferences.getBoolean(Model.DISCLAMER, false);
		if (!disclamer) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Zombie Nation: Disclamer");
			builder.setMessage("This application means no harm. Any similarity with real characters are not intentional. If anyone find this application offending please inform us on cerspri.stars@gmail.com. This application is trying to show taughts of important people via popular way.");
			builder.show();
			SharedPreferences.Editor editor = getSharedPreferences(
					Model.PREFERENCES_NAME, Context.MODE_PRIVATE).edit();
			editor.putBoolean(Model.DISCLAMER, true);
			editor.commit();
		}
		
	}
}
