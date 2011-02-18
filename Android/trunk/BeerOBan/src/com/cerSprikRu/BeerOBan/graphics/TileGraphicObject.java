package com.cerSprikRu.BeerOBan.graphics;

import android.content.res.Resources;
import android.graphics.Bitmap;

abstract class TileGraphicObject extends GraphicObject{
	public TileGraphicObject(Resources resources, Bitmap bitmap){
		super(resources,bitmap);
	}
	public TileGraphicObject(Resources resources, Bitmap bitmap, Coordinates coordinates){
		super(resources,bitmap,coordinates);
	}
}
