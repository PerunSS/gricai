package com.cerspri.games.tapit;

import com.cerspri.games.tapit.model.TapITGame;

public class TapITCreatorThread implements Runnable {

	private boolean run;
	private TapITPanel panel;
	private boolean paused = false;
	private Object mutex = new Object();
	
	public TapITCreatorThread(TapITPanel panel){
		this.panel = panel;
	}
	
	public void setRunning(boolean run){
		this.run = run;
	}
	
	public void suspend(){
		paused = true;
	}
	
	public void resume(){
		paused = false;
		synchronized (mutex) {
			mutex.notifyAll();
		}
	}
	
	@Override
	public void run() {
		while(run){
			synchronized (mutex) {
				while (paused)
					try {
						mutex.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			}
			try {
				Thread.sleep(TapITGame.getInstance().getSpawnTime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			panel.generateRandomObject();
		}
	}

}
