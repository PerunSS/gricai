package com.cerSprikRu.RNBRumorsNFacts;

import java.util.List;

import com.cerSprikRu.RNBRumorsNFacts.db.customManager.DBManager;
import com.cerSprikRu.RNBRumorsNFacts.person.Person;

import android.app.Activity;
import android.os.Bundle;

public class RnBRumorsNFacts extends Activity {
	
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
    }
}