package com.cerspri.games.tapit;

public class TapITTimer implements Runnable {

	private boolean run = false;
	private long time;

	private static final int STEP = 100;
	private TapITPanel panel;
	private boolean paused = false;
	private Object mutex = new Object();

	public TapITTimer(long time, TapITPanel panel) {
		this.time = time;
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
			time -= STEP;
			if (time <= 0) {
				run = false;
			}
		}
		panel.endGame();
	}

	public synchronized void updateTime(long delay) {
		time += delay;
	}

	public synchronized long getTime() {
		return time;
	}

}
