package rs.novosti.model;

import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.Date;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
	private String source;
	private Date date;
	private String photoPath;
	private String oneLine;
	private int imageLength;
	private transient ImageView view;
	private boolean generated = false;
	private transient Bitmap bitmap;

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

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
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

	public void setOneLine(String oneLine) {
		this.oneLine = oneLine;
	}

	public String getOneLine() {
		return oneLine;
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
			view.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 48, 48, true));
		}
	}

	public void setView(ImageView view) {
		this.view = view;
	}

	public ImageView getView() {
		return view;
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
			if (small)
				view.setImageBitmap(Bitmap.createScaledBitmap(result, 48, 48,
						true));
		}

		private Bitmap getSmallIcon(String url) {
			InputStream is = null;
			url = url.replaceAll(" ", "%20");
			try {
				is = new URL(url).openStream();
				Bitmap bitmap = BitmapFactory.decodeStream(is);
				// int width = bitmap.getWidth();
				// int height = bitmap.getHeight();
				// double ratio = 48. / width;
				// if (ratio > 48. / height) {
				// ratio = 48. / height;
				// }
				return bitmap;
			} catch (Exception e) {
			}
			return null;
		}

	}

}
