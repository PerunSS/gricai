package com.cerSprikRu.BeerOBan.model.objects;

import com.cerSprikRu.BeerOBan.model.board.Tile;

public abstract class GameObject {

	private Tile position;

	public Tile getPosition() {
		return position;
	}

	public void setPosition(Tile position) {
		this.position = position;
	}
	
}
