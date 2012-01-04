package com.cerspri.star.NickiMinaj;

import com.cerspri.star.NickiMinaj.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

public class Disclaimer extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.disclaimer);
		SharedPreferences sPrefs = this.getSharedPreferences("nikiStarPrefs", MODE_WORLD_READABLE);
		final SharedPreferences.Editor editor = sPrefs.edit();
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("You need to load data from server for the first run ever (after that you can do it manually)")
		       .setCancelable(false)
		       .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	    editor.putInt("isFirstTime", 0);
		        	    editor.commit();
		        	    setResult(4372);
		        	    finish();
		           }
		       })
		       .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	   setResult(4373);
		        	   finish();
		           }
		       });
		AlertDialog alert = builder.create();
		alert.show();
	}	
}