package com.cerSprikRu.BeerOBan.view.graphicobjects;

import com.cerSprikRu.BeerOBan.R;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

public class PlayerEntityGraphicObject extends EntityGraphicObject{
	public PlayerEntityGraphicObject(Resources resources){
		super(BitmapFactory.decodeResource(resources, R.drawable.player));
	}
	public PlayerEntityGraphicObject(Resources resources, Coordinates coordinates){
		super(BitmapFactory.decodeResource(resources, R.drawable.player), coordinates);
	}
}
