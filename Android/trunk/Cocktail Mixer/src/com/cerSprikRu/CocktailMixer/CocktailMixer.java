package com.cerSprikRu.CocktailMixer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class CocktailMixer extends Activity {

	private int splashTime = 750;

	// private boolean standard = true;
	// private static final int standard_mix_type = 1;
	// private static final int complete_random_mix_type = 2;
	// private static final int mix_type_group = 0;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash_screen);
		Thread splashTread = new Thread() {
			@Override
			public void run() {
				try {
					int waited = 0;
					while (waited < splashTime) {
						sleep(100);
						waited += 100;
					}
				} catch (InterruptedException e) {
					// do nothing
				} finally {
					finish();
					startActivity(new Intent(CocktailMixer.this,Chooser.class));
					stop();
				}
			}
		};
		splashTread.start();
	}



	
	/*
	 * public boolean onCreateOptionsMenu( android.view.Menu menu) {
	 * super.onCreateOptionsMenu(menu); menu.add("Backgrounds")
	 * .setIcon(android.R.drawable.ic_menu_gallery); // .setIntent(new
	 * Intent(this, FormsActivity.class));
	 * 
	 * SubMenu style_choice = menu.addSubMenu("Mixing type")
	 * .setIcon(android.R.drawable.ic_menu_manage);
	 * style_choice.add(mix_type_group, standard_mix_type, 1, "Light")
	 * .setChecked(standard); style_choice.add(mix_type_group,
	 * complete_random_mix_type, 2, "Dark") .setChecked(!standard);
	 * style_choice.setGroupCheckable(mix_type_group, true, true);
	 * 
	 * 
	 * menu.add("About us") .setIcon(android.R.drawable.ic_menu_info_details);
	 * // .setIntent(new Intent(this, ContainersActivity.class));
	 * 
	 * return true; }
	 * 
	 * public boolean onOptionsItemSelected(MenuItem item) { if
	 * (item.getItemId() == standard_mix_type) { item.setChecked(true); standard
	 * = true; return true; } else if (item.getItemId() ==
	 * complete_random_mix_type) { item.setChecked(true); standard = false;
	 * return true; } return super.onOptionsItemSelected(item); }
	 */
}