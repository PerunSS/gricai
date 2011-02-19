package com.cerSprikRu.BeerOBan.view.graphicobjects;

import android.graphics.Bitmap;

import com.cerSprikRu.BeerOBan.view.graphicobjects.GraphicObject.Coordinates;

public abstract class EntityGraphicObject extends GraphicObject{
	public EntityGraphicObject(Bitmap bitmap){
		super(bitmap);
	}
	public EntityGraphicObject(Bitmap bitmap, Coordinates coordinates){
		super(bitmap,coordinates);
	}
}
