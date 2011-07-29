package rs.novosti.adapter;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import rs.novosti.R;
import rs.novosti.model.Article;
import rs.novosti.model.Main;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 * Adapter class for loading data into latest news gallery view
 * @author aleksandarvaricak
 *
 */
public class LatestNewsGalleryAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private List<Article> articles;
	private Context context;


	public LatestNewsGalleryAdapter(Context context, List<Article> articles) {
		inflater = LayoutInflater.from(context);
		this.articles = articles;
		this.context = context;
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
	/**
	 * Method for creating a new ImageView for each item referenced by the Adapter
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Article article = (Article)getItem(position);
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

		holder.latestArticleTitle.setText(Html.fromHtml(article.getTitle()));

		holder.articleLayout.setBackgroundResource(R.drawable.spinner);
		new LoadImageTask(holder.articleLayout, article).execute();
		return convertView;
	}
	private class Holder {
		LinearLayout articleLayout;
		TextView latestArticleTitle;
		
	}
	
	public void clearAll(){
		for(Article article:articles){
			article.clear();
		}
	}
	/**
	 * AsyncTask for downloading image
	 * @author aleksandarvaricak
	 *
	 */
	private class LoadImageTask extends AsyncTask<Void, Void, Drawable>{
		private LinearLayout layout;
		private Article article;
		
		LoadImageTask(LinearLayout layout, Article article){
			this.layout = layout;
			this.article = article;
		}
		
		@Override
		protected Drawable doInBackground(Void ... params) {
			InputStream is = null;
			if(article.getBigDrawable()!=null)
				return article.getBigDrawable();
			if(article.getPhotoPath() == null)
				article = Main.getInstance().readArticle(article);
			String url = article.getPhotoPath();
			if (url != null) {
				url = url.replaceAll(" ", "%20");
				try {
					is = new URL(url).openStream();
					BitmapFactory.Options options = new BitmapFactory.Options();
					Bitmap bitmap = BitmapFactory.decodeStream(is,null, options);
	
					Drawable drawable = new BitmapDrawable(bitmap);

					article.setBigDrawable(drawable);
					return drawable;
				} catch (Exception e) {
				} finally {
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else {
				return new BitmapDrawable(BitmapFactory.decodeResource(
						context.getResources(), R.drawable.no_image));
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Drawable result) {
			layout.setBackgroundDrawable(result);
			layout.setMinimumHeight(190);
		}
		
	}
}
