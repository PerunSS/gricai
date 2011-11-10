package com.cerSprikRu.TruthOrDare;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

import com.cerSprikRu.TruthOrDare.adapter.PlayersListAdapter;
import com.cerSprikRu.TruthOrDare.model.Player;

public class PlayerChooser extends Activity {
	
	private ListView playersListView;
	private List<Player> playersList;
	private Context myContext=this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.player_chooser);
		
		Spinner spinner = (Spinner) findViewById(R.id.player_spinner);
	    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
	            this, R.array.players_array, android.R.layout.simple_spinner_item);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner.setAdapter(adapter);
		
	    spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
	    
		playersListView = (ListView)findViewById(R.id.players_list);
		
	}
	
	public class MyOnItemSelectedListener implements OnItemSelectedListener {

	    public void onItemSelected(AdapterView<?> parent,
	        View view, int pos, long id) {
	    	playersList= new ArrayList<Player>();
	        for(int i=0; i<pos+2; i++){	        	
	        	Player player = new Player("player"+(i+1),Player.Gender.MALE,Player.Orientation.STRAIGHT);
	        	playersList.add(player);
	        }
        	playersListView.setAdapter(new PlayersListAdapter(myContext, playersList));
	    }

	    public void onNothingSelected(AdapterView<?> parent) {
	      // Do nothing.
	    }
	}
}
