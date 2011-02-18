package com.cerSprikRu.BeerOBan;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.cerSprikRu.BeerOBan.graphics.GraphicObject;
import com.cerSprikRu.BeerOBan.graphics.PlayerGraphicObject;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

	private Thread thread;
	private DrawThread drawThread;
	private List<GraphicObject> graphics = new ArrayList<GraphicObject>();

	public GameView(Context context) {
		super(context);
		getHolder().addCallback(this);
		setFocusable(true);

	}

	@Override
	public void onDraw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		for(GraphicObject graphicObject : graphics){
			canvas.drawBitmap(graphicObject.getGraphic(), graphicObject.getCoordinates().getX(), graphicObject.getCoordinates().getY(),null);
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		drawThread = new DrawThread(getHolder(), this);
		drawThread.setRunning(true);
		thread = new Thread(drawThread);
		thread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		drawThread.setRunning(false);
		while (retry) {
			try {
				thread.join();
				retry = false;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		synchronized (drawThread.getSurfaceHolder()) {
			if (event.getAction() == MotionEvent.ACTION_DOWN){
				GraphicObject graphic = new PlayerGraphicObject(getResources());
				graphic.getCoordinates().setX((int)event.getX());
				graphic.getCoordinates().setY((int)event.getY());
			    graphics.add(graphic);
			}
			return true;
		}		
	}
}
