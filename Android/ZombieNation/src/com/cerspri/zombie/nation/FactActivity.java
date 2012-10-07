package com.cerspri.zombie.nation;

import com.cerspri.zombie.nation.model.Model;
import com.cerspri.zombie.nation.model.Quote;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class FactActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fact);
		ImageView view = (ImageView) findViewById(R.id.image);
		view.setImageResource(Model.getInstance().getCurrentImage());
		setText(Model.getInstance().currentQuote());
	}

	public void clickNext(View v) {
		setText(Model.getInstance().nextQuote());
	}

	public void clickPrev(View v) {
		setText(Model.getInstance().previousQuote());
	}

	public void clickRandom(View v) {
		setText(Model.getInstance().randomQuote());
	}

	public void clickShare(View v) {
		String text = Model.getInstance().getCurrentPerson() + " zombie: ";
		Quote quote = Model.getInstance().currentQuote();
		if (quote == null)
			return;
		text += quote.getText();
		final Intent intent = new Intent(Intent.ACTION_SEND);

		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_SUBJECT, "Zombie Nation!");
		intent.putExtra(Intent.EXTRA_TEXT, text);

		startActivity(Intent.createChooser(intent, "Zombie Nation!"));
	}

	public void clickFav(View v) {
		Model.getInstance().currentQuote().toogleFavorite();
		setFavButton(Model.getInstance().currentQuote());
		Model.getInstance().updateCurrentFavorite(this);
	}

	private void setText(Quote quote) {
		if (quote == null)
			return;
		TextView view = (TextView) findViewById(R.id.text);
		view.setText(quote.getText());
		setFavButton(quote);
	}

	private void setFavButton(Quote quote) {
		if (quote == null)
			return;
		ImageButton button = (ImageButton) findViewById(R.id.fav);
		if (quote.isFavorite()) {
			button.setImageResource(android.R.drawable.star_big_on);
		} else {
			button.setImageResource(android.R.drawable.star_big_off);
		}
	}

}
