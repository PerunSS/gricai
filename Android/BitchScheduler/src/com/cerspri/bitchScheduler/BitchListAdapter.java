package com.cerspri.bitchScheduler;

import java.util.List;

import com.cerspri.bitchScheduler.model.Bitch;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BitchListAdapter extends BaseAdapter {
	
	private List<Bitch> bitches;
	private Context context;
	
	public BitchListAdapter(Context context, List<Bitch> bitches){
		this.bitches = bitches;
		this.context = context;
	}

	@Override
	public int getCount() {
		return bitches.size();
	}

	@Override
	public Object getItem(int position) {
		return bitches.get(position);
	}

	@Override
	public long getItemId(int position) {
		return bitches.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView view = (TextView)convertView;
		if(view == null){
			view = new TextView(context);
		}
		Bitch bitch = bitches.get(position);
		view.setText(bitch.getName());
		switch (bitch.getBitchState()) {
		case C_PERIOD:
			view.setTextColor(Color.RED);
			break;
		case B_AROUND_PERIOD:
			view.setTextColor(Color.YELLOW);
			break;
		case A_NO_PERIOD:
			view.setTextColor(Color.GREEN);
		default:
			break;
		}
		return view;
	}

}
