package com.cerSprikRu.BeerOBan;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.cerSprikRu.BeerOBan.model.board.Board;
import com.cerSprikRu.BeerOBan.view.Constants;
import com.cerSprikRu.BeerOBan.view.GameView;

public class BeerOBan extends Activity {

	private static final Map<String, Integer> levels = new HashMap<String, Integer>();
	
	static final int DIALOG_SELECT_LEVEL_ID = 0;
	
	private Button startButton;

	static {
		levels.put("lvl1", R.raw.lvl1);
		levels.put("lvl2", R.raw.lvl2);
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		startApplication();
	}

	private void startApplication(){
		setContentView(R.layout.main_menu);
		startButton = (Button) findViewById(R.id.start);
		startButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startButtonClick(v);
			}
		});
		
	}
	
	private void startButtonClick(View v){
		showDialog(DIALOG_SELECT_LEVEL_ID);
	}
	
	private void startLvl(int lvl){
		Board.getInstance().setResources(this.getResources());
		Constants.getInstance().setResources(this.getResources());
		setContentView(new GameView(this, lvl));
	}
	
	public static int getLvlResource(String lvl) {
		return levels.get(lvl);
	}
	protected Dialog onCreateDialog(int id){
		Dialog dialog;
		switch(id) {
	    case DIALOG_SELECT_LEVEL_ID:
	    	CharSequence[] items = new CharSequence[levels.keySet().size()];
			int i=0;
			for ( String str:levels.keySet()){
				items[i++] = str;
			}

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Pick level");
			builder.setItems(items, new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog, int item) {
			        startLvl(item+1);
			    }
			});
			dialog = builder.create();
	        break;
	    default:
	        dialog = null;
	    }
	    return dialog;
	}
}