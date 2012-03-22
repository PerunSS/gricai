package com.funforall.bakiapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

public class ReadingActivity extends Activity {

	int current = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text_viewer);
		handleIntent(getIntent());
		final TextView text = (TextView) findViewById(R.id.text);
		Text initial = Model.getInstance().getText(current);
		if(initial == null){
			finish();
			return;
		}
		text.setText(initial.text);

		final Button favorite = (Button) findViewById(R.id.favourite);
		favorite.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Model.getInstance().toogleFavorite(current);
				if (Model.getInstance().getText(current).isFavorite)
					favorite.setBackgroundResource(R.drawable.button_favourite_active);
				else
					favorite.setBackgroundResource(R.drawable.button_favourite);
			}
		});

		final Button next = (Button) findViewById(R.id.next);
		next.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				current++;
				text.setText(Model.getInstance().getText(current).text);
				if (Model.getInstance().getText(current).isFavorite)
					favorite.setBackgroundResource(R.drawable.button_favourite_active);
				else
					favorite.setBackgroundResource(R.drawable.button_favourite);
			}
		});

		final Button previous = (Button) findViewById(R.id.previous);
		previous.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				current--;
				if (current < 0)
					current = Model.getInstance().getSize() - 1;
				text.setText(Model.getInstance().getText(current).text);
				if (Model.getInstance().getText(current).isFavorite)
					favorite.setBackgroundResource(R.drawable.button_favourite_active);
				else
					favorite.setBackgroundResource(R.drawable.button_favourite);
			}
		});

		final Button share = (Button) findViewById(R.id.share);
		share.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("text/plain");
				intent.putExtra(Intent.EXTRA_SUBJECT,
						getString(R.string.app_name));
				intent.putExtra(Intent.EXTRA_TEXT,
						Model.getInstance().getText(current).text);

				startActivity(Intent.createChooser(intent, "Share via"));
			}
		});

		final Button back = (Button) findViewById(R.id.back);
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), Constants.APPLICATION);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
		
		final Button search = (Button) findViewById(R.id.search);
		search.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				showSearch();

			}
		});

		AdView view = (AdView) findViewById(R.id.adView);
		view.loadAd(new AdRequest());
	}

	@Override
	protected void onNewIntent(Intent intent) {
		setIntent(intent);
		handleIntent(intent);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent(getApplicationContext(), Constants.APPLICATION);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			//finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	private void handleIntent(Intent intent) {
		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			String query = intent.getStringExtra(SearchManager.QUERY);
			doMySearch(query);
		}
	}

	private void doMySearch(String query) {
		Log.i("SEARCH", query);
		String queryArr[] = query.split(" ");
		for (int i = 0; i < queryArr.length; i++) {
			queryArr[i].trim();
		}
		Map<Integer, List<Text>> result = new HashMap<Integer, List<Text>>();
		for (int i = 0; i < Model.getInstance().getSize(); i++) {
			Text text = Model.getInstance().getText(i);
			int wordCount = 0;
			for (String q : queryArr) {
				if (text.text.toLowerCase().contains(q.toLowerCase()))
					wordCount++;
			}
			if (wordCount > 0) {
				List<Text> searchFacts = result.get(wordCount);
				if (searchFacts == null)
					searchFacts = new ArrayList<Text>();
				searchFacts.add(text);
				result.put(wordCount, searchFacts);
			}
		}
		// setContentView(R.layout.search);
		// searchView = (ListView) findViewById(R.id.search_list_view);
		List<Text> searchResult = new ArrayList<Text>();
		int maxCount = queryArr.length;
		while (maxCount > 0) {
			List<Text> res = result.get(maxCount);
			if (res != null)
				searchResult.addAll(res);
			maxCount--;
		}
		Model.getInstance().setSearchResult(searchResult);
		Intent intent = new Intent(this, ListActivity.class);
		intent.putExtra("search", true);
		startActivity(intent);
	}

	private void showSearch() {
		onSearchRequested();
	}
}
