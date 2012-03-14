package com.funforall.template;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

import android.app.Activity;
import android.os.Bundle;

public class ListActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		AdView view = (AdView) findViewById(R.id.adViewList);
		view.loadAd(new AdRequest());
	}
}
