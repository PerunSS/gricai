package com.cerspri.games.tapit.adapter;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cerspri.games.tapit.R;
import com.cerspri.games.tapit.model.HighScore;

public class HighScoreAdapter extends ArrayAdapter<HighScore> {
	
	private List<HighScore> data;
	private LayoutInflater inflater;
	private DecimalFormat format = new DecimalFormat("#,##0.0#");
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public HighScoreAdapter(Context context, int textViewResourceId,
			List<HighScore> objects) {
		super(context, textViewResourceId, objects);
		inflater = LayoutInflater.from(context);
		data = objects;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		HighScore score = data.get(position);
		if(convertView == null){
			convertView = inflater.inflate(R.layout.high_score, null); 
		}
		if(score!=null){
			((TextView) convertView.findViewById(R.id.name)).setText(score.getName());
			((TextView) convertView.findViewById(R.id.value)).setText(format.format(score.getValue()));
			((TextView) convertView.findViewById(R.id.date)).setText(dateFormat.format(score.getDate()));
		}
		return convertView;
	}

}
