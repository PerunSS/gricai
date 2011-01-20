package com.cerSprikRu.rnbrumorsnfacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomElementAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private static final int[] imageIDs = { R.drawable.beyonce,
			R.drawable.aliciakeys, R.drawable.duffy, R.drawable.keyshiacole,
			R.drawable.neyo, R.drawable.chrisbrown };

	private static final int[] nameIDs = { R.string.person0name,
			R.string.person1name, R.string.person2name, R.string.person3name,
			R.string.person4name, R.string.person5name };

	public CustomElementAdapter(Context ctx) {
		inflater = LayoutInflater.from(ctx);
	}

	@Override
	public int getCount() {
		return imageIDs.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.custom_element_view, null);
			holder = new ViewHolder();
			holder.image = (ImageView) convertView.findViewById(R.id.image_view);
//			holder.image.setLayoutParams(new GridView.LayoutParams(120, 100));
			holder.text = (TextView) convertView.findViewById(R.id.text_view);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.image.setImageResource(imageIDs[position]);
		holder.text.setText(nameIDs[position]);
		return convertView;

	}

	class ViewHolder {
		ImageView image;
		TextView text;
	}

}
