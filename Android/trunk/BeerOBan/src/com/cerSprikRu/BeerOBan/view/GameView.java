package com.cerSprikRu.BeerOBan.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.cerSprikRu.BeerOBan.DrawThread;
import com.cerSprikRu.BeerOBan.model.board.Board;
import com.cerSprikRu.BeerOBan.model.board.Tile;
import com.cerSprikRu.BeerOBan.view.graphicobjects.DestinationTileGraphicObject;
import com.cerSprikRu.BeerOBan.view.graphicobjects.GraphicObject;
import com.cerSprikRu.BeerOBan.view.graphicobjects.PlayerEntityGraphicObject;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

	private Thread thread;
	private DrawThread drawThread;
	private List<GraphicObject> graphics = new ArrayList<GraphicObject>();
	private GraphicObject destinationGraphics;
	
	private static final int tileWidth = 60;
	private static final int tileHeight = 60;

	public GameView(Context context) {
		super(context);
		destinationGraphics  = new DestinationTileGraphicObject(context.getResources());
		getHolder().addCallback(this);
		setFocusable(true);
		Board.getInstance().loadLevel(1);
	}

	@Override
	public void onDraw(Canvas canvas) {
		int rowIndex = 0;
		for(Tile[] row : Board.getInstance().getTiles()){
			int columnIndex = 0;
			for(Tile cell: row){
				if(cell.isDestination()){
					canvas.drawBitmap(destinationGraphics.getGraphic(), columnIndex*tileWidth, rowIndex*tileHeight, null);
				}
				if(cell.getGameObject()!=null){
					canvas.drawBitmap(cell.getGameObject().getGraphics().getGraphic(), columnIndex*tileWidth, rowIndex*tileHeight, null);
				}
				columnIndex++;
			}
			rowIndex++;
		}
//		canvas.drawColor(Color.BLACK);
//		for(GraphicObject graphicObject : graphics){
//			canvas.drawBitmap(graphicObject.getGraphic(), graphicObject.getCoordinates().getX(), graphicObject.getCoordinates().getY(),null);
//		}
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
				GraphicObject graphic = new PlayerEntityGraphicObject(getResources());
				graphic.getCoordinates().setX((int)event.getX());
				graphic.getCoordinates().setY((int)event.getY());
			    graphics.add(graphic);
			}
			return true;
		}		
	}
}
