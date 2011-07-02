package com.cerSprikRu.TruthOrDare;

import com.cerSprikRu.TruthOrDare.adapter.PlayersListAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class PlayerChooser extends Activity {
	private PlayersListAdapter adapter;
	private ListView playersList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.player_chooser);
		final Button generatePlayerList = (Button)findViewById(R.id.confirm_players);
		generatePlayerList.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				adapter = new PlayersListAdapter();
				playersList.setAdapter(adapter);
				adapter.notifyDataSetChanged();
			}
		});
		
		playersList = (ListView)findViewById(R.id.players_list);
		
	}
}
