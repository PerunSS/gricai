package com.cerspri.star.NickiMinaj;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cerspri.star.NickiMinaj.widget.MultiDirectionSlidingDrawer;

public class NickiStarActivity extends Activity {
	
	Button mOpenButton;
	MultiDirectionSlidingDrawer mDrawer;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mOpenButton = (Button) findViewById( R.id.cerspri_home_button );
        mDrawer = (MultiDirectionSlidingDrawer) findViewById( R.id.drawer );
        mDrawer.animateOpen();
        mOpenButton.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick( View v )
			{
				if( !mDrawer.isOpened() ){
					mDrawer.animateOpen();
				} else {
					mDrawer.animateClose();
				}
			}
        });
    }
    @Override
    public void onContentChanged(){
        super.onContentChanged();     

    } 
   
}