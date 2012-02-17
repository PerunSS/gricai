package com.cerspri.games.tapit;

import com.cerspri.games.tapit.model.TapITGame;

public class TapITCreatorThread implements Runnable {

	private boolean run;
	private TapITPanel panel;
	
	public TapITCreatorThread(TapITPanel panel){
		this.panel = panel;
	}
	
	public void setRunning(boolean run){
		this.run = run;
	}
	
	@Override
	public void run() {
		while(run){
			try {
				Thread.sleep(TapITGame.getInstance().getSpawnTime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			panel.generateRandomObject();
		}
	}

}
