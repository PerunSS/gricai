package com.cerSprikRu.CocktailMixer;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.cerSprikRu.CocktailMixer.adapter.CocktailListAdapter;
import com.cerSprikRu.CocktailMixer.model.drink.Cocktail;
import com.cerSprikRu.CocktailMixer.model.drink.CocktailCreator;

public class DisplayCocktails extends Activity {

	private ListView cocktailsView;
	private List<Cocktail> cocktailList = new ArrayList<Cocktail>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.display);
		cocktailsView = (ListView) findViewById(R.id.cocktail_list_view);
		cocktailList = CocktailCreator.getInstance().getCocktails();
		cocktailsView.setAdapter(new CocktailListAdapter(this, cocktailList));
		cocktailsView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
				Intent cocktailIntent = new Intent(v.getContext(),
						DisplayCocktail.class);
				cocktailIntent.putExtra("position", position);
				startActivity(cocktailIntent);
			}
		});
	}
}
