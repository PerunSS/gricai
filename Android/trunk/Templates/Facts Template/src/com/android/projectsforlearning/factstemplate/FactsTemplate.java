package com.android.projectsforlearning.factstemplate;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.android.projectsforlearning.factstemplate.person.Person;

public class FactsTemplate extends Activity {
    /** Called when the activity is first created. */
	
	private GridView gridView;
	private TextView factsNRumorsText;
	private Button homeButton;
	private Button nextButton;
	private Button previousButton;
	private Button randomButton;
	private Person person = Person.readPerson(0);
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    
        startMainView();
        
    }
    
    private void startMainView(){
    	setContentView(R.layout.main);  
    	
    	gridView = (GridView) findViewById(R.id.startPageGridView);
        gridView.setAdapter(new ImageAdapter(this));
        gridView.setBackgroundColor(android.graphics.Color.WHITE);

        gridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            	itemSelected( parent, v, position, id);
            }

			
        });
    }
    
    
    private void itemSelected(AdapterView<?> parent, View v, int position, long id) {
				setContentView(R.layout.selected);
				
				factsNRumorsText = (TextView) findViewById(R.id.rumorText);
				factsNRumorsText.setText(this.person.getNextRumor());
				
				
				previousButton = (Button) findViewById(R.id.previousButton);
				previousButton.setOnClickListener( new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						previousClick(v);
					}
				});
				
				
				homeButton = (Button) findViewById(R.id.homeButton);
		        homeButton.setOnClickListener( new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						homeClick(v);
					}
				});
			}
    
    public void previousClick( View v){
    	setContentView(R.layout.selected);
    	
    	factsNRumorsText = (TextView) findViewById(R.id.rumorText);
		factsNRumorsText.setText(this.person.getNextRumor());
		
		previousButton = (Button) findViewById(R.id.previousButton);
		previousButton.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				previousClick(v);
			}
		});
		
		
		homeButton = (Button) findViewById(R.id.homeButton);
        homeButton.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				homeClick(v);
			}
		});
		
    }
    
    public void homeClick( View view){
    	startMainView();
    }
    
}