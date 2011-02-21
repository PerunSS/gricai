package com.cerSprikRu.BeerOBan.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.cerSprikRu.BeerOBan.DrawThread;
import com.cerSprikRu.BeerOBan.model.board.Board;
import com.cerSprikRu.BeerOBan.model.board.Tile;
import com.cerSprikRu.BeerOBan.model.enums.Direction;
import com.cerSprikRu.BeerOBan.view.graphicobjects.DestinationTileGraphicObject;
import com.cerSprikRu.BeerOBan.view.graphicobjects.GraphicObject;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

	private Thread thread;
	private DrawThread drawThread;
	private GraphicObject destinationGraphics;
	private Direction direction;
	private boolean moved;
	private int currentStep = 0;

	public GameView(Context context, int level) {
		super(context);
		destinationGraphics = new DestinationTileGraphicObject(
				context.getResources());
		getHolder().addCallback(this);
		setFocusable(true);
		Board.getInstance().loadLevel(level);
		Constants.getInstance()
				.calculetaStartingPosition(Board.getInstance().getRows(),
						Board.getInstance().getColumns());
	}

	@Override
	public void onDraw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		int rowIndex = 0;
		for (Tile[] row : Board.getInstance().getTiles()) {
			int columnIndex = 0;
			int y = Constants.getInstance().getStartY() + rowIndex
					* Constants.getInstance().getTileSize();
			for (Tile cell : row) {
				int x = Constants.getInstance().getStartX() + columnIndex
						* Constants.getInstance().getTileSize();
				if (cell.isDestination()) {
					canvas.drawBitmap(destinationGraphics.getGraphic(), x, y,
							null);
				}
				if (cell.getGameObject() != null) {
					if (cell.getGameObject().isAnimated()) {
						if (moved) {
							int tmpX = x;
							int tmpY = y;
							switch (direction) {
							case NORTH:
								tmpY = tmpY
										+ (int) (Constants.getInstance()
												.getTileSize() - currentStep
												* Constants.getInstance()
														.getMoveStep());
								if(tmpY <=y)
									cell.getObject().setAnimated(false);
								break;
							case SOUTH:
								tmpY = tmpY
										- (int) (Constants.getInstance()
												.getTileSize() - currentStep
												* Constants.getInstance()
														.getMoveStep());
								if(tmpY >=y)
									cell.getObject().setAnimated(false);
								break;
							case EAST:
								tmpX = tmpX
										- (int) (Constants.getInstance()
												.getTileSize() - currentStep
												* Constants.getInstance()
														.getMoveStep());
								if(tmpX >=x)
									cell.getObject().setAnimated(false);
								break;
							case WEST:
								tmpX = tmpX
										+ (int) (Constants.getInstance()
												.getTileSize() - currentStep
												* Constants.getInstance()
														.getMoveStep());
								if(tmpX <=x)
									cell.getObject().setAnimated(false);
								break;
							}
							canvas.drawBitmap(cell.getGameObject()
									.getGraphics().getGraphic(), tmpX, tmpY, null);
							
						} else {
							cell.getGameObject().setAnimated(false);
							canvas.drawBitmap(cell.getGameObject()
									.getGraphics().getGraphic(), x, y, null);
						}
					} else
						canvas.drawBitmap(cell.getGameObject().getGraphics()
								.getGraphic(), x, y, null);
				}
				columnIndex++;
			}
			rowIndex++;
		}
		if (moved) {
			currentStep++;
			if (currentStep == Constants.ANIMATION_STEPS) {
				currentStep = 0;
				moved = false;
			}
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
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				if (event.getY() >= Constants.getInstance().getRatio()
						* event.getX()) {
					if (event.getY() >= Constants.getInstance().getHeight()
							- Constants.getInstance().getRatio() * event.getX()) {
						direction = Direction.SOUTH;
					} else
						direction = Direction.WEST;
				} else if (event.getY() >= Constants.getInstance().getHeight()
						- Constants.getInstance().getRatio() * event.getX()) {
					direction = Direction.EAST;
				} else
					direction = Direction.NORTH;
				if (moved = Board.getInstance().move(direction))
					System.out.println(event.getX() + "," + event.getY() + ": "
							+ direction);
				else
					System.out.println("invalid move: " + direction);
				Board.getInstance().printTiles();
			}
			return true;
		}
	}

}
