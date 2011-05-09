package com.cerSprikRu.CocktailMixer;

import com.cerSprikRu.CocktailMixer.favorites.FavoritesManager;
import com.cerSprikRu.CocktailMixer.model.drink.CocktailCreator;
import com.cerSprikRu.CocktailMixer.model.drink.Drink;
import com.cerSprikRu.CocktailMixer.model.drink.DrinkType;
import com.cerSprikRu.CocktailMixer.threads.CocktailsCreatorThread;
import com.google.ads.AdRequest;
import com.google.ads.AdView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;

public class Chooser extends Activity {

	public static Chooser instance;
	private boolean fullRandom = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		// strong drinks
		final CheckBox vodka = (CheckBox) findViewById(R.id.vodka);
		final CheckBox tequila = (CheckBox) findViewById(R.id.tequila);
		final CheckBox whisky = (CheckBox) findViewById(R.id.whisky);
		final CheckBox gin = (CheckBox) findViewById(R.id.gin);
		final CheckBox rum = (CheckBox) findViewById(R.id.rum);
		final CheckBox cognac = (CheckBox) findViewById(R.id.cognac);
		final CheckBox brandy = (CheckBox) findViewById(R.id.brandy);
		final CheckBox absinthe = (CheckBox) findViewById(R.id.absinthe);
		// liqueurs
		final CheckBox l_cherry = (CheckBox) findViewById(R.id.l_cherry);
		final CheckBox l_chocolate = (CheckBox) findViewById(R.id.l_chocolate);
		final CheckBox l_coffee = (CheckBox) findViewById(R.id.l_coffee);
		final CheckBox l_menthol = (CheckBox) findViewById(R.id.l_menthol);
		final CheckBox l_orange = (CheckBox) findViewById(R.id.l_orange);
		final CheckBox l_other = (CheckBox) findViewById(R.id.l_other);
		final CheckBox l_strawberry = (CheckBox) findViewById(R.id.l_strawberry);
		final CheckBox l_coconut = (CheckBox) findViewById(R.id.l_coconut);
		// other drinks
		final CheckBox beer = (CheckBox) findViewById(R.id.beer);
		final CheckBox vermouth = (CheckBox) findViewById(R.id.Vermouth);
		final CheckBox wine = (CheckBox) findViewById(R.id.wine);
		final CheckBox champagne = (CheckBox) findViewById(R.id.champagne);
		// non alc drinks
		final CheckBox grenadine = (CheckBox) findViewById(R.id.milk);
		final CheckBox cola = (CheckBox) findViewById(R.id.cola);
		final CheckBox j_apple = (CheckBox) findViewById(R.id.j_apple);
		final CheckBox j_lemon = (CheckBox) findViewById(R.id.j_lemon);
		final CheckBox j_orange = (CheckBox) findViewById(R.id.j_orange);
		final CheckBox j_other = (CheckBox) findViewById(R.id.j_other);
		final CheckBox m_water = (CheckBox) findViewById(R.id.m_water);
		final CheckBox bitter = (CheckBox) findViewById(R.id.bitter);

