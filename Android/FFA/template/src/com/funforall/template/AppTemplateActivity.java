package com.funforall.template;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AppTemplateActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final Button read = (Button)findViewById(R.id.start);
        read.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent readItent = new Intent(AppTemplateActivity.this, ReadingActivity.class);
				startActivity(readItent);
			}
		});
    }
}