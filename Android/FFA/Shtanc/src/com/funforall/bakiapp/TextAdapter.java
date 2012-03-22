package com.funforall.bakiapp;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TextAdapter extends ArrayAdapter<Text> {

	private List<Text> favorites;

	public TextAdapter(Context context, int textViewResourceId,
			List<Text> objects) {
		super(context, textViewResourceId, objects);
		favorites = objects;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView v = (TextView) convertView;
		if (v == null) {
			v = new TextView(getContext());
		}
		v.setText(favorites.get(position).text);
		v.setTextSize(15);
		return v;
	}

}
