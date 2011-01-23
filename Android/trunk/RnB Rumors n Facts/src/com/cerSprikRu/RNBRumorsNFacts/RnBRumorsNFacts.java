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

	private List<Fact> facts;
	private static int[] imageIDs = { R.drawable.beyonce,
			R.drawable.aliciakeys, R.drawable.rihanna, R.drawable.neyo,
			R.drawable.chrisbrown };

//	enum Action {
//		PREVIOUS, RANDOM, NEXT, NONE
//	}
//
//	private Action lastAction;

	private int curentIndex = 0;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DBManager manager = new DBManager(this);
		facts = manager.read();
//		lastAction = Action.NONE;
		startApplication();

	}

//	private Person curentPerson() {
//		if (curentIndex >= facts.size())
//			curentIndex = 0;
//		if (curentIndex < 0)
//			curentIndex = facts.size() - 1;
//		Log.d("CURRENT INDEX", "current index is :" + curentIndex);
//		return facts.get(curentIndex);
//	}

	private void startApplication() {
		setContentView(R.layout.rumorsnfacts);

		Fact fact = facts.get(curentIndex);
		factsTextView = (TextView) findViewById(R.id.factText);
		factsTextView.setText(fact.getText());

		personNameTextView = (TextView) findViewById(R.id.personName);
		personNameTextView.setText(fact.getPerson());

		personImageView = (ImageView) findViewById(R.id.imageView);
		personImageView.setImageResource(imageIDs[fact.getPersonID()-1]);

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

	}

	private void nextClick(View v) {
		setContentView(R.layout.rumorsnfacts);
		
		curentIndex ++;
		if(curentIndex>=facts.size())
			curentIndex = 0;
		Fact fact = facts.get(curentIndex);
		
		factsTextView = (TextView) findViewById(R.id.factText);
		factsTextView.setText(fact.getText());

		personNameTextView = (TextView) findViewById(R.id.personName);
		personNameTextView.setText(fact.getPerson());

		personImageView = (ImageView) findViewById(R.id.imageView);
		personImageView.setImageResource(imageIDs[fact.getPersonID()-1]);
		
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
	}

	private void previousClick(View v) {
		setContentView(R.layout.rumorsnfacts);
	
		curentIndex --;
		if(curentIndex<facts.size())
			curentIndex = facts.size() - 1;
		Fact fact = facts.get(curentIndex);
		factsTextView = (TextView) findViewById(R.id.factText);
		factsTextView.setText(fact.getText());

		personNameTextView = (TextView) findViewById(R.id.personName);
		personNameTextView.setText(fact.getPerson());

		personImageView = (ImageView) findViewById(R.id.imageView);
		personImageView.setImageResource(imageIDs[fact.getPersonID()-1]);
		
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
	}

	private void randomClick(View v) {
		setContentView(R.layout.rumorsnfacts);

		curentIndex = (int) (Math.random() * facts.size());
		Fact fact = facts.get(curentIndex);
		
		factsTextView = (TextView) findViewById(R.id.factText);
		factsTextView.setText(fact.getText());

		personNameTextView = (TextView) findViewById(R.id.personName);
		personNameTextView.setText(fact.getPerson());

		personImageView = (ImageView) findViewById(R.id.imageView);
		personImageView.setImageResource(imageIDs[fact.getPersonID()-1]);

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
	}

}