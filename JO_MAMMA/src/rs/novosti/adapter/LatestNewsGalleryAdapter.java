package rs.novosti.adapter;

import java.util.List;

import rs.novosti.NovostiCela;
import rs.novosti.R;
import rs.novosti.model.Article;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LatestNewsGalleryAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private List<Article> articles;
	private Activity activity;

	public LatestNewsGalleryAdapter(Context context, List<Article> articles,
			Activity a) {
		inflater = LayoutInflater.from(context);
		this.articles = articles;
		this.activity = a;
	}

	@Override
	public int getCount() {
		return articles.size();
	}

	@Override
	public Object getItem(int position) {
		return articles.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.first_article_style, null);
			holder = new Holder();
			holder.articleLayout = (LinearLayout) convertView
					.findViewById(R.id.firstStyleArticle);
			holder.latestArticleTitle = (TextView) convertView
					.findViewById(R.id.firstStyleArticle_title);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}

		// Article article = articles.get(position);
		// System.out.println(article.getName());
		// new BitmapFactory();
		// holder.latestArticleImage.setImageBitmap(BitmapFactory.decodeFile(article.getPhotoPath()));
		holder.latestArticleTitle.setText("naslov" + position);
		holder.latestArticleTitle
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent myIntent = new Intent(v.getContext(),
								NovostiCela.class);
						activity.startActivityForResult(myIntent, 0);
					}
				});
		holder.articleLayout.setBackgroundResource(R.drawable.b1);
		// holder.latestArticleTitle.setText(article.getName());
		return convertView;
	}

	private class Holder {
		LinearLayout articleLayout;
		TextView latestArticleTitle;

		@Override
		public String toString() {
			return latestArticleTitle.getText().toString().trim() + "\n";
		}
	}
}
