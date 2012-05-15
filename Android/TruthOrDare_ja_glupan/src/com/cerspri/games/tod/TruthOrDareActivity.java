package com.cerspri.games.tod;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TruthOrDareActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        
        Button coupleButton = (Button) findViewById(R.id.Couples);
        Button teamsButton = (Button) findViewById(R.id.Teams);
        Button settingsButton = (Button) findViewById(R.id.Settings);
        
        teamsButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(v.getContext(), PlayerSelectionScreen.class);
				startActivityForResult(myIntent, 0);
			}
		});
    }
    
}