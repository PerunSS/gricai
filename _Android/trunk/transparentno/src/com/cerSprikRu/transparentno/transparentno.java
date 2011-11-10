package com.cerSprikRu.transparentno;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class transparentno extends Activity {
    /** Called when the activity is first created. */
	
	private Button redButton;
	private Button blueButton;
	private LinearLayout mainLayout;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mainLayout = (LinearLayout) findViewById(R.id.main); 
        redButton = (Button) findViewById(R.id.red);
		redButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mainLayout.setBackgroundColor(Color.RED);
				
			}
		});
        blueButton = (Button) findViewById(R.id.blue);
		blueButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mainLayout.setBackgroundColor(Color.BLUE);
			}
		});
    }
}