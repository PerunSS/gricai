package rs.novosti.adapter;

import java.util.List;

import rs.novosti.model.Article;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;



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

	// create a new ImageView for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		if (convertView == null) { // if it's not recycled, initialize some
									// attributes
			imageView = new ImageView(mContext);
			imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(8, 8, 8, 8);
		} else {
			imageView = (ImageView) convertView;
		}
//		Article article = category.getArticles().get(position);
//		System.out.println("artikl     " + article.getPhotoPath() + "\n"
//				);
		articles.get(position).setView(imageView);
		articles.get(position).generateSmallPhoto();
		return imageView;
	}
	
	public void refresh(List<Article> articles){
		this.articles = articles;
		notifyDataSetChanged();
	}

	// references to our images

//	private Drawable getResizedDrawable(String url) {
//		System.out.println("aj kao krece sliku da vadi jebo ga ja");
//		InputStream is = null;
//		if (url != null) {
//			url = url.replaceAll(" ", "%20");
//			try {
//				is = new URL(url).openStream();
//				Bitmap bitmap = BitmapFactory.decodeStream(is);
//				bitmap = Bitmap.createScaledBitmap(bitmap, (int) (80),
//						(int) (80), true);
//				return new BitmapDrawable(bitmap);
//			} catch (Exception e) {
//				// TODO: ha]ndle exception
//			}
//		} else {
//			return new BitmapDrawable(BitmapFactory.decodeResource(
//					mContext.getResources(), R.drawable.no_image));
//		}
//		return null;
//	}
	public void clear(){
		for(Article article:articles)
			article.clear();
	}
}
