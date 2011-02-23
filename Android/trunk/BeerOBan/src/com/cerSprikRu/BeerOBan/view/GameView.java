package com.cerSprikRu.BeerOBan.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
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
	private Rect northRect, southRect, eastRect, westRect;

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
		initRects();
	}

	@Override
	public void onDraw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		Paint p = new Paint();
		p.setColor(Color.WHITE);
		canvas.drawRect(eastRect, p);
		canvas.drawRect(westRect, p);
		canvas.drawRect(southRect, p);
		canvas.drawRect(northRect, p);
		int rowIndex = 0;
		//izcrtava samo objekte koji se mogu pokrenuti i pivo
		for (Tile[] row : Board.getInstance().getTiles()) {
			int columnIndex = 0;
			int y = Constants.getInstance().getStartY() + rowIndex
					* Constants.getInstance().getTileSize();
			for (Tile cell : row) {
				int x = Constants.getInstance().getStartX() + columnIndex
						* Constants.getInstance().getTileSize();
				if (cell.isDestination()) {
					canvas.drawBitmap(destinationGraphics.getBitmap(), x, y,
							null);
				}
				columnIndex++;
			}
			rowIndex++;
		}
		rowIndex = 0;
		//izcrtava samo objekte koji se mogu pokrenuti i pivo
		for (Tile[] row : Board.getInstance().getTiles()) {
			int columnIndex = 0;
			int y = Constants.getInstance().getStartY() + rowIndex
					* Constants.getInstance().getTileSize();
			for (Tile cell : row) {
				int x = Constants.getInstance().getStartX() + columnIndex
						* Constants.getInstance().getTileSize();
//				if (cell.isDestination()) {
//					canvas.drawBitmap(destinationGraphics.getGraphic(), x, y,
//							null);
//				}
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
								if (tmpY <= y)
									cell.getObject().setAnimated(false);
								break;
							case SOUTH:
								tmpY = tmpY
										- (int) (Constants.getInstance()
												.getTileSize() - currentStep
												* Constants.getInstance()
														.getMoveStep());
								if (tmpY >= y)
									cell.getObject().setAnimated(false);
								break;
							case EAST:
								tmpX = tmpX
										- (int) (Constants.getInstance()
												.getTileSize() - currentStep
												* Constants.getInstance()
														.getMoveStep());
								if (tmpX >= x)
									cell.getObject().setAnimated(false);
								break;
							case WEST:
								tmpX = tmpX
										+ (int) (Constants.getInstance()
												.getTileSize() - currentStep
												* Constants.getInstance()
														.getMoveStep());
								if (tmpX <= x)
									cell.getObject().setAnimated(false);
								break;
							}
							canvas.drawBitmap(cell.getGameObject()
									.getGraphics().getBitmap(), tmpX, tmpY,
									null);

						} else {
							cell.getGameObject().setAnimated(false);
							canvas.drawBitmap(cell.getGameObject()
									.getGraphics().getBitmap(), x, y, null);
						}
					} else
						canvas.drawBitmap(cell.getGameObject().getGraphics()
								.getBitmap(), x, y, null);
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
			if (moved)
				return true;
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				boolean tryMove = false;
				if (northRect.contains((int) event.getX(), (int) event.getY())) {
					tryMove = true;
					direction = Direction.NORTH;
				}
				if (southRect.contains((int) event.getX(), (int) event.getY())) {
					tryMove = true;
					direction = Direction.SOUTH;
				}
				if (eastRect.contains((int) event.getX(), (int) event.getY())) {
					tryMove = true;
					direction = Direction.EAST;
				}
				if (westRect.contains((int) event.getX(), (int) event.getY())) {
					tryMove = true;
					direction = Direction.WEST;
				}

				if (tryMove) {
					if (moved = Board.getInstance().move(direction)) {
						System.out.println(event.getX() + "," + event.getY()
								+ ": " + direction);
						if (Board.getInstance().checkLevel()) {
							System.out.println("level finished");
						}
					} else
						System.out.println("invalid move: " + direction);
					Board.getInstance().printTiles();
				}
			}
			return true;
		}
	}

	private void initRects() {
		northRect = new Rect(Constants.getInstance().getWidth() / 3, 0,
				Constants.getInstance().getWidth() / 3 * 2, Constants
						.getInstance().getHeight() / 6);
		southRect = new Rect(Constants.getInstance().getWidth() / 3, Constants
				.getInstance().getHeight() / 6 * 5, Constants.getInstance()
				.getWidth() / 3 * 2, Constants.getInstance().getHeight());
		westRect = new Rect(0, Constants.getInstance().getHeight() / 3,
				Constants.getInstance().getWidth() / 8, Constants.getInstance()
						.getHeight() / 3 * 2);
		eastRect = new Rect(Constants.getInstance().getWidth() / 8 * 7,
				Constants.getInstance().getHeight() / 3, Constants
						.getInstance().getWidth(), Constants.getInstance()
						.getHeight() / 3 * 2);
	}

}
