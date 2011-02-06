package com.cerSprikRu.TrueFacts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.cerSprikRu.TrueFacts.adapter.FavoriteAdapter;
import com.cerSprikRu.TrueFacts.db.customManager.DBManager;
import com.cerSprikRu.TrueFacts.fact.Fact;

public class TrueFacts extends Activity {

	private TextView factsTextView;
	private TextView factsPublisherTextView;
	private Button previousButton;
	private Button nextButton;
	private Button randomButton;
	private Button favoriteButton;
	private Button openFavoriteButton;
	private Button backButton;
	private ListView favoritesView;
	private Button shareButton;
	private Button searchButton;
	private ListView searchView;

	private List<Fact> facts;
	private List<Fact> searchResult;
	private List<Fact> favorites;

	private int curentIndex = 0;
	private DBManager manager;

	private enum STATE {
		NORMAL, BACKABLE
	}

	private STATE currentState;

	private static TrueFacts instance;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		manager = new DBManager(this);
		facts = manager.read();
		startApplication();
		instance = this;
		handleIntent(getIntent());
	}

	@Override
	protected void onNewIntent(Intent intent) {
		setIntent(intent);
		handleIntent(intent);
	}

	private void handleIntent(Intent intent) {
		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			String query = intent.getStringExtra(SearchManager.QUERY);
			doMySearch(query);
		}
	}

	private void doMySearch(String query) {
		String queryArr[] = query.split(" ");
		for (int i = 0; i < queryArr.length; i++) {
			queryArr[i].trim();
		}
		Map<Integer, List<Fact>> result = new HashMap<Integer, List<Fact>>();
		int maxCount = 0;
		for (Fact fact : facts) {
			int wordCount = 0;
			for (String q : queryArr) {
				if (fact.getText().toLowerCase().contains(q.toLowerCase()))
					wordCount++;
				if (fact.getPublisher().toLowerCase().contains(q.toLowerCase()))
					wordCount++;
			}
			if(wordCount > maxCount)
				maxCount = wordCount;
			if (wordCount > 0) {
				List<Fact> searchFacts = result.get(wordCount);
				if (searchFacts == null)
					searchFacts = new ArrayList<Fact>();
				searchFacts.add(fact);
				result.put(wordCount, searchFacts);
			}
		}
		searchResult = null;
		searchResult = new ArrayList<Fact>();
		while (maxCount > 0) {
			List<Fact> res = result.get(maxCount);
			if (res != null)
				searchResult.addAll(res);
			maxCount--;
		}
		if (searchResult.isEmpty()) {
			AlertDialog.Builder adb = new AlertDialog.Builder(this);
			adb.setMessage("No results for query: " + query)
					.setCancelable(false)
					.setNeutralButton("OK",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.cancel();
								}
							});
			AlertDialog dialog = adb.create();
			dialog.setTitle("Search");
			adb.show();
			return;
		}
		currentState = STATE.BACKABLE;
		setContentView(R.layout.search);
		searchView = (ListView) findViewById(R.id.search_list_view);

		searchView.setDivider(new ColorDrawable(Color.GRAY));
		searchView.setDividerHeight(2);
		searchView.setAdapter(new FavoriteAdapter(instance,
				R.id.search_list_view, searchResult));
		searchView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
				Fact f = searchResult.get(position);
				curentIndex = 0;
				for (Fact fact : facts) {
					if (f.equals(fact)) {
						startApplication();
						return;
					}
					curentIndex++;
				}

			}
		});

		backButton = (Button) findViewById(R.id.back_from_search);
		backButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startApplication();
			}
		});

	}

	private void startApplication() {
		setContentView(R.layout.truefacts);
		currentState = STATE.NORMAL;
		previousButton = (Button) findViewById(R.id.previous);
		previousButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				previousClick(v);
			}
		});

		nextButton = (Button) findViewById(R.id.next);
		nextButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				nextClick(v);
			}
		});

		randomButton = (Button) findViewById(R.id.random);
		randomButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				randomClick(v);
			}
		});

		favoriteButton = (Button) findViewById(R.id.addToFavorites);
		favoriteButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Fact fact = facts.get(curentIndex);
				fact.togleFavorite();
				manager.updateFact(fact);
				togleFavorite(fact);
			}
		});

		openFavoriteButton = (Button) findViewById(R.id.openFavorites);
		openFavoriteButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				currentState = STATE.BACKABLE;
				setContentView(R.layout.favorites);
				favoritesView = (ListView) findViewById(R.id.favorites_list_view);
				favorites = null;
				favorites = new ArrayList<Fact>();
				for (Fact fact : facts)
					if (fact.isFavorite())
						favorites.add(fact);
				favoritesView.setDivider(new ColorDrawable(Color.GRAY));
				favoritesView.setDividerHeight(2);
				favoritesView.setAdapter(new FavoriteAdapter(instance,
						R.id.favorites_list_view, favorites));
				favoritesView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> a, View v,
							int position, long id) {
						Fact f = favorites.get(position);
						curentIndex = 0;
						for (Fact fact : facts) {
							if (f.equals(fact)) {
								startApplication();
								return;
							}
							curentIndex++;
						}

					}
				});

				backButton = (Button) findViewById(R.id.back_from_favorites);
				backButton.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						startApplication();
					}
				});
			}
		});

		shareButton = (Button) findViewById(R.id.share);
		shareButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Fact currentFact = facts.get(curentIndex);
				final Intent intent = new Intent(Intent.ACTION_SEND);

				intent.setType("text/plain");
				intent.putExtra(Intent.EXTRA_TEXT,
						"False fact: " + currentFact.getText());

				startActivity(Intent.createChooser(intent, "share"));
			}
		});

		searchButton = (Button) findViewById(R.id.search);
		searchButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showSearch();

			}
		});

		updateUI();

	}

	private void nextClick(View v) {
		curentIndex++;
		if (curentIndex >= facts.size())
			curentIndex = 0;
		updateUI();
	}

	private void previousClick(View v) {
		curentIndex--;
		if (curentIndex < 0)
			curentIndex = facts.size() - 1;
		updateUI();
	}

	private void randomClick(View v) {
		curentIndex = (int) (Math.random() * facts.size());
		updateUI();
	}

	private void updateUI() {
		Fact fact = facts.get(curentIndex);
		factsTextView = (TextView) findViewById(R.id.factText);
		if (fact.getText().length() > 200)
			factsTextView.setTextSize(17);
		else if (fact.getText().length() > 150)
			factsTextView.setTextSize(20);
		else if (fact.getText().length() > 100)
			factsTextView.setTextSize(25);
		else
			factsTextView.setTextSize(30);
		factsTextView.setText(fact.getText());
		
		factsPublisherTextView = (TextView) findViewById(R.id.factPublisher);
		factsPublisherTextView.setText(fact.getPublisher());
		
		togleFavorite(fact);
	}

	private void togleFavorite(Fact fact) {
		if (fact.isFavorite()) {
			favoriteButton
					.setBackgroundResource(R.drawable.favorite_yes_button);
		} else {
			favoriteButton.setBackgroundResource(R.drawable.favorite_no_button);
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			if (currentState == STATE.BACKABLE) {
				startApplication();
				return true;
			} else {
				return moveTaskToBack(true);
			}
		case KeyEvent.KEYCODE_MENU:
			// TODO show menu
		case KeyEvent.KEYCODE_HOME:
			// TODO home
		case KeyEvent.KEYCODE_SEARCH:
			// TODO search
		}
		return super.onKeyDown(keyCode, event);
	}

	private void showSearch() {
		onSearchRequested();
	}

}