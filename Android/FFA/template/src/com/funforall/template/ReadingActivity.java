package com.funforall.template;

import android.app.Activity;
import android.os.Bundle;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

public class ReadingActivity extends Activity {

	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.text_viewer);
	        AdView view = (AdView)findViewById(R.id.adView);
	        view.loadAd(new AdRequest());
	    }
}
