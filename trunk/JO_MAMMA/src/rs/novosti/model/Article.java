package rs.novosti.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.Date;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ImageView;

public class Article implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private String shortText;
	private String text;
	private String link;
	private Date date;
	private String photoPath;
	private String smallPhotoPath;
	private String description;
	private int imageLength;
	private transient ImageView view;
	private transient Drawable bigDrawable;
	private boolean generated = false;
	private transient Bitmap bitmap;
	private transient Bitmap smallBitmap;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShortText() {
		return shortText;
	}

	public void setShortText(String shortText) {
		this.shortText = shortText;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		int index = text.indexOf('>') + 1;
		if (index < 0)
			index = 0;
		text = text.substring(index);
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public void setImageLength(int imageLength) {
		this.imageLength = imageLength;
	}

	public int getImageLength() {
		return imageLength;
	}

	public void generateSmallPhoto() {
		if (!generated) {
			new DownloadImagesTask().execute(photoPath, "small");
			generated = true;
		}
		if (view != null && bitmap != null) {
			if (smallBitmap == null)
				smallBitmap = Bitmap.createScaledBitmap(bitmap, 48, 48, true);
			view.setImageBitmap(smallBitmap);
			bitmap.recycle();
		}
	}

	public void generateBigPhoto(double ratio) {

	}

	public void setView(ImageView view) {
		this.view = view;
	}

	public ImageView getView() {
		return view;
	}

	public void setBigDrawable(Drawable bigDrawable) {
		this.bigDrawable = bigDrawable;
	}

	public Drawable getBigDrawable() {
		return bigDrawable;
	}

	private class DownloadImagesTask extends AsyncTask<String, Void, Bitmap> {

		private boolean small = false;

		@Override
		protected Bitmap doInBackground(String... params) {
			if (params.length > 1 && params[1] != null)
				small = true;
			return getSmallIcon(params[0]);
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			bitmap = result;
			if (small && result!=null) {
				smallBitmap = Bitmap.createScaledBitmap(result, 48, 48,
						true);
				view.setImageBitmap(smallBitmap);
				result.recycle();
			}
		}

		private Bitmap getSmallIcon(String url) {
			InputStream is = null;
			if(url == null)
				return null;
			url = url.replaceAll(" ", "%20");
			try {
				is = new URL(url).openStream();
				if (is != null) {
					BitmapFactory.Options options = new BitmapFactory.Options();
					options.inSampleSize = 4;
					Bitmap bitmap = BitmapFactory.decodeStream(is, null,
							options);

					System.out.println("created bitmap in article . . .");
					// int width = bitmap.getWidth();
					// int height = bitmap.getHeight();
					// double ratio = 48. / width;
					// if (ratio > 48. / height) {
					// ratio = 48. / height;
					// }
					return bitmap;
				}
			} catch (Exception e) {
				System.out.println("ARTICLE");
				e.printStackTrace();
			} finally {
				if (is != null)
					try {
						is.close();
					} catch (IOException e) {
						System.out.println("ARTICLE FINALLY");
						e.printStackTrace();
					}
			}
			return null;
		}

	}

	public void clear() {
		if (bitmap != null)
			bitmap.recycle();
		if (smallBitmap != null)
			smallBitmap.recycle();
		bitmap = null;
		smallBitmap = null;
		generated = false;
	}

	public void setSmallPhotoPath(String smallPhotoPath) {
		this.smallPhotoPath = smallPhotoPath;
	}

	public String getSmallPhotoPath() {
		return smallPhotoPath;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getLink() {
		return link;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
