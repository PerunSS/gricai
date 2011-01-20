package com.cerSprikRu.RNBRumorsNFacts;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.cerSprikRu.RNBRumorsNFacts.db.customManager.DBManager;
import com.cerSprikRu.RNBRumorsNFacts.person.Person;

public class RnBRumorsNFacts extends Activity {
	
	private TextView factsText;
	private Button previousButton;
	private Button nextButton;
	private Button randomButton;
	
	List<Person> persons;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DBManager manager = new DBManager(this);
        persons = manager.read();
        startApplication();
       
    }
    
    private void startApplication(){
    	setContentView(R.layout.rumorsnfacts);
    	
    	factsText = (TextView) findViewById(R.id.factText);
		factsText.setText("start");
		
		
		previousButton = (Button) findViewById(R.id.previous);
		previousButton.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				previousClick(v);
			}
		});
		
		nextButton = (Button) findViewById(R.id.next);
		nextButton.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				nextClick(v);
			}
		});
		
		randomButton = (Button) findViewById(R.id.random);
		randomButton.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				randomClick(v);
			}
		});
		
		
    	
    	
    }
    
    private void nextClick( View v){
    	setContentView(R.layout.rumorsnfacts);
    	
    	factsText = (TextView) findViewById(R.id.factText);
		factsText.setText("next");
		
		
		previousButton = (Button) findViewById(R.id.previous);
		previousButton.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				previousClick(v);
			}
		});
		
		nextButton = (Button) findViewById(R.id.next);
		nextButton.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				nextClick(v);
			}
		});
		
		randomButton = (Button) findViewById(R.id.random);
		randomButton.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				randomClick(v);
			}
		});
    }
    
    private void previousClick( View v){
    	setContentView(R.layout.rumorsnfacts);
    	
    	factsText = (TextView) findViewById(R.id.factText);
		factsText.setText("previous");
		
		
		previousButton = (Button) findViewById(R.id.previous);
		previousButton.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				previousClick(v);
			}
		});
		
		nextButton = (Button) findViewById(R.id.next);
		nextButton.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				nextClick(v);
			}
		});
		
		randomButton = (Button) findViewById(R.id.random);
		randomButton.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				randomClick(v);
			}
		});
    }
 
    private void randomClick( View v){
    	setContentView(R.layout.rumorsnfacts);
    	
    	factsText = (TextView) findViewById(R.id.factText);
		factsText.setText("random");
		
		
		previousButton = (Button) findViewById(R.id.previous);
		previousButton.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				previousClick(v);
			}
		});
		
		nextButton = (Button) findViewById(R.id.next);
		nextButton.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				nextClick(v);
			}
		});
		
		randomButton = (Button) findViewById(R.id.random);
		randomButton.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				randomClick(v);
			}
		});
    }
    
}