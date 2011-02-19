package com.cerSprikRu.BeerOBan.view.graphicobjects;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

import com.cerSprikRu.BeerOBan.R;

public class StandardTileGraphicObject extends TileGraphicObject{
	public StandardTileGraphicObject(Resources resources){
		super(BitmapFactory.decodeResource(resources, R.drawable.empty_tile));
	}
	
	public StandardTileGraphicObject(Resources resources, Coordinates coordinates){
		super(BitmapFactory.decodeResource(resources, R.drawable.empty_tile), coordinates);
	}
}
