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
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SoloImageGalleryAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private List<Article> articles;
	private Context context;
	//private TextView text;

	public SoloImageGalleryAdapter(Context context, List<Article> articles, TextView text) {
		inflater = LayoutInflater.from(context);
		this.articles = articles;
		this.context = context;
		//this.text = text;
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
		final Article article = Main.getInstance().readArticle(articles.get(position));
		Holder holder = null;
//		final FrameLayout layout = new FrameLayout(context);
//		layout.setMinimumHeight(480);
//		layout.setMinimumWidth(320);
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.solo_image_gallery_element,null );
			holder = new Holder();
			holder.image = (ImageView) convertView
					.findViewById(R.id.solo_gallery_image);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}

		// Article article = articles.get(position);
		// System.out.println(article.getName());
		// new BitmapFactory();
		// holder.latestArticleImage.setImageBitmap(BitmapFactory.decodeFile(article.getPhotoPath()));
		// holder.latestArticleTitle.setText(article.getName());
		if (article.getBigDrawable() == null)
			article.setBigDrawable(getResizedDrawable(article.getPhotoPath()));
//		holder.image.setMinimumWidth(320);
		holder.image.setImageDrawable(article.getBigDrawable());
		holder.image.setBackgroundColor(Color.BLACK);
//		text.setText(article.getTitle());
		return convertView;
	}

	private Drawable getResizedDrawable(String url) {
		
		InputStream is = null;
		if (url != null) {
		url = url.replaceAll(" ", "%20");
		try {
			is = new URL(url).openStream();
			BitmapFactory.Options options = new BitmapFactory.Options();
			//options.inSampleSize = 2;
			Bitmap bitmap = BitmapFactory.decodeStream(is,null, options);

			// BitmapFactory.Options op = new BitmapFactory.Options();
			// op.inSampleSize = 8;
			// bitmap = BitmapFactory.decodeStream(is,null, op);
			// int width = bitmap.getWidth();
			// int height = bitmap.getHeight();
//			int screenWidth = ((Activity)context).getWindowManager().getDefaultDisplay()
//					.getWidth();
//			int screenHeight = ((Activity)context).getWindowManager().getDefaultDisplay()
//					.getHeight();
//			int maxHeight = (screenHeight - 100) / 2;
//			Bitmap bitmap2 = Bitmap.createScaledBitmap(bitmap, screenWidth, maxHeight,
//					true);
//			bitmap.recycle();
			Drawable drawable = new BitmapDrawable(bitmap);
			// drawable.setBounds(0, 0, screenWidth, maxHeight);
			// bitmap = Bitmap.createScaledBitmap(bitmap, (int) (width * ratio),
			// ((int) (height * ratio) > maxHeight ? maxHeight
			// : (int) (height * ratio)), true);
			return drawable;
		} catch (Exception e) {
			// TODO: handle exception
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

	private class Holder {
		ImageView image;
		
	}
	
	public void clearAll(){
		for(Article article:articles){
			article.clear();
		}
	}
}

