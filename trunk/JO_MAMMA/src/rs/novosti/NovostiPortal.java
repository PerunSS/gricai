package rs.novosti;

import java.util.ArrayList;
import java.util.List;

import rs.novosti.adapter.CategoryLayoutAdapter;
import rs.novosti.adapter.CategoryPreviewAdapter;
import rs.novosti.model.Article;
import rs.novosti.model.Category;
import rs.novosti.model.Main;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class NovostiPortal extends Activity {
	
	List<Article> sliderArticles;
	CategoryPreviewAdapter categoryPreviewAdapter;
	CategoryLayoutAdapter categoryLayoutAdapter;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		ImageView image = (ImageView) findViewById(R.id.Logo);
		image.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				createMainPage();
			}
		});
		Main main = Main.getInstance();
		HorizontalScrollView horScrView = (HorizontalScrollView) findViewById(R.id.menuScrollView);
		horScrView.setHorizontalScrollBarEnabled(false);
		LinearLayout menuView = (LinearLayout) findViewById(R.id.Menu);
		menuView.setHorizontalScrollBarEnabled(false);
		for (Category cat : main.getCategories()) {
			final Category category = cat;
			TextView tv = new TextView(this);
			tv.setText(" " + cat.getTitle() + " ");
			tv.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					categoryPreviewAdapter.clear();
					
//					Intent categoryIntent = new Intent(v.getContext(),
//							NovostiCategory.class);
//					categoryIntent.putExtra("category", category);
//					startActivityForResult(categoryIntent, 0);
					ListView view = (ListView) findViewById(R.id.Content);
					if(categoryLayoutAdapter!=null){
						categoryLayoutAdapter.clear();
					}
					categoryLayoutAdapter = new CategoryLayoutAdapter(NovostiPortal.this, category
							.getArticles());
					view.setAdapter(categoryLayoutAdapter);
					view.setItemsCanFocus(true);
					view.setFocusable(false);
				}
			});
			// RelativeLayout.LayoutParams lay = new
			// RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
			// RelativeLayout.LayoutParams.WRAP_CONTENT);
			// lay.addRule(RelativeLayout.RIGHT_OF, RelativeLayout.TRUE);
			menuView.addView(tv);
		}

		// Pravim ovu listu artikala cisto da bi imo sta da mu prosledim jer ako
		// je prazna ne pravi nista
		// a doduse to ce svakako posle biti lista ta tri artikla najnovija
		createMainPage();

	}

	private void createMainPage() {
		Main main = Main.getInstance();
		sliderArticles = new ArrayList<Article>();
		sliderArticles.clear();
		for (Category cat : main.getCategories()) {
			if (cat.getArticles() != null && cat.getArticles().size() > 1)
				if (cat.getTitle().equalsIgnoreCase("politika")
						|| cat.getTitle().equalsIgnoreCase("ekonomija")
						|| cat.getTitle().equalsIgnoreCase("sport"))
					sliderArticles.add(cat.getArticles().get(0));
		}
//		sliderArticles.add(new Article());
//		sliderArticles.add(new Article());
//		sliderArticles.add(new Article());
//		MyGallery gallery = (MyGallery) findViewById(R.id.latestNewsGallery);
//		gallery.setAdapter(new LatestNewsGalleryAdapter(this, sliderArticles,
//				this));

		ListView view = (ListView) findViewById(R.id.Content);
		if(categoryPreviewAdapter == null)
			categoryPreviewAdapter = new CategoryPreviewAdapter(this, main.getCategories(),sliderArticles);
		view.setAdapter(categoryPreviewAdapter);
		view.setItemsCanFocus(true);
		view.setFocusable(false);
	}

	// private void showMessage(String message){
	// Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
	// toast.show();
	// }
}