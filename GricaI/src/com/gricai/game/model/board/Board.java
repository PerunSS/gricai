package com.gricai.game.model.board;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import old.utils.log.LogChooser;

import com.gricai.game.model.monster.Monster;




public class Board {

	private Map<Integer, Field> fields = new HashMap<Integer, Field>();
	private static Board instance = new Board();
	//private int fieldSides = 4;
	
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
	
	public void load(String filename) throws IOException{
		File file = new File(filename);
		if(!file.exists())
			return;
		BufferedReader reader = null;
		try{
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			LogChooser.getLog("board").logException(e);
			throw e;
		}
		String line = null;
		try {
			if((line=reader.readLine())!=null){
				String []values = line.split(";");
				//fieldSides = Integer.parseInt(values[0]);
				int numberOfFields = Integer.parseInt(values[1]);
				for(int i=0;i<numberOfFields;i++){
					fields.put(i, new Field());
				}
			}
		} catch (IOException e) {
			LogChooser.getLog("board").logException(e);
			throw e;
		}
		try{
			reader.close();
		}catch (IOException e) {
			LogChooser.getLog("board").logException(e);
			throw e;
		}
	}
	
}
