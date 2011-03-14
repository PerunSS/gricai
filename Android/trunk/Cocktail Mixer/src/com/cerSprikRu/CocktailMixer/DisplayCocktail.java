package com.cerSprikRu.CocktailMixer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.admob.android.ads.AdManager;
import com.admob.android.ads.AdView;
import com.cerSprikRu.CocktailMixer.favorites.FavoritesManager;
import com.cerSprikRu.CocktailMixer.model.drink.Cocktail;
import com.cerSprikRu.CocktailMixer.model.drink.CocktailCreator;

public class DisplayCocktail extends Activity{
	
	private Cocktail cocktail;
	private TextView recipe;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.cocktail_display);
		cocktail = CocktailCreator.getInstance().getCocktails().get(getIntent().getExtras().getInt("position"));
		recipe = (TextView)findViewById(R.id.cocktail_recipe);
		recipe.setText(cocktail.toString());
		
		final Button shareButton = (Button) findViewById(R.id.share_cocktail);
		shareButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final Intent intent = new Intent(Intent.ACTION_SEND);

				intent.setType("text/plain");
				intent.putExtra(Intent.EXTRA_TEXT,
						"Cocktail recipe: " + cocktail.toString());

				startActivity(Intent.createChooser(intent, "share"));
			}
		});
		
		final Button favButton = (Button) findViewById(R.id.add_to_favorites);
		favButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				FavoritesManager.getInstance().togleFavorite(cocktail);
				favButton.setEnabled(false);
			}
		});
		if(FavoritesManager.getInstance().getFavorites().contains(cocktail))
			favButton.setEnabled(false);
		
		AdManager.setTestDevices(new String[]{
	            AdManager.TEST_EMULATOR });
	        AdView view3 = (AdView)findViewById(R.id.ad3);
	        view3.requestFreshAd();
	        
	}
}
