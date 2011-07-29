package rs.novosti.adapter;

import java.util.List;

import rs.novosti.R;
import rs.novosti.model.Article;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;


/**
 * Adapter class for loading data into my gallery view
 * @author aleksandarvaricak
 *
 */
public class MyGalleryAdapter extends BaseAdapter {
	private Context mContext;
	List<Article> articles;

	public MyGalleryAdapter(Context c, List<Article> articles) {
		mContext = c;
		this.articles = articles;
	}

	public int getCount() {
		return articles.size();
	}

	public void add(Article a){
		articles.add(a);
	}
	
	public Object getItem(int position) {
		return articles.get(position);
	}

	public long getItemId(int position) {
		return position;
	}
	/**
	 * Method for creating a new ImageView for each item referenced by the Adapter
	 */
	
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		if (convertView == null) {
			imageView = new ImageView(mContext);
			imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(8, 8, 8, 8);
		} else {
			imageView = (ImageView) convertView;
		}
		if(articles.get(position).getView() == null)
			imageView.setImageResource(R.drawable.spinner);
		articles.get(position).setView(imageView);
		articles.get(position).generateSmallPhoto();
		return imageView;
	}
	
	public void refresh(List<Article> articles){
		this.articles = articles;
		notifyDataSetChanged();
	}

	public void clear(){
		for(Article article:articles)
			article.clear();
	}
}
