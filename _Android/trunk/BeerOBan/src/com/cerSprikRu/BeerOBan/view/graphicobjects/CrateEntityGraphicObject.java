package com.cerSprikRu.BeerOBan.view.graphicobjects;

import com.cerSprikRu.BeerOBan.R;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

public class CrateEntityGraphicObject extends EntityGraphicObject{
	public CrateEntityGraphicObject(Resources resources){
		super(BitmapFactory.decodeResource(resources, R.drawable.beer_crate));
	}
	public CrateEntityGraphicObject(Resources resources, Coordinates coordinates){
		super(BitmapFactory.decodeResource(resources, R.drawable.beer_crate), coordinates);
	}
}