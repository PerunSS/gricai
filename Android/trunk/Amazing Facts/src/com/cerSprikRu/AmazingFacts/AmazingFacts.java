package com.cerSprikRu.AmazingFacts;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.cerSprikRu.AmazingFacts.adapter.FavoriteAdapter;
import com.cerSprikRu.AmazingFacts.db.customManager.DBManager;
import com.cerSprikRu.AmazingFacts.fact.Fact;

public class AmazingFacts extends Activity {

	private TextView factsTextView;
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

	private int curentIndex = 0;
	private DBManager manager;

	private static AmazingFacts instance;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		manager = new DBManager(this);
		facts = manager.read();
		startApplication();
		instance = this;
		
	}

	private void startApplication() {
		setContentView(R.layout.amazingfacts);

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
				setContentView(R.layout.favorites);
				favoritesView = (ListView) findViewById(R.id.favorites_list_view);
				List<Fact> favorites = new ArrayList<Fact>();
				for (Fact fact : facts)
					if (fact.isFavorite())
						favorites.add(fact);
				favoritesView.setAdapter(new FavoriteAdapter(instance, R.id.favorites_list_view, favorites));

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
				intent.putExtra(Intent.EXTRA_TEXT, "Amazing fact: "
						+ currentFact.getText());

				startActivity(Intent.createChooser(intent, "chooser: test"));
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
			//TODO back;
		case KeyEvent.KEYCODE_MENU:
			//TODO show menu
		case KeyEvent.KEYCODE_HOME:
			//TODO home
		}
		return super.onKeyDown(keyCode, event);
	}
	
	private void showSearch(){
		
	}
	
	private List<Fact> search(String[] keywords){
		return null;
	}

}