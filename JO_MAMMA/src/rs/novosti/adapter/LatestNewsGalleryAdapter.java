package rs.novosti.adapter;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import rs.novosti.NovostiCela;
import rs.novosti.R;
import rs.novosti.model.Article;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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
		Article article = articles.get(position);
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
		holder.latestArticleTitle.setText(article.getName());
		holder.latestArticleTitle
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent myIntent = new Intent(v.getContext(),
								NovostiCela.class);
						activity.startActivityForResult(myIntent, 0);
					}
				});
		// holder.latestArticleTitle.setText(article.getName());
		Drawable firstArticleImage = getResizedDrawable(article
				.getPhotoPath());
		holder.articleLayout.setBackgroundDrawable(firstArticleImage);
		return convertView;
	}
	
	private Drawable getResizedDrawable(String url) {
		InputStream is = null;
		url = url.replaceAll(" ", "%20");
		try {
			is = new URL(url).openStream();
			Bitmap bitmap = BitmapFactory.decodeStream(is);
			int width = bitmap.getWidth();
			int height = bitmap.getHeight();
			int screenWidth = activity.getWindowManager().getDefaultDisplay()
					.getWidth();
			int screenHeight = activity.getWindowManager().getDefaultDisplay()
					.getHeight();
			double ratio = ((double) screenWidth) / width;
			if (ratio > ((double) screenHeight - 100) / height) {
				ratio = ((double) screenHeight - 100) / height;
			}
			bitmap = Bitmap.createScaledBitmap(bitmap, (int) (width * ratio), (int) (height * ratio), true);
			return new BitmapDrawable(bitmap);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
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
