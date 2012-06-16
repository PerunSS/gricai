package com.cerspri.games.tod.adapter;

import java.util.List;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cerspri.games.tod.model.Player;

public class PlayerListAdapter extends BaseAdapter {
	private Context mContext;
	List<Player> players;

	public PlayerListAdapter(Context c, List<Player> players) {
		mContext = c;
		this.players = players;
	}

	public int getCount() {
		return players.size();
	}

	public void add(Player a){
		players.add(a);
	}
	
	public Object getItem(int position) {
		return players.get(position);
	}

	public long getItemId(int position) {
		return position;
	}
	/**
	 * Method for creating a new ImageView for each item referenced by the Adapter
	 */
	
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView textView;
		if (convertView == null) {
			textView = new TextView(mContext);
			
		} else {
			textView = (TextView) convertView;
		}
		textView.setText(players.get(position).getName());
		textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
		return textView;
	}
	
	public void refresh(List<Player> players){
		this.players = players;
		notifyDataSetChanged();
	}

}
