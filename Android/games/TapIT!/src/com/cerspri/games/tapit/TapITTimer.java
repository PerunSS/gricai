package com.cerspri.games.tapit;

import com.cerspri.games.tapit.model.TapITGame;

public class TapITTimer implements Runnable {

	private boolean run = false;
	//private long time;

	private static final int STEP = 100;
	private TapITPanel panel;
	private boolean paused = false;
	private Object mutex = new Object();
	private boolean backToMenu = false;
	private boolean surfaceDestroyed = false;

	public TapITTimer( TapITPanel panel) {
		this.panel = panel;
	}

	public void setRunning(boolean run) {
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
	
	public void backToMenu(){
		backToMenu = true;
	}
	
	public void surfaceDestroyed(){
		surfaceDestroyed = true;
	}

	@Override
	public void run() {
		while (run) {
			synchronized (mutex) {
				while (paused)
					try {
						mutex.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			}
			try {
				Thread.sleep(STEP);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			TapITGame.getInstance().updateTime(-STEP);
			//time -= STEP;
			if (TapITGame.getInstance().getTime() <= 0) {
				run = false;
			}
		}
		if(!surfaceDestroyed && !backToMenu)
			panel.endGame(true,false);
	}

}
