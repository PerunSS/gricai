package com.cerSprikRu.BeerOBan.view.graphicobjects;

import android.graphics.Bitmap;

public abstract class TileGraphicObject extends GraphicObject{
	public TileGraphicObject(Bitmap bitmap){
		super(bitmap);
	}
	public TileGraphicObject(Bitmap bitmap, Coordinates coordinates){
		super(bitmap,coordinates);
	}
}
