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
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class NovostiPortal extends Activity {

	Category naslovna;

	List<Article> sliderArticles;
	public CategoryPreviewAdapter categoryPreviewAdapter;
	public CategoryLayoutAdapter categoryLayoutAdapter;
	public CategoryLayoutAdapter activeCategory;
	ProgressDialog progressDialog;
	LinearLayout menuView;
	private String currentCategory;
	
	Handler mainHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// progressDialog.dismiss();
			if(msg.arg1 == 0){
				createMenu();
				createMainPage();
			}
			if(msg.arg1 == 1){
				if(progressDialog!=null)
					progressDialog.dismiss();
			}
		}
	};

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		// progressDialog = ProgressDialog.show(this, "", "Molimo sačekajte");
		setContentView(R.layout.loading);
		Thread thread = new Thread(new LoaderThread());
		thread.start();
		// createMenu();

		// Pravim ovu listu artikala cisto da bi imo sta da mu prosledim jer ako
		// je prazna ne pravi nista
		// a doduse to ce svakako posle biti lista ta tri artikla najnovija

	}

	public void resetMenuView() {
		for (int i = 0; i < menuView.getChildCount(); i++) {
			TextView tv = (TextView) menuView.getChildAt(i);
			tv.setGravity(Gravity.CENTER);
			tv.setTextColor(0xFFd7181f);
			tv.setBackgroundColor(0xFFFFFFFF);
		}
	}

	public void colorSelected(String name) {
		resetMenuView();
		for (int i = 0; i < menuView.getChildCount(); i++) {
			if (((TextView) menuView.getChildAt(i)).getText().toString().trim()
					.equalsIgnoreCase(name)) {
				menuView.getChildAt(i).setBackgroundResource(
						R.color.menu_high_light);
				return;
			}
		}
	}

	private void createMenu() {
		setContentView(R.layout.main);
		Button home = (Button) findViewById(R.id.HomeButton);
		Button gallery = (Button) findViewById(R.id.ImageGalleryButton);
		gallery.setBackgroundResource(R.drawable.photo_gallery_no);
		HorizontalScrollView horScrView = (HorizontalScrollView) findViewById(R.id.menuScrollView);
		horScrView.setHorizontalScrollBarEnabled(false);
		menuView = (LinearLayout) findViewById(R.id.Menu);
		menuView.setHorizontalScrollBarEnabled(false);
		home.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				currentCategory = null;
				resetMenuView();
				createMainPage();
			}
		});

		gallery.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(v.getContext(), Galerija.class);
				// myIntent.putExtra("article", article);
				startActivityForResult(myIntent, 0);
			}
		});

		String names[] = { "Top vesti", "Politika", "Društvo", "Ekonomija",
				"Hronika", "Beograd", "Dosije", "Spektakl", "Život plus",
				"Tehnologije", "Auto", "Sport" };

		for (String categoryName:names) {
			final String name = categoryName;
			final TextView tv = new TextView(this);
			tv.setHeight(30);
			tv.setTextSize(16);
			tv.setTextColor(0xFFd7181f);
			tv.setBackgroundColor(0xFFFFFFFF);
			tv.setPadding(5, 0, 5, 0);
			tv.setGravity(Gravity.CENTER);
			tv.setTypeface(Typeface.DEFAULT_BOLD);
			tv.setText(" " + name + " ");
			tv.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					

					// Intent categoryIntent = new Intent(v.getContext(),
					// NovostiCategory.class);
					// categoryIntent.putExtra("category", category);
					// startActivityForResult(categoryIntent, 0);
					
					if (categoryLayoutAdapter != null) {
						categoryLayoutAdapter.clear();
					}
					new CategoryLoaderTask().execute(name);
					resetMenuView();
					tv.setGravity(Gravity.CENTER);
					tv.setTextColor(0xFFFFFFFF);
					tv.setBackgroundColor(0xFFd7181f);
					
				}
			});
			// RelativeLayout.LayoutParams lay = new
			// RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
			// RelativeLayout.LayoutParams.WRAP_CONTENT);
			// lay.addRule(RelativeLayout.RIGHT_OF, RelativeLayout.TRUE);
			// tv.setBackgroundResource(R.color.menu_background);
			menuView.addView(tv);
		}
	}

	private void createMainPage() {
		Main main = Main.getInstance();
		sliderArticles = new ArrayList<Article>();
		sliderArticles.clear();

		if(Main.getInstance().getCategories().get("Politika").getArticles().size()>0)
			sliderArticles.add(Main.getInstance().getCategories().get("Politika").getArticles().get(0));
		if(Main.getInstance().getCategories().get("Društvo").getArticles().size()>0)
			sliderArticles.add(Main.getInstance().getCategories().get("Društvo").getArticles().get(0));
		if(Main.getInstance().getCategories().get("Ekonomija").getArticles().size()>0)
			sliderArticles.add(Main.getInstance().getCategories().get("Ekonomija").getArticles().get(0));
		if(Main.getInstance().getCategories().get("Sport").getArticles().size()>0)
			sliderArticles.add(Main.getInstance().getCategories().get("Sport").getArticles().get(0));
		
		// sliderArticles.add(new Article());
		// sliderArticles.add(new Article());
		// sliderArticles.add(new Article());
		// MyGallery gallery = (MyGallery) findViewById(R.id.latestNewsGallery);
		// gallery.setAdapter(new LatestNewsGalleryAdapter(this, sliderArticles,
		// this));

		ListView view = (ListView) findViewById(R.id.Content);
		if (categoryPreviewAdapter == null)
			categoryPreviewAdapter = new CategoryPreviewAdapter(this, main
					.getNaslovna().getArticles(), sliderArticles);
		view.setAdapter(categoryPreviewAdapter);
		view.setItemsCanFocus(true);
		view.setFocusable(false);

		final TextView refTime = (TextView) findViewById(R.id.time_refreshed);
		refTime.setText(Main.getInstance().getTimeRefreshed());
		LinearLayout refresh = (LinearLayout) findViewById(R.id.refresh_button);
		refresh.setClickable(true);
		refresh.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(currentCategory!=null){
					activeCategory.refresh();
					refTime.setText(Main.getInstance().getTimeRefreshed());
				}
				
			}
		});
		final TextView technicomView = (TextView) findViewById(R.id.tehnicom_solutions);
		technicomView.setText(Html.fromHtml(/*"<style type=\"text/css\">" +
				"A:link {text-decoration: none; color: white;}" +
				"A:visited {text-decoration: none; color: white;}" +
				"A:active {text-decoration: none; color: white;}" +
				"A:hover {text-decoration: underline; color: red;}" +*/
				"</style><a href=\"http://www.tehnicomsolutions.com\">Tehnicom solutions</a>"));
		technicomView.setMovementMethod(LinkMovementMethod.getInstance());
		technicomView.setLinkTextColor(Color.WHITE);
	}

	// private void showMessage(String message){
	// Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
	// toast.show();
	// }

	private class LoaderThread implements Runnable {

		@Override
		public void run() {
			Main.getInstance();
			Message msg = new Message();
			msg.arg1 = 0;
			mainHandler.sendMessage(msg);
		}

	}
	
	private class CategoryLoaderTask extends AsyncTask<String, Void, CategoryLayoutAdapter>{

		@Override
		protected CategoryLayoutAdapter doInBackground(String... params) {
			categoryLayoutAdapter = new CategoryLayoutAdapter(
					NovostiPortal.this, params[0]);
			currentCategory = params[0];
			return categoryLayoutAdapter;
		}
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = ProgressDialog.show(NovostiPortal.this, "", "Molimo sačekajte");
		}
		
		@Override
		protected void onPostExecute(CategoryLayoutAdapter result) {
			super.onPostExecute(result);
			if(categoryPreviewAdapter!=null)
				categoryPreviewAdapter.clear();
			activeCategory = result;
			categoryLayoutAdapter = null;
			ListView view = (ListView) findViewById(R.id.Content);
			view.setAdapter(result);
			view.setItemsCanFocus(true);
			view.setFocusable(false);
			progressDialog.dismiss();
			
		}
		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 0) {
			if (resultCode == 1440) {
				resetMenuView();
				createMainPage();
			}
			if (resultCode == 1410) {
				Intent myIntent = new Intent(this, Galerija.class);
				// myIntent.putExtra("article", article);
				startActivityForResult(myIntent, 0);
			}
		}
	}

}