package com.cerSprikRu.CocktailMixer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cerSprikRu.CocktailMixer.model.drink.CocktailCreator;

public class DisplayCocktails extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.display);
		 CocktailCreator.getInstance().createCocktails();
		 
		 Button back = (Button)findViewById(R.id.back);
		 back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
			}
		});
	}
}
