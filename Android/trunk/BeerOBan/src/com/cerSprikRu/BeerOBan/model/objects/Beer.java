package com.cerSprikRu.BeerOBan.model.objects;

public class Beer extends GameObject {

	private int amount;
	
	public Beer(int amount){
		this.amount = amount;
	}

	public int getAmount() {
		return amount;
	}
}
