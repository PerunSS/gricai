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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cerSprikRu.CocktailMixer.favorites.FavoritesManager;
import com.cerSprikRu.CocktailMixer.model.drink.Cocktail;
import com.cerSprikRu.CocktailMixer.model.drink.CocktailCreator;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;
import com.google.ads.AdRequest;
import com.google.ads.AdView;

public class DisplayCocktail extends Activity implements DialogListener{
	
	private Context mContext = this;
	private Cocktail cocktail;
	private TextView recipe;
	private TextView detailed;
	private Facebook facebookClient;
    private ImageView facebookButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.cocktail_display);
		cocktail = CocktailCreator.getInstance().getCocktails().get(getIntent().getExtras().getInt("position"));
		recipe = (TextView)findViewById(R.id.cocktail_recipe);
		recipe.setText(cocktail.toString());
		detailed = (TextView)findViewById(R.id.cocktail_recipe_detailed);
		detailed.setText(cocktail.getDescription());
		
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
				final View textEntryView = factory.inflate(
						R.layout.name_entry_dialog, null);
				AlertDialog dialog = new AlertDialog.Builder(
						DisplayCocktail.this)
						.setTitle("Enter Cocktail name")
						.setView(textEntryView)
						.setPositiveButton("Ok!",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int whichButton) {
										String name = ((EditText) textEntryView
												.findViewById(R.id.cocktail_name_entry))
												.getText().toString().trim();
										if (name != null && name.length() > 0) {
											cocktail.setName(name);
											FavoritesManager.getInstance()
													.togleFavorite(cocktail);
											favButton.setEnabled(false);
											favButton.setBackgroundResource(R.drawable.save_2);
											Toast.makeText(
													getApplicationContext(),
													"Cocktail "+name+" saved.",
													Toast.LENGTH_SHORT).show();
										} else {
											Toast.makeText(
													getApplicationContext(),
													"You must name a Cocktail!",
													Toast.LENGTH_SHORT).show();
										}
									}
								})
						.setNegativeButton("Cancel",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int whichButton) {

									}
								}).create();

				dialog.show();
			}
		});
		if(FavoritesManager.getInstance().getFavorites().contains(cocktail)){
			favButton.setEnabled(false);
			favButton.setBackgroundResource(R.drawable.save_2);
		}
		
		facebookButton = (ImageView)this.findViewById(R.id.share_cocktail_fb);
		facebookButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				facebookClient = new Facebook("117614281661009");
	            // replace APP_API_ID with your own
	            facebookClient.authorize(DisplayCocktail.this, new String[] {"publish_stream"}, DisplayCocktail.this);
			}
		});
		AdView adView4 = (AdView) findViewById(R.id.adView4);
		adView4.loadAd(new AdRequest());
	}

	@Override
	public void onComplete(Bundle values) {
		System.out.println("on complete");
		if (values.isEmpty())
        {
            //"skip" clicked ?
			return;
        }

        // if facebookClient.authorize(...) was successful, this runs
        // this also runs after successful post
        // after posting, "post_id" is added to the values bundle
        // I use that to differentiate between a call from
        // faceBook.authorize(...) and a call from a successful post
        // is there a better way of doing this?
        if (!values.containsKey("post_id")) {
            try {
            	System.out.println("uso u post id");
                Bundle parameters = new Bundle();
                parameters.putString("message", "Try crazy cocktail:\n" +cocktail.toString()+"\n"+cocktail.getDescription());// the message to post to the wall
                facebookClient.dialog(this, "feed", parameters, this);// "stream.publish" is an API call
                

            }catch (Exception e) {
            }
        }
	}

	@Override
	public void onFacebookError(FacebookError e) {
	}

	@Override
	public void onError(DialogError e) {
	}

	@Override
	public void onCancel() {
	}
	
	@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        facebookClient.authorizeCallback(requestCode, resultCode, data);
    }
}
