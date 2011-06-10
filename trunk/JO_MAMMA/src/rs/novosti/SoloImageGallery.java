package rs.novosti;

import java.util.List;

import rs.novosti.adapter.SoloImageGalleryAdapter;
import rs.novosti.model.Article;
import rs.novosti.model.Category;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class SoloImageGallery extends Activity{
	
	private MyGallery gallery;
	private List<Article> articles;
	private int position;
	boolean toggle = true;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.solo_image_gallery);
		final TextView text = (TextView)findViewById(R.id.solo_gallery_text);
		final TextView numbering = (TextView)findViewById(R.id.solo_gallery_numbering);
		text.setVisibility(View.INVISIBLE);
		Bundle b = getIntent().getExtras();
		position=b.getInt("position");
		Category cat=(Category)b.getSerializable("category");
		articles = cat.getArticles();
		
		gallery = (MyGallery)findViewById(R.id.solo_image_gallery);
		gallery.setAdapter(new SoloImageGalleryAdapter(this, articles));
		gallery.setSelection(position);
		gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, 
	                View v, int position, long id) {
				if (toggle){
					text.setVisibility(View.VISIBLE);
				} else {
					text.setVisibility(View.INVISIBLE);
				}
				toggle = !toggle;
			}
		});
		gallery.setOnItemSelectedListener(new OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                    int position, long id) {
            	text.setText(articles.get(position).getTitle());
            	numbering.setText((position+1)+"/"+articles.size());
		
            }

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
}
