package com.android.projectsforlearning.factstemplate;

import java.util.HashMap;
import java.util.Map;

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
	private Person person;
	
	private static Map<Integer, Integer> personIds = new HashMap<Integer, Integer>();
	static {
		personIds.put(0, R.string.person0);
		personIds.put(1, R.string.person1);
		personIds.put(2, R.string.person2);
		personIds.put(3, R.string.person3);
		personIds.put(4, R.string.person4);
		personIds.put(5, R.string.person5);
		
	}
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
				
				person = Person.readPerson(position, getString(personIds.get(position)));
				
				factsNRumorsText = (TextView) findViewById(R.id.rumorText);
				factsNRumorsText.setText(person.getNext());
				
				
				previousButton = (Button) findViewById(R.id.previousButton);
				previousButton.setOnClickListener( new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						previousClick(v);
					}
				});
				
				nextButton = (Button) findViewById(R.id.nextButton);
				nextButton.setOnClickListener( new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						nextClick(v);
					}
				});
				
				randomButton = (Button) findViewById(R.id.randomButton);
				randomButton.setOnClickListener( new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						randomClick(v);
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
    
    public void nextClick( View v){
    	factsNRumorsText = (TextView) findViewById(R.id.rumorText);
		factsNRumorsText.setText(person.getNext());
		
		
		previousButton = (Button) findViewById(R.id.previousButton);
		previousButton.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				previousClick(v);
			}
		});
		
		nextButton = (Button) findViewById(R.id.nextButton);
		nextButton.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				nextClick(v);
			}
		});
		
		randomButton = (Button) findViewById(R.id.randomButton);
		randomButton.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				randomClick(v);
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
    
    public void randomClick( View v){
    	factsNRumorsText = (TextView) findViewById(R.id.rumorText);
		factsNRumorsText.setText(person.getRandom());
		
		
		previousButton = (Button) findViewById(R.id.previousButton);
		previousButton.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				previousClick(v);
			}
		});
		
		nextButton = (Button) findViewById(R.id.nextButton);
		nextButton.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				nextClick(v);
			}
		});
		
		randomButton = (Button) findViewById(R.id.randomButton);
		randomButton.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				randomClick(v);
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
    	factsNRumorsText = (TextView) findViewById(R.id.rumorText);
		
		
		previousButton = (Button) findViewById(R.id.previousButton);
		previousButton.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				previousClick(v);
			}
		});
		
		nextButton = (Button) findViewById(R.id.nextButton);
		nextButton.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				nextClick(v);
			}
		});
		
		randomButton = (Button) findViewById(R.id.randomButton);
		randomButton.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				randomClick(v);
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