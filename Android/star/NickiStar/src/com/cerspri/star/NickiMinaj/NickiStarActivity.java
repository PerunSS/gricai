package com.cerspri.star.NickiMinaj;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cerspri.star.NickiMinaj.widget.MultiDirectionSlidingDrawer;

/**
 * 
 * @author churava
 * main application activity
 *	
 */

public class NickiStarActivity extends Activity {
	
	Button toggleMenuButton;
	Button factsButton;
	MultiDirectionSlidingDrawer mDrawer;
	MultiDirectionSlidingDrawer factsDrawer;
	Button quotesButton;
	int state;
	
    /**
     * Done on activity creation
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mDrawer.animateOpen();
        state = 0;
        
        /**
         * layout objects asignment
         */
        toggleMenuButton = (Button) findViewById( R.id.toggle_menu );
        mDrawer = (MultiDirectionSlidingDrawer) findViewById( R.id.menuDrawer );
        factsButton = (Button) findViewById( R.id.facts_button);
        factsDrawer = (MultiDirectionSlidingDrawer) findViewById( R.id.factsDrawer );
        quotesButton = (Button) findViewById(R.id.quotes_button);
        
        /**
         * listener for opening/closing menu
         */
        toggleMenuButton.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick( View v ){
				if( !mDrawer.isOpened() ){
					if (factsDrawer.isOpened()){
						factsDrawer.animateClose();
					}
					mDrawer.animateOpen();
					toggleMenuButton.setBackgroundResource(R.drawable.close_menu_button);
				} else {
					mDrawer.animateClose();
					if (state == 1){
						factsButton.performClick();
					} else if (state == 2){
						quotesButton.performClick();
					}
					toggleMenuButton.setBackgroundResource(R.drawable.open_menu_button);}
			}
        });
        
        
        /**
         * listener for facts button
         */
        factsButton.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mDrawer.animateClose();
				factsDrawer.animateOpen();
				state = 1;
				toggleMenuButton.setBackgroundResource(R.drawable.open_menu_button);
			}
		});
        
        
        /**
         * listener for quotes button
         */
        factsButton.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mDrawer.animateClose();
				factsDrawer.animateOpen();
				state = 2;
				toggleMenuButton.setBackgroundResource(R.drawable.open_menu_button);
			}
		});
        
        
    }
    @Override
    public void onContentChanged(){
        super.onContentChanged();     

    } 
   
}