package com.cerspri.games.tod.model;

public class ToDQuery {
	private String query;
	private int mask;
	private int dirtiness;
	
	public ToDQuery(String query, int mask, int dirtiness){
		this.query = query;
		this.mask = mask;
		this.dirtiness = dirtiness;
	}
	
	public String getQuery(){
		return this.query;
	}
	
	public int getMask(){
		return this.mask;
	}
	
	public int getDirtiness(){
		return this.dirtiness;
	}
	
	public boolean isTargetable(int mask, int dirtiness){
		if (mask == this.mask && dirtiness == this.dirtiness){
			return true;
		}
		return false;
	}
}
