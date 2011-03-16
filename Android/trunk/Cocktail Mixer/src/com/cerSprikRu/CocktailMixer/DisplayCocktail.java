package com.cerSprikRu.CocktailMixer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cerSprikRu.CocktailMixer.favorites.FavoritesManager;
import com.cerSprikRu.CocktailMixer.model.drink.Cocktail;
import com.cerSprikRu.CocktailMixer.model.drink.CocktailCreator;

public class DisplayCocktail extends Activity{
	
	private Context mContext = this;
	private Cocktail cocktail;
	private TextView recipe;
//	private static final int DIALOG_TEXT_ENTRY = 7;

	
	@Override
//    protected Dialog onCreateDialog(int id) {
//        switch (id) {
//        case DIALOG_TEXT_ENTRY:
//            // This example shows how to add a custom layout to an AlertDialog
//            LayoutInflater factory = LayoutInflater.from(this);
//            final View textEntryView = factory.inflate(R.layout.name_entry_dialog, null);
//            return new AlertDialog.Builder(DisplayCocktail.this)
//                .setTitle("Enter Cocktail name")
//                .setView(textEntryView)
//                .setPositiveButton("Ok!", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int whichButton) {
//                    	cocktail.setName(((EditText) textEntryView.findViewById(R.id.cocktail_name_entry)).getText().toString().trim());
//                    }
//                })
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int whichButton) {
//                    	cocktail.setName("");
//                    }
//                })
//                .create();
//        }
//        return null;
//    }

	
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
				LayoutInflater factory = LayoutInflater.from(mContext);
	            final View textEntryView = factory.inflate(R.layout.name_entry_dialog, null);
	            AlertDialog dialog = new AlertDialog.Builder(DisplayCocktail.this)
	                .setTitle("Enter Cocktail name")
	                .setView(textEntryView)
	                .setPositiveButton("Ok!", new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog, int whichButton) {
	                    	cocktail.setName(((EditText) textEntryView.findViewById(R.id.cocktail_name_entry)).getText().toString().trim());
	                    	FavoritesManager.getInstance().togleFavorite(cocktail);
	    					favButton.setEnabled(false);
	                    }
	                })
	                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog, int whichButton) {
	                    	Toast.makeText(getApplicationContext(), "You must name a Cocktail",
	    							Toast.LENGTH_SHORT).show();
	                    }
	                }).create();
				
				dialog.show();
//				showDialog(DIALOG_TEXT_ENTRY);
//				if (cocktail.getName() == ""){
//					Toast.makeText(getApplicationContext(), "You must name a Cocktail",
//							Toast.LENGTH_SHORT).show();
//				} else {	
//					FavoritesManager.getInstance().togleFavorite(cocktail);
//					favButton.setEnabled(false);
//				}
			}
		});
		if(FavoritesManager.getInstance().getFavorites().contains(cocktail))
			favButton.setEnabled(false);
	}
}
