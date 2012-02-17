package com.cerspri.games.tapit;

import java.util.Date;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class TapITThread implements Runnable {

	private SurfaceHolder holder;
	private TapITPanel panel;
	private boolean run;
	private long startTime = 0;
	private long lastTime = 0;
	private boolean paused = false;
	private Object mutex = new Object();

	public TapITThread(SurfaceHolder holder, TapITPanel panel) {
		this.holder = holder;
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
		Canvas c;
		startTime = new Date().getTime();
		while (run) {
			synchronized (mutex) {
				while (paused)
					try {
						mutex.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			}
			c = null;
			try {
				c = holder.lockCanvas(null);
				synchronized (holder) {
					lastTime = new Date().getTime();
					panel.updateObjects(lastTime - startTime);
					panel.onDraw(c);
					startTime = lastTime;
				}
			} finally {
				// do this in a finally so that if an exception is thrown
				// during the above, we don't leave the Surface in an
				// inconsistent state
				if (c != null) {
					holder.unlockCanvasAndPost(c);
				}
			}
		}
	}

}
