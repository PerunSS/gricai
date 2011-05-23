package rs.novosti;

import rs.novosti.adapter.MyGalleryAdapter;
import rs.novosti.model.Article;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;



public class Galerija extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_gallery);
        
        final MyGalleryAdapter adapter = new MyGalleryAdapter(this);
        final GridView gridview = (GridView) findViewById(R.id.gallery_gridview);
        gridview.setAdapter(adapter);
       
        
        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(Galerija.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });
        
        final Article article = new Article();
        article.setPhotoPath("http://www.novosti.rs/upload/images/2011/04/2404/po-toma.jpg");
        Button dugme = (Button)findViewById(R.id.dugme);
        dugme.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				adapter.add(new Article());
				adapter.notifyDataSetChanged();
			}
		});
    }
}