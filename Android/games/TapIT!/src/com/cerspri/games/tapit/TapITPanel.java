package com.cerspri.games.tapit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import com.cerspri.games.tapit.model.Coordinates;
import com.cerspri.games.tapit.model.TapITGame;
import com.cerspri.games.tapit.model.TapITObject;

public class TapITPanel extends SurfaceView implements SurfaceHolder.Callback {

	private static final long START_GAME_TIME = 1000;

	private TapITThread thread;
	private TapITCreatorThread creator;
	private Thread runningThread, generatorThread, timerThread;
	private int width;
	private int height;
	private TapITTimer timer;
	private Paint paint;
	private long score = 0;
	private long totalTime = 0;
	private long lvlUP = 40000;
	private long maxTime = 0;

	public TapITPanel(Context context) {
		super(context);
		getHolder().addCallback(this);
		thread = new TapITThread(getHolder(), this);
		creator = new TapITCreatorThread(this);
		timer = new TapITTimer(START_GAME_TIME, this);
		setFocusable(true);
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics metrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(metrics);
		width = metrics.widthPixels;
		height = metrics.heightPixels;
		generateRandomObject();
		paint = new Paint();
		paint.setColor(Color.WHITE);
	}

	public void pause() {
		thread.suspend();
	}

	public void continiue() {
		if (thread != null) {
			thread.resume();
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		removeObjects();
		canvas.drawColor(Color.BLACK);
		canvas.drawText("time: " + ((double) timer.getTime()) / 1000, 7, 22,
				paint);
		canvas.drawText("score: " + score, 150, 22, paint);
		Bitmap bitmap;
		Coordinates coords;
		for (TapITObject graphic : TapITGame.getInstance().getGraphics()) {
			bitmap = graphic.getBitmap();
			coords = graphic.getCoordinates();
			canvas.drawBitmap(bitmap, coords.getXForDraw(),
					coords.getYForDraw(), null);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();
		synchronized (getHolder()) {
			for (TapITObject object : TapITGame.getInstance().getGraphics()) {
				if (object.getCoordinates().contains(x, y)) {
					object.click();
					timer.updateTime(object.getTimeValue());
					long time = timer.getTime();
					if (time > 0) {
						score += object.getTimeValue() / 1000;

						if (object.getTimeValue() > 0) {
							totalTime += object.getTimeValue();
						}
						if (time > START_GAME_TIME && time > maxTime) {
							maxTime = time;
						}
						if (totalTime * 0.7 + score * 300 > lvlUP) {
							TapITGame.getInstance().lvlUp();
							lvlUP += 40000;
						}
					}
				}
			}
		}
		return true;
	}

	public void generateRandomObject() {
		synchronized (getHolder()) {
			TapITGame.getInstance().generateRandomObject(width, height,
					getResources());
		}
	}

	public synchronized void updateObjects(long time) {
		TapITGame.getInstance().updateObjects(time);

	}

	private synchronized void removeObjects() {
		TapITGame.getInstance().removeObjects();
	}

	public void endGame() {
		setFocusable(false);
		boolean retry = true;
		thread.setRunning(false);
		creator.setRunning(false);
		while (retry) {
			try {
				runningThread.join();
				generatorThread.join();
				retry = false;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Activity activity = ((Activity) getContext());
		Intent intent = new Intent();
		intent.putExtra("score", score);
		intent.putExtra("max", maxTime / 1000.0);
		activity.setResult(TapITActivity.CLICKIT_PLAY_CODE, intent);
		activity.finish();

	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		width = w;
		height = h;
	}

	/********************************************************/
	/***************** SurfaceHolder.Callback *****************/
	/********************************************************/
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		thread.setRunning(true);
		// if (runningThread != null && !runningThread.isAlive())
		runningThread = new Thread(thread);
		runningThread.start();
		creator.setRunning(true);
		// if (generatorThread != null && !generatorThread.isAlive())
		generatorThread = new Thread(creator);
		generatorThread.start();
		timer.setRunning(true);
		// if (timerThread != null && !timerThread.isAlive())
		timerThread = new Thread(timer);
		timerThread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		thread.setRunning(false);
		creator.setRunning(false);
		while (retry) {
			try {
				runningThread.join();
				generatorThread.join();
				retry = false;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public long getScore() {
		return score;
	}

}
