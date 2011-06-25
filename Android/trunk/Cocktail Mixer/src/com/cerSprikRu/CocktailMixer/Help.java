package com.cerSprikRu.CocktailMixer;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class Help extends Activity{
	
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.help);
	}
}
