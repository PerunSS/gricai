package com.cerSprikRu.AmazingFacts.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cerSprikRu.AmazingFacts.fact.Fact;

public class FavoriteAdapter extends ArrayAdapter<Fact> {

	private List<Fact> favorites;
	
	public FavoriteAdapter(Context context, int textViewResourceId,
			List<Fact> objects) {
		super(context, textViewResourceId, objects);
		favorites = objects;
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView v = (TextView)convertView;
		if(v == null){
			v = new TextView(getContext());
		}
		v.setText(favorites.get(position).getText());
		return v;
	}
	
}
