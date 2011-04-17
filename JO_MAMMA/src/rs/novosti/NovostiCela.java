package rs.novosti;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class NovostiCela extends Activity {
	
	// ova se prikazuje samo na klik svake prve novosti u listi u prvom aktivitiju 
	// ili ti jedino sam to ubacio u NovostiKategorija na liniji 45 da se poziva
	// tamo sam vec napisao razlog zasto odatle bash :D
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.full_article_view);
        HorizontalScrollView horScrView = (HorizontalScrollView) findViewById(R.id.fullArticle_menuScrollView);
        horScrView.setHorizontalScrollBarEnabled(false);
        LinearLayout menuView = (LinearLayout)findViewById(R.id.fullArticle_Menu);
        menuView.setHorizontalScrollBarEnabled(false);
        for(int i=0;i<10;i++){
        	final int id = i;
        	TextView tv = new TextView(this);
        	tv.setText(" menu "+i);
        	tv.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					showMessage("clicked on menu item "+ id);
					
				}
			});
        	//RelativeLayout.LayoutParams lay = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        	//lay.addRule(RelativeLayout.RIGHT_OF, RelativeLayout.TRUE);
        	menuView.addView(tv);
        }
   
        TextView articleTitle = (TextView)findViewById(R.id.fullArticle_Title);
        articleTitle.setText("nasloooov al aj kao da bude veci nesto ovo ono");
        
        TextView articleSource = (TextView)findViewById(R.id.fullArticle_Source);
        articleSource.setText("izvor recimo tanjug");
        
        TextView articleShortText = (TextView)findViewById(R.id.fullArticle_ShortText);
        articleShortText.setText("ovde kao kratak opis ovo ono djidji midji");
        
        ImageView articlePhoto = (ImageView)findViewById(R.id.fullArticle_Photo);
        articlePhoto.setBackgroundResource(R.drawable.b1);
        
        TextView articleFullText = (TextView)findViewById(R.id.fullArticle_FullText);
        articleFullText.setText("e ovde sad ima mnogo teksta bla bal la la kuraca palac" +
        		"ovo ono bla bla sad;ewr;wev;nf;v i dalje tuki palac i tako");
        
    }
    
    private void showMessage(String message){
    	Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
    	toast.show();
    }
}