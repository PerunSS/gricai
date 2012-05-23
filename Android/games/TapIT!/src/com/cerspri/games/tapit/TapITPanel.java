package com.cerspri.games.tapit;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import com.cerspri.games.tapit.model.Coordinates;
import com.cerspri.games.tapit.model.TapITGame;
import com.cerspri.games.tapit.model.TapITObject;

public class TapITPanel extends SurfaceView implements SurfaceHolder.Callback,
		MediaPlayer.OnPreparedListener {

	private static final long START_GAME_TIME = 20000;

	private TapITThread thread;
	private TapITCreatorThread creator;
	private Thread runningThread, generatorThread, timerThread;
	private int width;
	private int height;
	private TapITTimer timer;
	private long maxTime = 0;
	private SoundPool soundPool;
	private int clickPos, clickNeg;
	private MediaPlayer gameMusic;
	private ProgressDialog progressDialog;
	private Paint fontPaint;
	private Bitmap scorebox;

	private float soundVolume = 1;

	public TapITPanel(Context context) {
		super(context);
		soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 100);
		clickPos = soundPool.load(context, R.raw.click, 0);
		clickNeg = soundPool.load(context, R.raw.click_n, 0);
		gameMusic = MediaPlayer.create(context, R.raw.game_play);
		gameMusic.setLooping(true);
		gameMusic.start();
		gameMusic.setOnPreparedListener(this);
		progressDialog = ProgressDialog.show(context, "", "Loading...");
		scorebox = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.score_box);
	}

	public void pause() {
		thread.suspend();
		timer.suspend();
		creator.suspend();
		gameMusic.pause();
	}

	public void continiue() {
		if (thread != null) {
			thread.resume();
			timer.resume();
			creator.resume();
			gameMusic.start();
		}
	}

	public void setMusicVolume(float volume) {
		if (gameMusic != null) {
			gameMusic.setVolume(volume, volume);
		}
	}

	public void setSoundVolume(float volume) {
		if (soundPool != null) {
			soundVolume = volume;
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		removeObjects();

		canvas.drawColor(Color.BLACK);
		canvas.drawBitmap(scorebox, 3, 3, null);
		fontPaint.setTextAlign(Paint.Align.LEFT);
		canvas.drawText("TIME:", 20, 25, fontPaint);
		fontPaint.setTextAlign(Paint.Align.RIGHT);
		canvas.drawText("" + ((double) timer.getTime()) / 1000, 87, 25,
				fontPaint);
		fontPaint.setTextAlign(Paint.Align.LEFT);
		canvas.drawBitmap(scorebox, 113, 3, null);
		canvas.drawText("SCORE:", 130, 25, fontPaint);
		fontPaint.setTextAlign(Paint.Align.RIGHT);
		canvas.drawText("" + TapITGame.getInstance().getScore(), 200, 25,
				fontPaint);
		fontPaint.setTextAlign(Paint.Align.LEFT);
		canvas.drawBitmap(scorebox, 223, 3, null);
		canvas.drawText("LEVEL:", 240, 25, fontPaint);
		fontPaint.setTextAlign(Paint.Align.RIGHT);
		canvas.drawText("" + TapITGame.getInstance().getCurrentLevel(), 310,
				25, fontPaint);
		fontPaint.setTextAlign(Paint.Align.LEFT);
		Bitmap bitmap;
		Coordinates coords;
		for (TapITObject graphic : TapITGame.getInstance().getGraphics()) {
			bitmap = graphic.getBitmap();
			coords = graphic.getCoordinates();
			canvas.drawBitmap(bitmap, coords.getX(), coords.getY(), null);
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
					if (object.getTimeValue() > 0)
						soundPool.play(clickPos, soundVolume, soundVolume, 0,
								0, 1);
					else {
						soundPool.play(clickNeg, soundVolume, soundVolume, 0,
								0, 1);
					}
					timer.updateTime(object.getTimeValue() / 5 * 2);
					long time = timer.getTime();
					if (time > 0) {
						TapITGame.getInstance().updateScore(
								object.getTimeValue() / 1000);
						if (time > START_GAME_TIME && time > maxTime) {
							maxTime = time;
						}
						TapITGame.getInstance().lvlUp();
					}
					removeObjects();
					return true;
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

	public void endGame(boolean finishActivity, boolean wasSuspended) {
		gameMusic.release();
		if (wasSuspended) {
			thread.resume();
			timer.resume();
			creator.resume();
			timer.backToMenu();
		}
		setFocusable(false);
		boolean retry = true;
		thread.setRunning(false);
		creator.setRunning(false);
		if (wasSuspended) {
			timer.setRunning(false);
		}
		while (retry) {
			try {
				runningThread.join();
				generatorThread.join();
				if (wasSuspended) {
					timerThread.join();
				}
				retry = false;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (finishActivity) {
			Activity activity = ((Activity) getContext());
			Intent intent = new Intent();
			intent.putExtra("score", TapITGame.getInstance().getScore());
			intent.putExtra("max", maxTime / 1000.0);
			activity.setResult(TapITActivity.CLICKIT_PLAY_CODE, intent);
			activity.finish();
		}

	}
	
	public void saveGameState(){
		
	}
	
	public static TapITPanel buildState(Context context){
		TapITPanel panel = new TapITPanel(context);
		
		return panel;
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		width = w;
		height = h;
	}
	
	/**
	 * @deprecated should use TapITGame.getInstance().getScore()
	 */
	public long getScore() {
		return TapITGame.getInstance().getScore();
	}

	public long getMaxTime() {
		return maxTime;
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

	/****************************************************/
	/********* continue app when media prepared *********/
	/****************************************************/
	@Override
	public void onPrepared(MediaPlayer mp) {
		getHolder().addCallback(this);
		thread = new TapITThread(getHolder(), this);
		creator = new TapITCreatorThread(this);
		timer = new TapITTimer(START_GAME_TIME, this);
		setFocusable(true);
		WindowManager wm = (WindowManager) getContext().getSystemService(
				Context.WINDOW_SERVICE);
		DisplayMetrics metrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(metrics);
		width = metrics.widthPixels;
		height = metrics.heightPixels;
		generateRandomObject();
		fontPaint = new Paint();
		fontPaint.setTypeface(Typeface.DEFAULT_BOLD);
		fontPaint.setColor(Color.WHITE);
		progressDialog.dismiss();
	}

}
