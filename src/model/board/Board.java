package model.board;

import java.util.HashMap;
import java.util.Map;

import model.monster.Monster;

public class Board {

	private Map<Integer, Field> fields = new HashMap<Integer, Field>();
	private static Board instance = new Board();

	public Map<Integer, Field> getFields() {
		return fields;
	}

	public static Board getInstance() {
		return instance;
	}
	
	public void endCurrentTurn(){
		
	}
	
	public void removeMonster(Monster monster){
		
	}
	
}
