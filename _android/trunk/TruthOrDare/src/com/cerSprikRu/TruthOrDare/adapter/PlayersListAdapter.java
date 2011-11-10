package com.cerSprikRu.TruthOrDare.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cerSprikRu.TruthOrDare.R;
import com.cerSprikRu.TruthOrDare.model.Player;

public class PlayersListAdapter extends BaseAdapter {
	
	private LayoutInflater inflater;
	private List<Player> players;
	
	public PlayersListAdapter(Context context, List<Player> players) {
		inflater = LayoutInflater.from(context);
		this.players=players;
	}

	@Override
	public int getCount() {
		return players.size();
	}

	@Override
	public Object getItem(int position) {
		return players.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		mHolder holder = null;
		if(convertView==null){
		convertView = inflater.inflate(R.layout.player, null);
		holder = new mHolder();		
		holder.playerName = (TextView) convertView
				.findViewById(R.id.player_name);
		convertView.setTag(holder);
		}else {
			holder = (mHolder) convertView.getTag();
		}
		holder.playerName.setText(players.get(position).getName());
		return convertView;
	}
	
	private class mHolder {
		TextView playerName;

	}
}
