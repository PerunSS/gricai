package com.android.projectsforlearning.factstemplate;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;

public class FactsTemplate extends Activity {
    /** Called when the activity is first created. */
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startMainView();
    }
    
    private void startMainView(){
    	setContentView(R.layout.main);
        
        GridView gridview = (GridView) findViewById(R.id.startPageGridView);
        gridview.setAdapter(new ImageAdapter(this));
        gridview.setBackgroundColor(android.graphics.Color.WHITE);

        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            	itemSelected( parent, v, position, id);
            }

			
        });
    }
    
    
    private void itemSelected(AdapterView<?> parent, View v, int position, long id) {
				setContentView(R.layout.selected);
				final Button homeButton = (Button) findViewById(R.id.homeButton);
		        homeButton.setOnClickListener( new Button.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						homeClick(v);
					}
				});
			}
    public void homeClick( View view){
    	setContentView(R.layout.main);
    }
    
}