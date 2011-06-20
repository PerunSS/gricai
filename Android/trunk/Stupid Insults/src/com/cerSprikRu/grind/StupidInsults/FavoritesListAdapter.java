package com.cerSprikRu.grind.StupidInsults;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cerSprikRu.grind.StupidInsults.insult.Fact;

public class FavoritesListAdapter extends BaseAdapter{
	
	private LayoutInflater inflater;
	private List<Fact> elements;
	
	public FavoritesListAdapter(Context ctx, List<Fact> elements){
		inflater = LayoutInflater.from(ctx);
		this.elements = elements;
	}
	@Override
	public int getCount() {
		return elements.size();
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder = null;
		if(convertView == null){
			convertView = inflater.inflate(R.layout.favorite_list_element,null);
			holder = new Holder();
			holder.name = (TextView) convertView.findViewById(R.id.favorite_name);
			holder.fact = (TextView) convertView.findViewById(R.id.favorite_fact);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		holder.name.setText(elements.get(position).getPerson());
		holder.fact.setText(elements.get(position).getText());
		return convertView;
	}
	
	private class Holder {
		TextView name;
		TextView fact;
	}
}
