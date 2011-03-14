package com.cerSprikRu.CocktailMixer;

import com.cerSprikRu.CocktailMixer.model.drink.Cocktail;
import com.cerSprikRu.CocktailMixer.model.drink.CocktailCreator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class DisplayFavorite extends Activity{
	private Cocktail cocktail;
	private TextView recipe;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.favorite_cocktail_display);
		cocktail = CocktailCreator.getInstance().getCocktails().get(getIntent().getExtras().getInt("position"));
		recipe = (TextView)findViewById(R.id.cocktail_recipe);
		recipe.setText(cocktail.toString());
		
	}
}
