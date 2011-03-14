package com.cerSprikRu.CocktailMixer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.cerSprikRu.CocktailMixer.adapter.CocktailListAdapter;
import com.cerSprikRu.CocktailMixer.favorites.FavoritesManager;
import com.cerSprikRu.CocktailMixer.model.drink.Cocktail;

public class DisplayFavorites extends Activity {

	private ListView favoriteCocktailsView;
	private List<Cocktail> favoriteCocktailList = new ArrayList<Cocktail>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.favorites_display);
		favoriteCocktailsView = (ListView) findViewById(R.id.favorites_cocktail_list_view);
		favoriteCocktailsView.setAdapter(new CocktailListAdapter(this,
				favoriteCocktailList));
		// favoriteCocktailsView.setOnItemClickListener(new
		// OnItemClickListener() {
		//
		// public void onItemClick(AdapterView<?> a, View v, int position,
		// long id) {
		// Intent cocktailIntent = new Intent(v.getContext(),
		// DisplayFavorite.class);
		// System.out.println(position);
		// cocktailIntent.putExtra("favorite",
		// favoriteCocktailList.get(position));
		// startActivity(cocktailIntent);
		// }
		// });
	}

	@Override
	protected void onResume() {
		super.onResume();
		favoriteCocktailsView = (ListView) findViewById(R.id.favorites_cocktail_list_view);
		if (FavoritesManager.getInstance() == null)
			new FavoritesManager(CocktailMixer.instance);
		Set<Cocktail> cocktailsSet = FavoritesManager.getInstance()
				.getFavorites();
		favoriteCocktailList.clear();
		for (Cocktail cocktail : cocktailsSet) {
			favoriteCocktailList.add(cocktail);
		}
		favoriteCocktailsView.setAdapter(new CocktailListAdapter(this,
				favoriteCocktailList));
		favoriteCocktailsView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
				Intent cocktailIntent = new Intent(v.getContext(),
						DisplayFavorite.class);
				cocktailIntent.putExtra("favorite",
						favoriteCocktailList.get(position));
				startActivity(cocktailIntent);
			}
		});
	}
	

}
