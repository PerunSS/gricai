package rs.novosti;

import java.util.ArrayList;
import java.util.List;

import rs.novosti.adapter.CategoryLayoutAdapter;
import rs.novosti.adapter.CategoryPreviewAdapter;
import rs.novosti.model.Article;
import rs.novosti.model.Category;
import rs.novosti.model.Main;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class NovostiPortal extends Activity {
	
	List<Article> sliderArticles;
	public CategoryPreviewAdapter categoryPreviewAdapter;
	public CategoryLayoutAdapter categoryLayoutAdapter;
	ProgressDialog progressDialog;
	LinearLayout menuView;
	Handler mainHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			progressDialog.dismiss();
			createMenu();
			createMainPage();
		}
	};
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		progressDialog = ProgressDialog.show(this, "", "Molimo sačekajte");
		Thread thread = new Thread(new LoaderThread());
		thread.start();
		//createMenu();

		// Pravim ovu listu artikala cisto da bi imo sta da mu prosledim jer ako
		// je prazna ne pravi nista
		// a doduse to ce svakako posle biti lista ta tri artikla najnovija
		

	}
	
	public void resetMenuView(){
		for(int i=0;i<menuView.getChildCount();i++){
			menuView.getChildAt(i).setBackgroundResource(R.color.menu_background);
		}
	}
	
	public void colorSelected(String name){
		resetMenuView();
		for(int i=0;i<menuView.getChildCount();i++){
			if(((TextView)menuView.getChildAt(i)).getText().toString().trim().equalsIgnoreCase(name)){
				menuView.getChildAt(i).setBackgroundResource(R.color.menu_high_light);
				return;
			}
		}
	}

	private void createMenu() {
		setContentView(R.layout.main);
		ImageView image = (ImageView) findViewById(R.id.Logo);
		HorizontalScrollView horScrView = (HorizontalScrollView) findViewById(R.id.menuScrollView);
		horScrView.setHorizontalScrollBarEnabled(false);
		menuView = (LinearLayout) findViewById(R.id.Menu);
		menuView.setHorizontalScrollBarEnabled(false);
		image.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				resetMenuView();
				createMainPage();
			}
		});
		
		
		for (Category cat : Main.getInstance().getCategories()) {
			final Category category = cat;
			final TextView tv = new TextView(this);
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
					resetMenuView();
					tv.setBackgroundResource(R.color.menu_high_light);
				}
			});
			// RelativeLayout.LayoutParams lay = new
			// RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
			// RelativeLayout.LayoutParams.WRAP_CONTENT);
			// lay.addRule(RelativeLayout.RIGHT_OF, RelativeLayout.TRUE);
			tv.setBackgroundResource(R.color.menu_background);
			menuView.addView(tv);
		}
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
	
	private class LoaderThread implements Runnable{

		@Override
		public void run() {
			Main.getInstance();
			mainHandler.sendEmptyMessage(0);
		}
		
	}
	
}