package com.gricai.game.model.magic;

public class MagicFactory {

	private static MagicFactory instance = new MagicFactory();
	
	private MagicFactory(){
		
	}

	public static MagicFactory getInstance() {
		return instance;
	}
}
