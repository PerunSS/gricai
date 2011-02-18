package com.cerSprikRu.BeerOBan;

import java.util.HashMap;
import java.util.Map;

import com.cerSprikRu.BeerOBan.model.board.Board;

import android.app.Activity;
import android.os.Bundle;

public class BeerOBan extends Activity {
	
	private static final Map<String, Integer> levels = new HashMap<String, Integer>();
	
	static {
		levels.put("lvl1", R.raw.lvl1);
	}
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Board.getInstance().setContext(this);
        setContentView(new GameView(this));
    }
    
    public static int getLvlResource(String lvl){
    	return levels.get(lvl);
    }
}