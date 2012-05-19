package com.cerspri.games.tod;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.cerspri.games.tod.model.Constants;
import com.cerspri.games.tod.model.Game;

public class SelectDirtinessScreen extends Activity{
	
	private RadioGroup dirtinessGroup;
	private RadioGroup levelSelection;
	private Context myContext = this;
	private RadioButton funny;
	private RadioButton tempting;
	private RadioButton sexy;
	private RadioButton dirty;
	private List<RadioButton> lvls;
	
	private Game game = Game.getInstance();
	
	public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.dirtiness_selection);
	        
	        Button dirtinessSelectedButton = (Button) findViewById(R.id.selectDirtinessButton);
	        dirtinessGroup = (RadioGroup) findViewById(R.id.dirtinessSelection);
	        levelSelection = (RadioGroup) findViewById(R.id.levelSelection);
	        funny = (RadioButton) findViewById(R.id.funny);
	        tempting = (RadioButton) findViewById(R.id.tempting);
	        sexy = (RadioButton) findViewById(R.id.sexy);
	        dirty = (RadioButton) findViewById(R.id.dirty);
	        lvls = new ArrayList<RadioButton>();
	        lvls.add((RadioButton) findViewById(R.id.lvl1));
	        lvls.add((RadioButton) findViewById(R.id.lvl2));
	        lvls.add((RadioButton) findViewById(R.id.lvl3));
	        lvls.add((RadioButton) findViewById(R.id.lvl4));
	        lvls.add((RadioButton) findViewById(R.id.lvl5));
	        
	        this.initializeDirtiness();
	        this.initializeDirtinessLevel(game.getDirtiness(), game.getDirtinessLevel());
	        
	        for(int i=0; i<5; i++){
	        	lvls.get(i).setOnCheckedChangeListener(new OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						if(isChecked){
							int dirtiness = 0;
							RadioButton dirtinessButton = (RadioButton)findViewById(dirtinessGroup.getCheckedRadioButtonId());
							if (dirtinessButton!=null){
								dirtiness =  new Integer(dirtinessButton.getTag().toString());
							}
							int level = new Integer(buttonView.getTag().toString());
					        initializeDirtinessLevel(dirtiness,level);
						}
					}
				});
	        }
	        
	        funny.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					if(isChecked){
						int level = 0;
						RadioButton levelButton = (RadioButton)findViewById(levelSelection.getCheckedRadioButtonId());
						if (levelButton!=null){
							level =  new Integer(levelButton.getTag().toString());
						}
				        initializeDirtinessLevel(1,level);
					}
				}
			});
	        
	        tempting.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					if(isChecked){
						int level = 0;
						RadioButton levelButton = (RadioButton)findViewById(levelSelection.getCheckedRadioButtonId());
						if (levelButton!=null){
							level =  new Integer(levelButton.getTag().toString());
						}
				        initializeDirtinessLevel(2,level);
					}
				}
			});
	        sexy.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					if(isChecked){
						int level = 0;
						RadioButton levelButton = (RadioButton)findViewById(levelSelection.getCheckedRadioButtonId());
						if (levelButton!=null){
							level =  new Integer(levelButton.getTag().toString());
						}
				        initializeDirtinessLevel(3,level);
					}
				}
			});
	        dirty.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					if(isChecked){
						int level = 0;
						RadioButton levelButton = (RadioButton)findViewById(levelSelection.getCheckedRadioButtonId());
						if (levelButton!=null){
							level =  new Integer(levelButton.getTag().toString());
						}
				        initializeDirtinessLevel(4,level);
					}
				}
			});
//	        funny.setOnClickListener(new View.OnClickListener() {
//				
//				@Override
//				public void onClick(View v) {
//					// TODO Auto-generated method stub
//					
//				}
//			});
	        
	        
	        dirtinessSelectedButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					int dirtiness = 0;
					RadioButton dirtinessButton = (RadioButton)findViewById(dirtinessGroup.getCheckedRadioButtonId());
					if (dirtinessButton!=null){
						dirtiness =  new Integer(dirtinessButton.getTag().toString());
					}
					
					int level = 0;
					RadioButton levelButton = (RadioButton)findViewById(levelSelection.getCheckedRadioButtonId());
					if (levelButton!=null){
						level =  new Integer(levelButton.getTag().toString());
					}
					
					if(dirtiness>0 && level>0){
						Game.getInstance().setDirtiness(dirtiness);
						Game.getInstance().setDirtinessLevel(level);
						setResult(Constants.SELECT_DIRTINESS_ACTIVITY);
						finish();
					}else {
						AlertDialog.Builder builder = new AlertDialog.Builder(myContext);
						builder.setMessage("You must select dirtiness and level")
								.setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
									
									@Override
									public void onClick(DialogInterface dialog, int which) {
										
									}
								});
						AlertDialog alert = builder.create();
						alert.show();
					}
				}
			});
	}
	private void initializeDirtiness(){
		switch(game.getDirtiness()){
    	case 1 :
    		funny.setChecked(true);
    		break;
    	case 2 :
    		tempting.setChecked(true);
    		break;
    	case 3 :
    		sexy.setChecked(true);
    		break;
    	case 4 :
    		dirty.setChecked(true);
    		break;
		}
	}
	private void initializeDirtinessLevel(int dirtiness, int dirtinessLevel){
		for(int i =0 ; i < 5; i++){
			if(i<dirtinessLevel){
				switch(dirtiness){
		    	case 1 :
		    		lvls.get(i).setButtonDrawable(R.drawable.funny_checked);
		    		break;
		    	case 2 :
		    		lvls.get(i).setButtonDrawable(R.drawable.tempting_checked);
		    		break;
		    	case 3 :
		    		lvls.get(i).setButtonDrawable(R.drawable.sexy_checked);
		    		break;
		    	case 4 :
		    		lvls.get(i).setButtonDrawable(R.drawable.dirty_checked);
		    		break;
				}
			} else {
				switch(dirtiness){
		    	case 1 :
		    		lvls.get(i).setButtonDrawable(R.drawable.funny_not_checked);
		    		break;
		    	case 2 :
		    		lvls.get(i).setButtonDrawable(R.drawable.tempting_not_checked);
		    		break;
		    	case 3 :
		    		lvls.get(i).setButtonDrawable(R.drawable.sexy_not_checked);
		    		break;
		    	case 4 :
		    		lvls.get(i).setButtonDrawable(R.drawable.dirty_not_checked);
		    		break;
				}
			}
			lvls.get(dirtinessLevel-1).setChecked(true);
		}
	}
//	private void saveDirtinessState(){
//		int dirtiness = 0;
//		RadioButton dirtinessButton = (RadioButton)findViewById(dirtinessGroup.getCheckedRadioButtonId());
//		if (dirtinessButton!=null){
//			dirtiness =  new Integer(dirtinessButton.getTag().toString());
//		}
//		
//		int level = 0;
//		RadioButton levelButton = (RadioButton)findViewById(levelSelection.getCheckedRadioButtonId());
//		if (levelButton!=null){
//			level =  new Integer(levelButton.getTag().toString());
//		}
//		Game.getInstance().setDirtiness(dirtiness);
//		Game.getInstance().setDirtinessLevel(level);
//	}
}
