package com.cerSprikRu.CocktailMixer.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cerSprikRu.CocktailMixer.R;
import com.cerSprikRu.CocktailMixer.model.drink.Cocktail;

public class FavoriteCocktailListAdapter extends BaseAdapter{
	
	private List<Cocktail> CocktailList = new ArrayList<Cocktail>(); 
	private LayoutInflater mInflater;
	
	public FavoriteCocktailListAdapter( Context context, List<Cocktail> Cocktails){
		mInflater = LayoutInflater.from(context);
		CocktailList = Cocktails;
	}
	
	@Override
	public int getCount() {
		return CocktailList.size();
	}

	@Override
	public Object getItem(int position) {
		return CocktailList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder = null;
		if(convertView == null){
			convertView = mInflater.inflate(R.layout.list_element,null);
			holder = new Holder();
			holder.cocktailText = (TextView) convertView.findViewById(R.id.cocktail_list_element_text);
//			holder.addToFavorites = (Button) convertView.findViewById(R.id.cocktail_list_element_favorites);
//			holder.share = (Button) convertView.findViewById(R.id.cocktail_list_element_share);
			
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		if (CocktailList.get(position).getName() != ""){
			holder.cocktailText.setText(CocktailList.get(position).getName() +":\n"+CocktailList.get(position).toString());
		} else {
			holder.cocktailText.setText(CocktailList.get(position).toString());
		}
		
		return convertView;
	}
	private class Holder {
		TextView cocktailText;
//		Button addToFavorites;
//		Button share;
	}
}
