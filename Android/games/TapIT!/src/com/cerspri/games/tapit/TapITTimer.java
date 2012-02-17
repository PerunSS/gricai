package com.cerspri.games.tapit;



public class TapITTimer implements Runnable{

	private boolean run = false;
	private long time;
	
	private static final int STEP = 100;
	private TapITPanel panel;
	
	public TapITTimer(long time, TapITPanel panel){
		this.time = time;
		this.panel = panel;
	}
	
	public void setRunning(boolean run){
		this.run = run;
	}
	@Override
	public void run() {
		while(run){
			try {
				Thread.sleep(STEP);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			time -= STEP;
			if(time <= 0){
				run = false;
			}
		}
		panel.endGame();
	}
	
	public synchronized void updateTime(long delay) {
		time += delay;
	}
	
	public synchronized long getTime(){
		return time;
	}

}
