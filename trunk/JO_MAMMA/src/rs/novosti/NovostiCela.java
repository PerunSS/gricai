package rs.novosti;

import java.io.InputStream;
import java.net.URL;

import rs.novosti.model.Article;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class NovostiCela extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.full_article_view);
		Article article = (Article) getIntent().getExtras().get("article");

		TextView articleTitle = (TextView) findViewById(R.id.fullArticle_Title);
		articleTitle.setText(article.getTitle());

		TextView articleSource = (TextView) findViewById(R.id.fullArticle_Source);
		articleSource.setText("...");

		TextView articleShortText = (TextView) findViewById(R.id.fullArticle_ShortText);
		articleShortText.setText(article.getShortText());

		ImageView articlePhoto = (ImageView) findViewById(R.id.fullArticle_Photo);
		articlePhoto.setBackgroundDrawable(getResizedDrawable(article
				.getPhotoPath()));

		TextView articleFullText = (TextView) findViewById(R.id.fullArticle_FullText);
		articleFullText.setText(article.getText());

	}

	private Drawable getResizedDrawable(String url) {
		InputStream is = null;
		url = url.replaceAll(" ", "%20");
		try {
			is = new URL(url).openStream();
			Bitmap bitmap = BitmapFactory.decodeStream(is);
			int width = bitmap.getWidth();
			int height = bitmap.getHeight();
			int screenWidth = getWindowManager().getDefaultDisplay().getWidth();
			int screenHeight = getWindowManager().getDefaultDisplay()
					.getHeight();
			double ratio = ((double) screenWidth) / width;
			if (ratio > ((double) screenHeight - 100) / height) {
				ratio = ((double) screenHeight - 100) / height;
			}
			bitmap = Bitmap.createScaledBitmap(bitmap, (int) (width * ratio),
					(int) (height * ratio), true);
			return new BitmapDrawable(bitmap);
		} catch (Exception e) {
		}
		return null;
	}

}