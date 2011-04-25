package rs.novosti;

import rs.novosti.adapter.CategoryLayoutAdapter;
import rs.novosti.model.Category;
import rs.novosti.model.Main;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class NovostiCategory extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.category_layout);
		Category currentCategory = (Category) getIntent().getExtras().get(
				"category");
		HorizontalScrollView horScrView = (HorizontalScrollView) findViewById(R.id.categoryLayout_menuScrollView);
		horScrView.setHorizontalScrollBarEnabled(false);
		LinearLayout menuView = (LinearLayout) findViewById(R.id.categoryLayout_Menu);
		menuView.setHorizontalScrollBarEnabled(false);
		Main main = Main.getInstance();
		for (Category cat : main.getCategories()) {
			final Category category = cat;
			TextView tv = new TextView(this);
			tv.setText(" " + cat.getTitle() + " ");
			tv.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent categoryIntent = new Intent(v.getContext(),
							NovostiCategory.class);
					categoryIntent.putExtra("category", category);
					startActivityForResult(categoryIntent, 0);
				}
			});
			// RelativeLayout.LayoutParams lay = new
			// RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
			// RelativeLayout.LayoutParams.WRAP_CONTENT);
			// lay.addRule(RelativeLayout.RIGHT_OF, RelativeLayout.TRUE);
			menuView.addView(tv);
		}

		ListView view = (ListView) findViewById(R.id.categoryLayout_Content);
		view.setAdapter(new CategoryLayoutAdapter(this, currentCategory
				.getArticles(), this));
		view.setItemsCanFocus(true);
		view.setFocusable(false);

	}

	// private void showMessage(String message){
	// Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
	// toast.show();
	// }
}