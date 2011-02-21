package com.cerSprikRu.BeerOBan;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.cerSprikRu.BeerOBan.model.board.Board;
import com.cerSprikRu.BeerOBan.view.Constants;
import com.cerSprikRu.BeerOBan.view.GameView;

public class BeerOBan extends Activity {

	private static final Map<String, Integer> levels = new HashMap<String, Integer>();

	static {
		levels.put("lvl1", R.raw.lvl1);
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		Board.getInstance().setResources(this.getResources());
		Constants.getInstance().setResources(this.getResources());
		setContentView(new GameView(this, 1));
	}

	public static int getLvlResource(String lvl) {
		return levels.get(lvl);
	}
}