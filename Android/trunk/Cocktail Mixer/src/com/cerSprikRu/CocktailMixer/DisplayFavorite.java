package com.cerSprikRu.CocktailMixer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.cerSprikRu.CocktailMixer.favorites.FavoritesManager;
import com.cerSprikRu.CocktailMixer.model.drink.Cocktail;

public class DisplayFavorite extends Activity{
	private Cocktail cocktail;
	private TextView recipe;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.favorite_cocktail_display);
		cocktail = (Cocktail) getIntent().getExtras().get("favorite");
		recipe = (TextView)findViewById(R.id.favorite_cocktail_recipe);
		recipe.setText(cocktail.toFavoritesString());
		
		final Button shareButton = (Button) findViewById(R.id.share_favorite_cocktail);
		shareButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final Intent intent = new Intent(Intent.ACTION_SEND);

				intent.setType("text/plain");
				intent.putExtra(Intent.EXTRA_TEXT,
						"Cocktail recipe: " + cocktail.toFavoritesString());

				startActivity(Intent.createChooser(intent, "share"));
			}
		});
		
		final Button favButton = (Button) findViewById(R.id.remove_from_favorites);
		favButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				FavoritesManager.getInstance().togleFavorite(cocktail);
				finish();
			}
		});
		
	}
}
