package com.cerSprikRu.BeerOBan;

import com.cerSprikRu.BeerOBan.view.GameView;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class DrawThread implements Runnable {
	private SurfaceHolder surfaceHolder;
    private GameView view;
    private boolean run = false;

    public DrawThread(SurfaceHolder surfaceHolder, GameView view) {
        this.surfaceHolder = surfaceHolder;
        this.view = view;
    }

    public void setRunning(boolean run) {
        this.run = run;
    }

    @Override
    public void run() {
        Canvas c;
        while (run) {
            c = null;
            try {
                c = surfaceHolder.lockCanvas(null);
                synchronized (surfaceHolder) {
                    view.onDraw(c);
                }
            } finally {
                // do this in a finally so that if an exception is thrown
                // during the above, we don't leave the Surface in an
                // inconsistent state
                if (c != null) {
                    surfaceHolder.unlockCanvasAndPost(c);
                }
            }
        }
    }

	public SurfaceHolder getSurfaceHolder() {
		return surfaceHolder;
	}

}
