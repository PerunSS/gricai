package rs.novosti;

import java.io.InputStream;
import java.net.URL;

import rs.novosti.model.Article;
import rs.novosti.model.Category;
import rs.novosti.model.Main;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NovostiCela extends Activity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.full_article_view);
        Article article = (Article) getIntent().getExtras().get("article");
        HorizontalScrollView horScrView = (HorizontalScrollView) findViewById(R.id.fullArticle_menuScrollView);
        horScrView.setHorizontalScrollBarEnabled(false);
        LinearLayout menuView = (LinearLayout)findViewById(R.id.fullArticle_Menu);
        menuView.setHorizontalScrollBarEnabled(false);
        Main main = Main.getInstance();
        for (Category cat : main.getCategories()) {
			final Category category = cat;
			TextView tv = new TextView(this);
			tv.setText(" " + cat.getName() + " ");
			tv.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent categoryIntent = new Intent(v.getContext(),
							NovostiCategory.class);
					categoryIntent.putExtra("category", category);
					startActivityForResult(categoryIntent, 0);
				}
			});
			menuView.addView(tv);
		}
   
        TextView articleTitle = (TextView)findViewById(R.id.fullArticle_Title);
        articleTitle.setText(article.getName());
        
        TextView articleSource = (TextView)findViewById(R.id.fullArticle_Source);
        articleSource.setText("...");
        
        TextView articleShortText = (TextView)findViewById(R.id.fullArticle_ShortText);
        articleShortText.setText(article.getShortText());
        
        ImageView articlePhoto = (ImageView)findViewById(R.id.fullArticle_Photo);
        articlePhoto.setBackgroundDrawable(getResizedDrawable(article.getPhotoPath()));
        
        TextView articleFullText = (TextView)findViewById(R.id.fullArticle_FullText);
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
			int screenWidth = getWindowManager().getDefaultDisplay()
					.getWidth();
			int screenHeight = getWindowManager().getDefaultDisplay()
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
    
}