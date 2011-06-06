package rs.novosti;

import rs.novosti.adapter.MyGalleryAdapter;
import rs.novosti.model.Category;
import rs.novosti.model.Main;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;



public class Galerija extends Activity {
	
	LinearLayout menuView;
	Context context = this;
	MyGalleryAdapter adapter;
	private Button homeButton;
	
    /** Called when the activity is first created. */
    @Override        
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_gallery);
        HorizontalScrollView horScrView = (HorizontalScrollView) findViewById(R.id.menuScrollView2);
		horScrView.setHorizontalScrollBarEnabled(false);
        menuView = (LinearLayout) findViewById(R.id.Menu);
		menuView.setHorizontalScrollBarEnabled(false);
        
		TextView refTime = (TextView) findViewById(R.id.time_refreshed_gallery);
		refTime.setText(Main.getInstance().getTimeRefreshed());
		
		homeButton = (Button) findViewById(R.id.HomeButton3);
		homeButton.setBackgroundResource(R.drawable.home_no);
		homeButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				setResult(1440);
				finish();
			}
		});
		
//		adapter = new MyGalleryAdapter(this,Main.getInstance().getGalleryCategories().get(0).getArticles());
        final GridView gridview = (GridView) findViewById(R.id.gallery_gridview);
//        gridview.setAdapter(adapter);
        
        boolean doClick = true;
        for (Category cat : Main.getInstance().getGalleryCategories()) {
			final Category category = cat;
			final TextView tv = new TextView(this);
			tv.setHeight(30);
			tv.setTextSize(16);
			tv.setGravity(Gravity.CENTER);
			tv.setTextColor(0xFFe1302a);
			tv.setBackgroundColor(0xFFFFFFFF);
			tv.setPadding(5, 0, 5, 0);
			tv.setTypeface(Typeface.DEFAULT_BOLD);
			tv.setText(" " + cat.getTitle() + " ");
			tv.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					
					if (adapter != null) {
						adapter.clear();
					}
					adapter = new MyGalleryAdapter(context,category.getArticles());
					gridview.setAdapter(adapter);
					
					gridview.setFocusable(false);
					
					resetMenuView();
					tv.setGravity(Gravity.CENTER);
					tv.setTextColor(0xFFFFFFFF);
					tv.setBackgroundColor(0xFFe1302a);
				}
			});
			if(doClick){
				tv.performClick();
				doClick = false;
			}
			// RelativeLayout.LayoutParams lay = new
			// RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
			// RelativeLayout.LayoutParams.WRAP_CONTENT);
			// lay.addRule(RelativeLayout.RIGHT_OF, RelativeLayout.TRUE);
			// tv.setBackgroundResource(R.color.menu_background);
			menuView.addView(tv);
		}
        
        
        
//        gridview.setOnItemClickListener(new OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//                Toast.makeText(Galerija.this, "" + position, Toast.LENGTH_SHORT).show();
//            }
//        });
//        
//        final Article article = new Article();
//        article.setPhotoPath("http://www.novosti.rs/upload/images/2011/04/2404/po-toma.jpg");
//        Button dugme = (Button)findViewById(R.id.dugme);
//        dugme.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				adapter.add(new Article());
//				adapter.notifyDataSetChanged();
//			}
//		});
    }
    public void resetMenuView() {
		for (int i = 0; i < menuView.getChildCount(); i++) {
			TextView tv = (TextView) menuView.getChildAt(i);
			tv.setTextColor(0xFFe1302a);
			tv.setBackgroundColor(0xFFFFFFFF);
			tv.setGravity(Gravity.CENTER);

		}
	}
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
        	setResult(1440);
			finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}