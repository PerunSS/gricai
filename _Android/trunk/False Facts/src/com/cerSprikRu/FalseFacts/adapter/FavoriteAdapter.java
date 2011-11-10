package com.cerSprikRu.FalseFacts.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cerSprikRu.FalseFacts.R;
import com.cerSprikRu.FalseFacts.fact.Fact;

public class FavoriteAdapter extends ArrayAdapter<Fact> {

	private LayoutInflater inflater;
	private List<Fact> favorites;
	
	public FavoriteAdapter(Context context, int textViewResourceId,
			List<Fact> objects) {
		super(context, textViewResourceId, objects);
		inflater = LayoutInflater.from(context);
		favorites = objects;
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder = null;
		if(convertView == null){
			convertView = inflater.inflate(R.layout.list_element,null);
			holder = new Holder();
			holder.fact = (TextView) convertView.findViewById(R.id.favorite_fact);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		holder.fact.setText(favorites.get(position).getText());
		return convertView;
		
		
		/*TextView v = (TextView)convertView;
		if(v == null){
			v = new TextView(getContext());
		}
		v.setText(favorites.get(position).getText());
		v.setTextSize(15);
		v.setTextColor(Color.BLACK);
		return v;*/
	}
	private class Holder {
		TextView fact;
	}
}
