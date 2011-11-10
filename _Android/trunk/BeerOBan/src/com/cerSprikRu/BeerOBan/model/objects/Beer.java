package com.cerSprikRu.BeerOBan.model.objects;

import com.cerSprikRu.BeerOBan.view.graphicobjects.BeerEntityGraphicObject;

import android.content.res.Resources;

public class Beer extends GameObject {

	private int amount;
	
	public Beer(int amount, Resources res){
		this.amount = amount;
		setGraphics(new BeerEntityGraphicObject(res));
	}

	public int getAmount() {
		return amount;
	}
}
