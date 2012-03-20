package com.funforall.template;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

public class ListActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		final ListView listView = (ListView) findViewById(R.id.listView);

		AdView view = (AdView) findViewById(R.id.adViewList);
		view.loadAd(new AdRequest());
		Intent intent = getIntent();
		boolean search = false;
		if (intent.getExtras() != null)
			search = intent.getExtras().getBoolean("search");
		if (search) {
			listView.setAdapter(new TextAdapter(this, R.id.listView, Model
					.getInstance().getSearchResult()));
		} else {
			listView.setAdapter(new TextAdapter(this, R.id.listView, Model
					.getInstance().getFavorites()));
		}

		final Button back = (Button) findViewById(R.id.backList);
		back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
