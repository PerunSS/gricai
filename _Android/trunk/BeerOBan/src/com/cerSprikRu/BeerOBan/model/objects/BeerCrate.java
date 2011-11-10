package com.cerSprikRu.BeerOBan.model.objects;

import com.cerSprikRu.BeerOBan.view.graphicobjects.CrateEntityGraphicObject;

import android.content.res.Resources;

public class BeerCrate extends GameObject {

	private int weight;

	public BeerCrate(int weight, Resources res) {
		super();
		this.weight = weight;
		setGraphics(new CrateEntityGraphicObject(res));
	}

	public int getWeight() {
		return weight;
	}
	
	
}
