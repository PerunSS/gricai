package rs.novosti;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import rs.novosti.model.Article;
import rs.novosti.model.Main;
import rs.novosti.model.font.FontSize;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class NovostiCela extends Activity {

	private TextView articleTitle;
	private TextView articleSource;
	private TextView articleShortText;
	private ImageView articlePhoto;
	private TextView articleFullText;
	private Button homeButton;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.full_article_view);
		
		
		
		Article article = (Article) getIntent().getExtras().get("article");
		Main.getInstance().readArticle(article);
		
		homeButton = (Button) findViewById(R.id.HomeButton2);
		homeButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				setResult(1440);
				finish();
			}
		});
		
		Button gallery = (Button) findViewById(R.id.ImageGalleryButton2);
		gallery.setBackgroundResource(R.drawable.photo_gallery_no);
		gallery.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				setResult(1410);
				finish();
			}
		});
		
		articleTitle = (TextView) findViewById(R.id.fullArticle_Title);
		articleTitle.setText(Html.fromHtml(article.getTitle()));

		articleSource = (TextView) findViewById(R.id.fullArticle_Source);
		articleSource.setText(android.text.format.DateFormat.format("dd.MM.yyyy hh:mm", article.getDate())+"h");

		articleShortText = (TextView) findViewById(R.id.fullArticle_ShortText);
		articleShortText.setText(article.getShortText());

		articlePhoto = (ImageView) findViewById(R.id.fullArticle_Photo);
		articlePhoto.setBackgroundDrawable(getResizedDrawable(article
				.getPhotoPath()));

		articleFullText = (TextView) findViewById(R.id.fullArticle_FullText);
		articleFullText.setText(Html.fromHtml(article.getText()));
		
		TextView refTime = (TextView) findViewById(R.id.time_refreshed_full);
		refTime.setText(Main.getInstance().getTimeRefreshed());
	
	}

	private void setFontSize(List<Integer> list){
		articleTitle.setTextSize(list.get(0));
		articleSource.setTextSize(list.get(1));
		articleShortText.setTextSize(list.get(2));
		articleFullText.setTextSize(list.get(3));
	}
	
	private Drawable getResizedDrawable(String url) {
		if(url==null){
			return getResources().getDrawable(R.drawable.no_image);
		} else { 
			InputStream is = null;
			url = url.replaceAll(" ", "%20");
			try {
				is = new URL(url).openStream();
				Bitmap bitmap = BitmapFactory.decodeStream(is);
//				int width = bitmap.getWidth();
//				int height = bitmap.getHeight();
//				int screenWidth = getWindowManager().getDefaultDisplay().getWidth();
//				int screenHeight = getWindowManager().getDefaultDisplay()
//						.getHeight();
//				double ratio = ((double) screenWidth) / width;
//				if (ratio > ((double) screenHeight - 100) / height) {
//					ratio = ((double) screenHeight - 100) / height;
//				}
//				bitmap = Bitmap.createScaledBitmap(bitmap, (int) (width * ratio),
//						(int) (height * ratio), true);
				return new BitmapDrawable(bitmap);
			} catch (Exception e) {
			}
		}
		return null;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.small:
	    	if (!item.isChecked()){
	    		setFontSize(FontSize.getInstance().getDefaultSizeFullArticle());
	    		item.setChecked(true);
	    	}
	        return true;
	    case R.id.medium:
	    	if (!item.isChecked()){
	    		setFontSize(FontSize.getInstance().getMediumSizeFullArticle());
	    		item.setChecked(true);
	    	}
	        return true;
	    case R.id.big:
	    	if (!item.isChecked()){
	    		setFontSize(FontSize.getInstance().getBigSizeFullArticle());
	    		item.setChecked(true);
	    	}
	        return true;    
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.full_article_menu, menu);
	    return true;
	}
}