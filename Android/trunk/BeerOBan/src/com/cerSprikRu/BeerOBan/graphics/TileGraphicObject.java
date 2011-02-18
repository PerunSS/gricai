package com.cerSprikRu.BeerOBan.graphics;

import android.graphics.Bitmap;

abstract class TileGraphicObject extends GraphicObject{
	public TileGraphicObject(Bitmap bitmap){
		super(bitmap);
	}
	public TileGraphicObject(Bitmap bitmap, Coordinates coordinates){
		super(bitmap,coordinates);
	}
}
