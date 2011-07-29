package rs.novosti;

import rs.novosti.adapter.MyGalleryAdapter;
import rs.novosti.model.Category;
import rs.novosti.model.Main;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
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
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Activity class for displaying the image gallery
 * @author aleksandarvaricak
 * 
 */
public class Galerija extends Activity {

	LinearLayout menuView;
	Context context = this;
	MyGalleryAdapter adapter;
	private Button homeButton;
	private String currentCategory;

	Handler mainHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.arg1 == 0) {
				createGallery();

			}
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_gallery);
		HorizontalScrollView horScrView = (HorizontalScrollView) findViewById(R.id.menuScrollView2);
		horScrView.setHorizontalScrollBarEnabled(false);
		menuView = (LinearLayout) findViewById(R.id.Menu);
		menuView.setHorizontalScrollBarEnabled(false);

		homeButton = (Button) findViewById(R.id.HomeButton3);
		homeButton.setBackgroundResource(R.drawable.home_no);
		homeButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				setResult(1440);
				finish();
			}
		});
		new FirstStartTask().execute();

	}

	/**
	 * Method for creating the gallery with given categories
	 */
	private void createGallery() {
		final TextView refTime = (TextView) findViewById(R.id.time_refreshed_gallery);
		refTime.setText(Main.getInstance().getTimeRefreshed());
		final GridView gridview = (GridView) findViewById(R.id.gallery_gridview);
		String categoryNames[] = { "Top vesti", "Sport", "Politika" };
		boolean doClick = true;
		for (String categoryName : categoryNames) {
			final Category category = Main.getInstance().getCategories()
					.get(categoryName);
			final String curCatName = categoryName;
			final TextView tv = new TextView(this);
			tv.setHeight(30);
			tv.setTextSize(16);
			tv.setGravity(Gravity.CENTER);
			tv.setTextColor(0xFFe1302a);
			tv.setBackgroundColor(0xFFFFFFFF);
			tv.setPadding(5, 0, 5, 0);
			tv.setTypeface(Typeface.DEFAULT_BOLD);
			tv.setText(" " + categoryName + " ");
			tv.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {

					if (adapter != null) {
						adapter.clear();
					}
					adapter = new MyGalleryAdapter(context, category
							.getArticles());
					gridview.setAdapter(adapter);
					gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> parent, View v,
								int position, long id) {
							Intent myIntent = new Intent(v.getContext(),
									SoloImageGallery.class);
							Bundle b = new Bundle();
							b.putSerializable("category", category);
							b.putInt("position", position);
							myIntent.putExtras(b);
							startActivityForResult(myIntent, 0);

						}
					});
					gridview.setFocusable(false);
					currentCategory = curCatName;
					resetMenuView();
					tv.setGravity(Gravity.CENTER);
					tv.setTextColor(0xFFFFFFFF);
					tv.setBackgroundColor(0xFFe1302a);
				}
			});
			if (doClick) {
				tv.performClick();
				doClick = false;
			}
			menuView.addView(tv);
		}
		final TextView technicomView = (TextView) findViewById(R.id.tehnicom_solutions_gallery);
		technicomView
				.setText(Html
						.fromHtml("</style><a href=\"http://www.tehnicomsolutions.com\">Tehnicom computers</a>"));
		technicomView.setMovementMethod(LinkMovementMethod.getInstance());
		technicomView.setLinkTextColor(Color.WHITE);
		LinearLayout refresh = (LinearLayout) findViewById(R.id.refresh_button_gallery);
		refresh.setClickable(true);
		refresh.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (currentCategory != null) {
					new RefreshTask().execute();
					refTime.setText(Main.getInstance().getTimeRefreshed());
				}

			}
		});
	}

	public void resetMenuView() {
		for (int i = 0; i < menuView.getChildCount(); i++) {
			TextView tv = (TextView) menuView.getChildAt(i);
			tv.setTextColor(0xFFe1302a);
			tv.setBackgroundColor(0xFFFFFFFF);
			tv.setGravity(Gravity.CENTER);

		}
	}

	/**
	 * Method that gets back to parent activity with a result of 1440
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			setResult(1440);
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * AsyncTask for refreshing images
	 * @author aleksandarvaricak
	 * 
	 */
	private class RefreshTask extends AsyncTask<Void, Void, Void> {
		ProgressDialog progressDialog;

		@Override
		protected Void doInBackground(Void... params) {
			Main.getInstance().refreshCategory(currentCategory);
			return null;
		}

		@Override
		protected void onPreExecute() {
			progressDialog = ProgressDialog.show(context, "",
					"Molimo sačekajte");
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(Void result) {
			adapter.refresh(Main.getInstance().getCategories()
					.get(currentCategory).getArticles());
			progressDialog.dismiss();
			super.onPostExecute(result);
		}

	}

	/**
	 * AsyncTask for loading images for first start
	 * @author aleksandarvaricak
	 * 
	 */
	private class FirstStartTask extends AsyncTask<Void, Void, Void> {
		ProgressDialog progressDialog;

		@Override
		protected Void doInBackground(Void... params) {
			Main.getInstance().readGallery();
			return null;
		}

		@Override
		protected void onPreExecute() {
			progressDialog = ProgressDialog.show(context, "",
					"Molimo sačekajte");
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(Void result) {
			progressDialog.dismiss();

			Message msg = new Message();
			msg.arg1 = 0;
			mainHandler.sendMessage(msg);

			super.onPostExecute(result);
		}

	}

}