package com.cerSprikRu.CocktailMixer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;

import com.admob.android.ads.AdManager;
import com.admob.android.ads.AdView;
import com.cerSprikRu.CocktailMixer.favorites.FavoritesManager;
import com.cerSprikRu.CocktailMixer.model.drink.CocktailCreator;
import com.cerSprikRu.CocktailMixer.model.drink.Drink;
import com.cerSprikRu.CocktailMixer.model.drink.DrinkType;

public class CocktailMixer extends Activity {
	
	public static CocktailMixer instance;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        //strong drinks
        final CheckBox vodka = (CheckBox) findViewById(R.id.vodka);
        final CheckBox tequila = (CheckBox) findViewById(R.id.tequila);
        final CheckBox whisky = (CheckBox) findViewById(R.id.whisky);
        final CheckBox gin = (CheckBox) findViewById(R.id.gin);
        final CheckBox rum = (CheckBox) findViewById(R.id.rum);
        final CheckBox cognac = (CheckBox) findViewById(R.id.cognac);
        final CheckBox brandy = (CheckBox) findViewById(R.id.brandy);
        final CheckBox absinthe = (CheckBox) findViewById(R.id.absinthe);
        //liqueurs
        final CheckBox l_cherry = (CheckBox) findViewById(R.id.l_cherry);
        final CheckBox l_chocolate = (CheckBox) findViewById(R.id.l_chocolate);
        final CheckBox l_coffee = (CheckBox) findViewById(R.id.l_coffee);
        final CheckBox l_menthol = (CheckBox) findViewById(R.id.l_menthol);
        final CheckBox l_orange = (CheckBox) findViewById(R.id.l_orange);
        final CheckBox l_strawberry = (CheckBox) findViewById(R.id.l_strawberry);
        //other drinks
        final CheckBox beer = (CheckBox) findViewById(R.id.beer);
        final CheckBox vermouth = (CheckBox) findViewById(R.id.Vermouth);
        final CheckBox wine = (CheckBox) findViewById(R.id.wine);
        final CheckBox champagne = (CheckBox) findViewById(R.id.champagne);
        //non alc drinks
        final CheckBox milk = (CheckBox) findViewById(R.id.milk);
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
				if(vodka.isChecked()){
					Drink drink = new Drink();
					drink.setName("vodka");
					drink.setType(DrinkType.STRONG);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if(tequila.isChecked()){
					Drink drink = new Drink();
					drink.setName("tequila");
					drink.setType(DrinkType.STRONG);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if(whisky.isChecked()){
					Drink drink = new Drink();
					drink.setName("whisky");
					drink.setType(DrinkType.STRONG);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if(gin.isChecked()){
					Drink drink = new Drink();
					drink.setName("gin");
					drink.setType(DrinkType.STRONG);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if(rum.isChecked()){
					Drink drink = new Drink();
					drink.setName("rum");
					drink.setType(DrinkType.STRONG);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if(cognac.isChecked()){
					Drink drink = new Drink();
					drink.setName("cognac");
					drink.setType(DrinkType.STRONG);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if(brandy.isChecked()){
					Drink drink = new Drink();
					drink.setName("brandy");
					drink.setType(DrinkType.STRONG);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if(absinthe.isChecked()){
					Drink drink = new Drink();
					drink.setName("absinthe");
					drink.setType(DrinkType.STRONG);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if(l_cherry.isChecked()){
					Drink drink = new Drink();
					drink.setName("cherry type liqueur");
					drink.setType(DrinkType.LIQUEUR);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if(l_chocolate.isChecked()){
					Drink drink = new Drink();
					drink.setName("chocolate type liqueur");
					drink.setType(DrinkType.LIQUEUR);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if(l_coffee.isChecked()){
					Drink drink = new Drink();
					drink.setName("coffee type liqueur");
					drink.setType(DrinkType.LIQUEUR);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if(l_menthol.isChecked()){
					Drink drink = new Drink();
					drink.setName("menthol type liqueur");
					drink.setType(DrinkType.LIQUEUR);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if(l_orange.isChecked()){
					Drink drink = new Drink();
					drink.setName("orange type liqueur");
					drink.setType(DrinkType.LIQUEUR);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if(l_strawberry.isChecked()){
					Drink drink = new Drink();
					drink.setName("Other type liqueur");
					drink.setType(DrinkType.LIQUEUR);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if(beer.isChecked()){
					Drink drink = new Drink();
					drink.setName("beer");
					drink.setType(DrinkType.OTHER);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if(wine.isChecked()){
					Drink drink = new Drink();
					drink.setName("wine");
					drink.setType(DrinkType.OTHER);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if(vermouth.isChecked()){
					Drink drink = new Drink();
					drink.setName("vermouth");
					drink.setType(DrinkType.OTHER);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if(champagne.isChecked()){
					Drink drink = new Drink();
					drink.setName("champagne");
					drink.setType(DrinkType.OTHER);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if(j_apple.isChecked()){
					Drink drink = new Drink();
					drink.setName("apple juice");
					drink.setType(DrinkType.NON_ALC);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if(j_orange.isChecked()){
					Drink drink = new Drink();
					drink.setName("orange juice");
					drink.setType(DrinkType.NON_ALC);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if(j_other.isChecked()){
					Drink drink = new Drink();
					drink.setName("other juice");
					drink.setType(DrinkType.NON_ALC);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if(j_lemon.isChecked()){
					Drink drink = new Drink();
					drink.setName("lemon juice");
					drink.setType(DrinkType.NON_ALC);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if(m_water.isChecked()){
					Drink drink = new Drink();
					drink.setName("Tonic water");
					drink.setType(DrinkType.NON_ALC);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if(cola.isChecked()){
					Drink drink = new Drink();
					drink.setName("cola");
					drink.setType(DrinkType.NON_ALC);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if(bitter.isChecked()){
					Drink drink = new Drink();
					drink.setName("bitter");
					drink.setType(DrinkType.NON_ALC);
					CocktailCreator.getInstance().addDrink(drink);
				}
				if(milk.isChecked()){
					Drink drink = new Drink();
					drink.setName("milk");
					drink.setType(DrinkType.NON_ALC);
					CocktailCreator.getInstance().addDrink(drink);
				}
				
				if(CocktailCreator.getInstance().size() < 5){
					showError();
				}else {
					CocktailCreator.getInstance().createCocktails();
					Intent myIntent = new Intent(v.getContext(), DisplayCocktails.class);
	                startActivityForResult(myIntent, 0);
				}
				
				
			}
		});
        new FavoritesManager(this);
        instance = this;
        
        AdManager.setTestDevices(new String[]{
	            AdManager.TEST_EMULATOR });
	        AdView view1 = (AdView)findViewById(R.id.ad1);
	        view1.requestFreshAd();
	        AdView view2 = (AdView)findViewById(R.id.ad2);
	        view2.requestFreshAd();
        
    }
    
    private void showError(){
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setMessage("if u have less than 5 drinks, u dont need cocktail mixer for that, just experiment ;)");
		builder.setTitle("cannot create");
		builder.setNeutralButton("OK",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog,
                            int which) {
                        dialog.cancel();
                    }
                });
		AlertDialog dialog = builder.create();
		dialog.show();
    }
}