		Button favorites = (Button) findViewById(R.id.favorites);
		favorites.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent favoritesIntent = new Intent(v.getContext(),
						DisplayFavorites.class);
				startActivity(favoritesIntent);
			}
		});

		Button mix = (Button) findViewById(R.id.mix);
		mix.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				CocktailCreator.getInstance().reset();
				if (vodka.isChecked()) {
					Drink drink = new Drink();
					drink.setName("Vodka");
					drink.setType(DrinkType.A_STRONG);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if (tequila.isChecked()) {
					Drink drink = new Drink();
					drink.setName("Tequila");
					drink.setType(DrinkType.A_STRONG);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if (whisky.isChecked()) {
					Drink drink = new Drink();
					drink.setName("Whisky");
					drink.setType(DrinkType.A_STRONG);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if (gin.isChecked()) {
					Drink drink = new Drink();
					drink.setName("Gin");
					drink.setType(DrinkType.A_STRONG);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if (rum.isChecked()) {
					Drink drink = new Drink();
					drink.setName("Rum");
					drink.setAlcPercent(0.42);
					drink.setType(DrinkType.A_STRONG);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if (cognac.isChecked()) {
					Drink drink = new Drink();
					drink.setName("Cognac");
					drink.setType(DrinkType.A_STRONG);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if (brandy.isChecked()) {
					Drink drink = new Drink();
					drink.setName("Brandy");
					drink.setAlcPercent(0.42);
					drink.setType(DrinkType.A_STRONG);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if (absinthe.isChecked()) {
					Drink drink = new Drink();
					drink.setName("Absinthe");
					drink.setAlcPercent(0.65);
					drink.setType(DrinkType.A_STRONG);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if (l_cherry.isChecked()) {
					Drink drink = new Drink();
					drink.setName("Cherry type liqueur");
					drink.setType(DrinkType.B_LIQUEUR);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if (l_chocolate.isChecked()) {
					Drink drink = new Drink();
					drink.setName("Chocolate type liqueur");
					drink.setType(DrinkType.B_LIQUEUR);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if (l_coffee.isChecked()) {
					Drink drink = new Drink();
					drink.setName("Coffee type liqueur");
					drink.setType(DrinkType.B_LIQUEUR);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if (l_menthol.isChecked()) {
					Drink drink = new Drink();
					drink.setName("Menthol type liqueur");
					drink.setType(DrinkType.B_LIQUEUR);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if (l_orange.isChecked()) {
					Drink drink = new Drink();
					drink.setName("Orange type liqueur");
					drink.setType(DrinkType.B_LIQUEUR);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if (l_strawberry.isChecked()) {
					Drink drink = new Drink();
					drink.setName("Strawberry type liqueur");
					drink.setType(DrinkType.B_LIQUEUR);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if (l_coconut.isChecked()) {
					Drink drink = new Drink();
					drink.setName("Coconut type liqueur");
					drink.setType(DrinkType.B_LIQUEUR);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if (l_other.isChecked()) {
					Drink drink = new Drink();
					drink.setName("Other type liqueur");
					drink.setType(DrinkType.B_LIQUEUR);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if (beer.isChecked()) {
					Drink drink = new Drink();
					drink.setName("Beer");
					drink.setAlcPercent(0.05);
					drink.setType(DrinkType.C_OTHER);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if (wine.isChecked()) {
					Drink drink = new Drink();
					drink.setName("Wine");
					drink.setType(DrinkType.C_OTHER);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if (vermouth.isChecked()) {
					Drink drink = new Drink();
					drink.setName("Vermouth");
					drink.setAlcPercent(0.18);
					drink.setType(DrinkType.C_OTHER);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if (champagne.isChecked()) {
					Drink drink = new Drink();
					drink.setName("Champagne");
					drink.setType(DrinkType.C_OTHER);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if (j_apple.isChecked()) {
					Drink drink = new Drink();
					drink.setName("Apple juice");
					drink.setType(DrinkType.D_NON_ALC);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if (j_orange.isChecked()) {
					Drink drink = new Drink();
					drink.setName("Orange juice");
					drink.setType(DrinkType.D_NON_ALC);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if (j_other.isChecked()) {
					Drink drink = new Drink();
					drink.setName("Other juice");
					drink.setType(DrinkType.D_NON_ALC);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if (j_lemon.isChecked()) {
					Drink drink = new Drink(true);
					drink.setName("Lemon juice");
					drink.setType(DrinkType.D_NON_ALC);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if (m_water.isChecked()) {
					Drink drink = new Drink();
					drink.setName("Tonic water");
					drink.setType(DrinkType.D_NON_ALC);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if (cola.isChecked()) {
					Drink drink = new Drink();
					drink.setName("Cola");
					drink.setType(DrinkType.D_NON_ALC);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if (bitter.isChecked()) {
					Drink drink = new Drink();
					drink.setName("Bitter");
					drink.setType(DrinkType.D_NON_ALC);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if (grenadine.isChecked()) {
					Drink drink = new Drink(true);
					drink.setName("Grenadine");
					drink.setType(DrinkType.D_NON_ALC);
					CocktailCreator.getInstance().addDrink(drink);
				}

				if (CocktailCreator.getInstance().size() < 5) {
					showError("If You have less than 5 drinks, You dont need cocktail mixer for that, just experiment. ;)");
				} else if (CocktailCreator.getInstance().canMix()) {
					CocktailsCreatorThread cct = new CocktailsCreatorThread(
							"cct", fullRandom);
					try {
						cct.t.join();
					} catch (InterruptedException e) {
					}
					Intent myIntent = new Intent(v.getContext(),
							DisplayCocktails.class);
					startActivityForResult(myIntent, 0);
				} else {
					showError("You cannot make cocktails with only strong drinks!");
				}

			}
		});
		AdView adView1 = (AdView) findViewById(R.id.adView1);
		adView1.loadAd(new AdRequest());
		AdView adView2 = (AdView) findViewById(R.id.adView2);
		adView2.loadAd(new AdRequest());
		new FavoritesManager(this);
		instance = this;
	}

	private void showError(String text) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(text);
		builder.setTitle("cannot create");
		builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
	}
}
