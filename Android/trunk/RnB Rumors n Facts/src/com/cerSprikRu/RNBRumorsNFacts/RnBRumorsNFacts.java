package com.cerSprikRu.RNBRumorsNFacts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cerSprikRu.RNBRumorsNFacts.db.customManager.DBManager;
import com.cerSprikRu.RNBRumorsNFacts.fact.Fact;

public class RnBRumorsNFacts extends Activity {

	private TextView factsTextView;
	private TextView personNameTextView;
	private ImageView personImageView;
	private Button previousButton;
	private Button nextButton;
	private Button randomButton;
	private Button favoriteButton;
	private Button openFavoriteButton;
	private Button backButton;
	private ListView favoritesView;
	private Button shareButton;
	private Button searchButton;

	private List<Fact> facts;
	private List<Fact> searchResult;
	private List<Fact> favorites;
	private static int[] imageIDs = { R.drawable.beyonce,
			R.drawable.aliciakeys, R.drawable.rihanna, R.drawable.neyo,
			R.drawable.chrisbrown };

	private int curentIndex = 0;
	private DBManager manager;

	private static RnBRumorsNFacts instance;
	
	private enum STATE {
		NORMAL, BACKABLE
	}
	
	private STATE currentState;
	
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
	
	private void doMySearch(String query){
		currentState = STATE.BACKABLE;
		String queryArr [] = query.split(" ");
		for(int i=0;i<queryArr.length;i++){
			queryArr[i].trim();
		}
		Map<Integer, List<Fact>> result = new HashMap<Integer, List<Fact>>();
		int maxWordCount = 0;
		for(Fact fact:facts){
			int wordCount = 0;
			for(String q:queryArr){
				if(fact.getText().toLowerCase().contains(q.toLowerCase()))
					wordCount++;
				if(fact.getPerson().toLowerCase().contains(q.toLowerCase()))
					wordCount++;
			}
			if(wordCount > maxWordCount)
				maxWordCount = wordCount;
			if(wordCount>0){
				List<Fact> searchFacts = result.get(wordCount);
				if(searchFacts == null)
					searchFacts = new ArrayList<Fact>();
				searchFacts.add(fact);
				result.put(wordCount, searchFacts);
			}
		}
		setContentView(R.layout.favorites);
		favoritesView = (ListView) findViewById(R.id.favorites_list_view);
		searchResult = new ArrayList<Fact>();
		while(maxWordCount > 0){
			List<Fact> res = result.get(maxWordCount);
			if(res!=null)
				searchResult.addAll(res);
			maxWordCount--;
		}
		favoritesView.setAdapter(new FavoritesListAdapter(instance, searchResult));
		favoritesView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				Fact f = searchResult.get(position);
				curentIndex = 0;
				for(Fact fact:facts){
					if(f.equals(fact)){
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

	private void startApplication() {
		currentState = STATE.NORMAL;
		setContentView(R.layout.rumorsnfacts);

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
				favorites = new ArrayList<Fact>();
				for (Fact fact : facts)
					if (fact.isFavorite())
						favorites.add(fact);
				favoritesView.setAdapter(new FavoritesListAdapter(instance,
						favorites));
				favoritesView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> a, View v, int position, long id) {
						Fact f = favorites.get(position);
						curentIndex = 0;
						for(Fact fact:facts){
							if(f.equals(fact)){
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
						"Fact about " + currentFact.getPerson() + ": "
								+ currentFact.getText());

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
		if (fact.getText().length() > 150)
			factsTextView.setTextSize(20);
		else if (fact.getText().length() > 100)
			factsTextView.setTextSize(25);
		else
			factsTextView.setTextSize(30);
		factsTextView.setText(fact.getText());

		personNameTextView = (TextView) findViewById(R.id.personName);
		personNameTextView.setText(fact.getPerson());

		personImageView = (ImageView) findViewById(R.id.imageView);
		personImageView.setImageResource(imageIDs[fact.getPersonID() - 1]);

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
		switch(keyCode){
		case KeyEvent.KEYCODE_BACK:
			if(currentState == STATE.BACKABLE){
				startApplication();
				return true;
			}
			else{
				moveTaskToBack(true);
			}
		case KeyEvent.KEYCODE_MENU:
			//TODO show menu
		case KeyEvent.KEYCODE_HOME:
			//TODO home
		case KeyEvent.KEYCODE_SEARCH:
			//TODO search
		}
		return super.onKeyDown(keyCode, event);
	}
	
	private void showSearch(){
		onSearchRequested();
	}

}