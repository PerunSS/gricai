package com.funforall.template;


import com.google.ads.AdRequest;
import com.google.ads.AdView;

import android.app.Activity;
import android.os.Bundle;

public class AppTemplateActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
//        AdView view = (AdView)findViewById(R.id.adView);
//        view.loadAd(new AdRequest());
    }
}