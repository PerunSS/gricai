package com.cerSprikRu.RNBRumorsNFacts;

import android.app.Activity;
import android.os.Bundle;

public class RnBRumorsNFacts extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startApplication();
       
    }
    
    private void startApplication(){
    	setContentView(R.layout.rumorsnfacts);
    }
}