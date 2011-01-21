package com.cerSprikRu.RNBRumorsNFacts;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cerSprikRu.RNBRumorsNFacts.db.customManager.DBManager;
import com.cerSprikRu.RNBRumorsNFacts.person.Person;

public class RnBRumorsNFacts extends Activity {

	private TextView factsTextView;
	private TextView personNameTextView;
	private ImageView personImageView;
	private Button previousButton;
	private Button nextButton;
	private Button randomButton;

	private List<Person> persons;
	private static int[] imageIDs = { R.drawable.beyonce,
			R.drawable.aliciakeys, R.drawable.rihanna, R.drawable.neyo,
			R.drawable.chrisbrown };

	enum Action {
		PREVIOUS, RANDOM, NEXT, NONE
	}

	private Action lastAction;

	private int curentIndex = 0;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DBManager manager = new DBManager(this);
		persons = manager.read();
		lastAction = Action.NONE;
		startApplication();

	}

	private Person curentPerson() {
		if (curentIndex >= persons.size())
			curentIndex = 0;
		if (curentIndex < 0)
			curentIndex = persons.size() - 1;
		Log.d("CURRENT INDEX", "current index is :" + curentIndex);
		return persons.get(curentIndex);
	}

	private void startApplication() {
		setContentView(R.layout.rumorsnfacts);

		String rumorText = null;
		String personName = null;
		if (lastAction == Action.PREVIOUS) {
			curentIndex++;
		}
		do {
			rumorText = curentPerson().getNext();
			personName = curentPerson().getName();
			if (rumorText == null) {
				curentPerson().resetIndex();
				curentIndex++;
			}
		} while (rumorText == null);
		lastAction = Action.NEXT;
		factsTextView = (TextView) findViewById(R.id.factText);
		factsTextView.setText(rumorText);

		personNameTextView = (TextView) findViewById(R.id.personName);
		personNameTextView.setText(personName);

		personImageView = (ImageView) findViewById(R.id.imageView);
		personImageView.setImageResource(imageIDs[curentIndex]);

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

		String rumorText = null;
		String personName = null;
		if (lastAction == Action.PREVIOUS) {
			curentIndex++;
		}
		do {
			rumorText = curentPerson().getNext();
			personName = curentPerson().getName();
			if (rumorText == null) {
				curentPerson().resetIndex();
				curentIndex++;
			}
		} while (rumorText == null);
		lastAction = Action.NEXT;
		factsTextView = (TextView) findViewById(R.id.factText);
		factsTextView.setText(rumorText);

		personNameTextView = (TextView) findViewById(R.id.personName);
		personNameTextView.setText(personName);

		personImageView = (ImageView) findViewById(R.id.imageView);
		personImageView.setImageResource(imageIDs[curentIndex]);
		
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

		String rumorText = null;
		String personName = null;
		if (lastAction == Action.NEXT) {
			curentIndex--;
		}
		do {
			rumorText = curentPerson().getPrevious();
			personName = curentPerson().getName();
			if (rumorText == null) {
				curentPerson().lastINdex();
				curentIndex--;
			}
		} while (rumorText == null);
		lastAction = Action.PREVIOUS;
		factsTextView = (TextView) findViewById(R.id.factText);
		factsTextView.setText(rumorText);

		personNameTextView = (TextView) findViewById(R.id.personName);
		personNameTextView.setText(personName);

		personImageView = (ImageView) findViewById(R.id.imageView);
		personImageView.setImageResource(imageIDs[curentIndex]);
		
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

		String rumorText = null;
		String personName = null;
		for (Person p : persons)
			p.resetIndex();
		do {
			curentIndex = (int) (Math.random() * persons.size());
			rumorText = curentPerson().getRandom();
			personName = curentPerson().getName();
			if (rumorText == null) {
				curentPerson().resetIndex();
			}
		} while (rumorText == null);
		lastAction = Action.RANDOM;
		factsTextView = (TextView) findViewById(R.id.factText);
		factsTextView.setText(rumorText);

		personNameTextView = (TextView) findViewById(R.id.personName);
		personNameTextView.setText(personName);
		
		personImageView = (ImageView) findViewById(R.id.imageView);
		personImageView.setImageResource(imageIDs[curentIndex]);

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