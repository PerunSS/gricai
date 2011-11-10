package com.cerSprikRu.BeerOBan.view.graphicobjects;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

import com.cerSprikRu.BeerOBan.R;

public class DestinationTileGraphicObject extends TileGraphicObject{
	public DestinationTileGraphicObject(Resources resources){
		super(BitmapFactory.decodeResource(resources, R.drawable.destination_tile));
	}
	
	public DestinationTileGraphicObject(Resources resources, Coordinates coordinates){
		super(BitmapFactory.decodeResource(resources, R.drawable.destination_tile), coordinates);
	}
}

