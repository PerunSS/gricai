package com.cerSprikRu.RNBRumorsNFacts;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
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

	private List<Fact> facts;
	private static int[] imageIDs = { R.drawable.beyonce,
			R.drawable.aliciakeys, R.drawable.rihanna, R.drawable.neyo,
			R.drawable.chrisbrown };

	private int curentIndex = 0;
	private DBManager manager;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		manager = new DBManager(this);
		facts = manager.read();
		startApplication();
	}

	private void startApplication() {
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
		
		updateUI();

	}

	private void nextClick(View v) {
		curentIndex ++;
		if(curentIndex>=facts.size())
			curentIndex = 0;
		updateUI();
	}

	private void previousClick(View v) {
		curentIndex --;
		if(curentIndex<0)
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
		factsTextView.setText(fact.getText());

		personNameTextView = (TextView) findViewById(R.id.personName);
		personNameTextView.setText(fact.getPerson());

		personImageView = (ImageView) findViewById(R.id.imageView);
		personImageView.setImageResource(imageIDs[fact.getPersonID()-1]);
		
		togleFavorite(fact);
	}

	private void togleFavorite(Fact fact) {
		if(fact.isFavorite()){
			favoriteButton.setBackgroundResource(R.drawable.favorite_yes_button);
		}else{
			favoriteButton.setBackgroundResource(R.drawable.favorite_no_button);
		}
	}

}