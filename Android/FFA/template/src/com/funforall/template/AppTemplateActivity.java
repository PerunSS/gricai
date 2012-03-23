package com.funforall.template;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AppTemplateActivity extends Activity {
	
	private DBManager manager;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        manager = new DBManager(this);
        boolean rated = false;
        SharedPreferences preferences = getPreferences(MODE_WORLD_READABLE);
        rated = preferences.getBoolean("isRated", false);
        Model.getInstance().setData(manager.read(rated));
        Model.getInstance().setManager(manager);
        
        boolean messageShown = false;
        messageShown = preferences.getBoolean("messageShown", false);
        
        if(Constants.REWRITE_DB){
        	rated = false;
        	messageShown = false;
        }
        if(!messageShown){
        	if(!rated){
        		Toast.makeText(this, "Consider to rate application in order to unlock additional content!", Toast.LENGTH_LONG).show();
        	}else{
        		Toast.makeText(this, "Thank you for rating our application, aditional content has been unlocked", Toast.LENGTH_LONG).show();
        		SharedPreferences.Editor editor = getPreferences(MODE_WORLD_WRITEABLE).edit();
        		editor.putBoolean("messageShown", true);
        		editor.commit();
        	}
        }
        
        final Button read = (Button)findViewById(R.id.start);
        read.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent readItent = new Intent(getApplicationContext(), ReadingActivity.class);
				startActivity(readItent);
			}
		});
        
        final Button listFavorites = (Button)findViewById(R.id.listFavorites);
        listFavorites.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent listIntent = new Intent(getApplicationContext(), ListActivity.class);
				startActivity(listIntent);
				
			}
		});
        
        final Button visitUs = (Button)findViewById(R.id.market);
        visitUs.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://"+Constants.DEVELOPER_QUERY));
                startActivity(intent);
			}
		});
        
        final Button rateUs = (Button)findViewById(R.id.rateUs);
        rateUs.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SharedPreferences.Editor editor = getPreferences(MODE_WORLD_WRITEABLE).edit();
        		editor.putBoolean("isRated", true);
        		editor.commit();
				Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://"+Constants.APPLICATION_QUERY));
                startActivity(intent);
			}
		});
    }
    
}