package rs.novosti.adapter;

import java.util.List;

import rs.novosti.NovostiCela;
import rs.novosti.R;
import rs.novosti.model.Article;
import rs.novosti.model.Category;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CategoryPreviewAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private List<Category> categories;
	private Context context;

	public CategoryPreviewAdapter(Context context, List<Category> categories) {
		this.context = context;
		inflater = LayoutInflater.from(context);
		this.categories = categories;
	}

	@Override
	public int getCount() {
		return categories.size();
	}

	@Override
	public Object getItem(int position) {
		return categories.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.category_view, null);
			holder = new Holder();
			holder.categoryName = (TextView) convertView
					.findViewById(R.id.category_name);
			holder.firstArticle = (RelativeLayout)convertView.findViewById(R.id.firstArticle);
			holder.firstArticleDescription = (TextView) convertView
					.findViewById(R.id.firstArticle_shortText);
			holder.firstArticleImage = (ImageView) convertView
					.findViewById(R.id.firstArticle_image);
			holder.firstArticleTitle = (TextView) convertView
					.findViewById(R.id.firstArticle_name);
			holder.secondArticle = (TextView) convertView
					.findViewById(R.id.secondArticle);
			holder.thirdArticle = (TextView) convertView
					.findViewById(R.id.thirdArticle);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		Category cat = categories.get(position);
		holder.categoryName.setText(cat.getName());
		final Article firstArticle = cat.getArticles().get(0);
		if (firstArticle != null) {
			holder.firstArticle.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent myIntent = new Intent(v.getContext(),
							NovostiCela.class);
					myIntent.putExtra("article", firstArticle);
					((Activity)context).startActivityForResult(myIntent, 0);
				}
			});
			holder.firstArticleTitle.setText(firstArticle.getName());
			holder.firstArticleDescription.setText(firstArticle.getShortText());
			holder.firstArticleImage.setBackgroundResource(R.drawable.icon);
		}
		final Article secondArticle = cat.getArticles().get(1);
		if (secondArticle != null) {
			holder.secondArticle.setText(secondArticle.getOneLine());
			holder.secondArticle.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent myIntent = new Intent(v.getContext(),
							NovostiCela.class);
					myIntent.putExtra("article", firstArticle);
					((Activity)context).startActivityForResult(myIntent, 0);
				}
			});
		}
		final Article thirdArticle = cat.getArticles().get(2);
		if (thirdArticle != null) {
			holder.thirdArticle.setText(thirdArticle.getOneLine());
			holder.thirdArticle.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent myIntent = new Intent(v.getContext(),
							NovostiCela.class);
					myIntent.putExtra("article", firstArticle);
					((Activity)context).startActivityForResult(myIntent, 0);
				}
			});
		}
		return convertView;
	}

	private class Holder {
		TextView categoryName;
		ImageView firstArticleImage;
		TextView firstArticleTitle;
		TextView firstArticleDescription;
		TextView secondArticle;
		TextView thirdArticle;
		RelativeLayout firstArticle;

//		@Override
//		public String toString() {
//			return categoryName.getText() + "\n" + firstArticleTitle.getText()
//					+ "\n" + firstArticleDescription.getText() + "\n"
//					+ secondArticle.getText() + "\n" + thirdArticle.getText()
//					+ "\n";
//		}
	}
}
