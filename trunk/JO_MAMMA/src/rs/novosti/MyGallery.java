package rs.novosti;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Gallery;

public class MyGallery extends Gallery {

	public MyGallery(Context context) {
		super(context);
		
	}
	
	public MyGallery(Context context, AttributeSet attrs) {
		super(context, attrs);
		
	}
	

	public MyGallery(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
	}

//	@Override
//    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//      return super.onFling(e1, e2, 0, velocityY);
//    }


	private boolean isScrollingLeft(MotionEvent e1, MotionEvent e2){
		  return e2.getX() > e1.getX();
		}

	/*@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY){
		  int kEvent;
		  if(isScrollingLeft(e1, e2)){ //Check if scrolling left
		    kEvent = KeyEvent.KEYCODE_DPAD_LEFT;
		  }
		  else{ //Otherwise scrolling right
		    kEvent = KeyEvent.KEYCODE_DPAD_RIGHT;
		  }
		  onKeyDown(kEvent, null);
		  return true;  
	}*/
	
	  @Override
	  public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
	    setAnimationDuration(600);
	    return super.onScroll(e1, e2, distanceX, distanceY);
	  }

	  @Override
	  public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
	    float velMax = 2500f;
	    float velMin = 1000f;
	    float velX = Math.abs(velocityX);
	    if (velX > velMax) {
	      velX = velMax;
	    } else if (velX < velMin) {
	      velX = velMin;
	    }
	    velX -= 600;
	    int k = 500000;
	    int speed = (int) Math.floor(1f / velX * k);
	    setAnimationDuration(speed);

	    int kEvent;
	    if (isScrollingLeft(e1, e2)) {
	      // Check if scrolling left
	      kEvent = KeyEvent.KEYCODE_DPAD_LEFT;
	    } else {
	      // Otherwise scrolling right
	      kEvent = KeyEvent.KEYCODE_DPAD_RIGHT;
	    }
	    onKeyDown(kEvent, null);

	    return true;
	  }


}
