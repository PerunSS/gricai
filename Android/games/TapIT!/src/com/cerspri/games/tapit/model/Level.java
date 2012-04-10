package com.cerspri.games.tapit.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.cerspri.games.tapit.R;

public class Level {
	
	private double negativeOdd;
	private double negativeValueModificator, positiveValueModificator;
	
	public Level(){
		
	}

	public TapITObject generateRandomObject(int width, int height, Resources res) {
		double random = Math.random();
		double value = 1;
		double valueModificator = 1;
		if(random < negativeOdd){
			valueModificator = negativeValueModificator;
		}else{
			valueModificator = positiveValueModificator;
		}
		
		random = Math.random();
		if(random < valueModificator * valueModificator){
			//TODO biggest number
		}else if (random < valueModificator){
			//TODO middle number
		}else {
			//TODO smallest number
		}
		
		
		long lifeTime = 0;
		

		Bitmap icon = null;
		
		int imageWidth = icon.getWidth();
		int imageHeight = icon.getHeight();
		int x = imageWidth / 2 + (int) (Math.random() * (width - imageWidth));
		int y = imageHeight / 2 + 50
				+ (int) (Math.random() * (height - imageHeight - 50));
		TapITObject object = new TapITObject(icon, lifeTime, (long)(1000 * value));
		object.getCoordinates().setX(x);
		object.getCoordinates().setY(y);
		return object;
	}
}
