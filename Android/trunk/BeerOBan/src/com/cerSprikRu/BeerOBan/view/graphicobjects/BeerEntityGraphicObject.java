package com.cerSprikRu.BeerOBan.view.graphicobjects;

import com.cerSprikRu.BeerOBan.R;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

public class BeerEntityGraphicObject extends EntityGraphicObject{
	public BeerEntityGraphicObject(Resources resources){
		super(BitmapFactory.decodeResource(resources, R.drawable.beer));
	}
	public BeerEntityGraphicObject(Resources resources, Coordinates coordinates){
		super(BitmapFactory.decodeResource(resources, R.drawable.beer), coordinates);
	}
}
