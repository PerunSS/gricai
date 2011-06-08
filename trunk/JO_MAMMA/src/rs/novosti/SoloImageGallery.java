package rs.novosti;

import java.util.List;

import rs.novosti.adapter.SoloImageGalleryAdapter;
import rs.novosti.model.Article;
import rs.novosti.model.Category;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class SoloImageGallery extends Activity{
	
	private MyGallery gallery;
	private List<Article> articles;
	private int position;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.solo_image_gallery);
		
		Bundle b = getIntent().getExtras();
		position=b.getInt("position");
		Category cat=(Category)b.getSerializable("category");
		articles = cat.getArticles();
		
		gallery = (MyGallery)findViewById(R.id.solo_image_gallery);
		gallery.setAdapter(new SoloImageGalleryAdapter(this, articles));
		gallery.setSelection(position);
		
		
	}
}
