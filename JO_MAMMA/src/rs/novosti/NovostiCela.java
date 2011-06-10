package rs.novosti;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import rs.novosti.model.Article;
import rs.novosti.model.Main;
import rs.novosti.model.font.FontSize;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NovostiCela extends Activity {

	private TextView articleTitle;
	private TextView articleSource;
	private TextView articleShortText;
	private ImageView articlePhoto;
	private TextView articleFullText;
	private Button homeButton;
	
	private boolean hasTitle = false;
	private boolean hasSource = false;
	private boolean hasDescription = false;
	private boolean hasText = false;
	ProgressDialog progressDialog;
	private Article article;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.full_article_view);
		
		article = (Article) getIntent().getExtras().get("article");
		new LoadArticle().execute(article);
		
//		Article article = (Article) getIntent().getExtras().get("article");
//		article = Main.getInstance().readArticle(article);
		
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
		LinearLayout refresh = (LinearLayout) findViewById(R.id.refresh_button_full);
		refresh.setClickable(true);
		refresh.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				refresh();
				
			}
		});
		final TextView technicomView = (TextView) findViewById(R.id.tehnicom_solutions_full);
		technicomView.setText(Html.fromHtml(/*"<style type=\"text/css\">" +
				"A:link {text-decoration: none; color: white;}" +
				"A:visited {text-decoration: none; color: white;}" +
				"A:active {text-decoration: none; color: white;}" +
				"A:hover {text-decoration: underline; color: red;}" +*/
				"</style><a href=\"http://www.tehnicomsolutions.com\">Tehnicom computers</a>"));
		technicomView.setMovementMethod(LinkMovementMethod.getInstance());
		technicomView.setLinkTextColor(Color.WHITE);
	
	}

	private void setFontSize(List<Integer> list){
		if (hasTitle)	articleTitle.setTextSize(list.get(0));
		if (hasSource)  articleSource.setTextSize(list.get(1));
		if (hasDescription)  articleShortText.setTextSize(list.get(2));
		if (hasText)  articleFullText.setTextSize(list.get(3));
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

	public void refresh(){
		article = (Article) getIntent().getExtras().get("article");
		new LoadArticle().execute(article);
	}
	
	private class LoadArticle extends AsyncTask<Article, Void, Article>{
		
		@Override
		protected Article doInBackground(Article... params) {
			return Main.getInstance().readArticle(params[0]);
		}
		
		@Override
		protected void onPreExecute() {
			progressDialog = ProgressDialog.show(NovostiCela.this, "", "Molimo sačekajte");
			super.onPreExecute();
		}
		
		@Override
		protected void onPostExecute(Article result) {
			if(result!=null){
				if(result.getTitle()!=null){
					articleTitle = (TextView) findViewById(R.id.fullArticle_Title);
					articleTitle.setText(Html.fromHtml(result.getTitle()));
					hasTitle = true;
				}
	
				if(result.getDate()!=null){
					articleSource = (TextView) findViewById(R.id.fullArticle_Source);
					articleSource.setText(android.text.format.DateFormat.format("dd.MM.yyyy hh:mm", result.getDate())+"h");
					hasSource = true;
				}
				
				if(result.getDescription()!=null){
					articleShortText = (TextView) findViewById(R.id.fullArticle_ShortText);
					articleShortText.setText(result.getDescription());
					hasDescription = true;
				}
				
				if(result.getPhotoPath()!=null){
					articlePhoto = (ImageView) findViewById(R.id.fullArticle_Photo);
					articlePhoto.setImageDrawable(getResizedDrawable(result
							.getPhotoPath()));
				}
	
				if(result.getText()!=null){
					articleFullText = (TextView) findViewById(R.id.fullArticle_FullText);
					articleFullText.setText(Html.fromHtml(result.getText()));
					hasText = true;
				}
				
				TextView refTime = (TextView) findViewById(R.id.time_refreshed_full);
				refTime.setText(Main.getInstance().getTimeRefreshed());
			}
			progressDialog.dismiss();
			progressDialog = null;
			super.onPostExecute(result);
		}
		
	}
}