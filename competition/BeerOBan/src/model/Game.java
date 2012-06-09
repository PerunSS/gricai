package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Game {

	private static Game instance = new Game();
	private Table table;

	private int playerRow, playerCol;
	
	int lvlScore;
	int totalScore;
	int highscore = Integer.MAX_VALUE;

	private boolean win;
	
	private boolean endGame;

	public enum Direction {
		UP, DOWN, LEFT, RIGHT
	}

	public void setPlayer(int x, int y) {
		playerRow = x;
		playerCol = y;
	}

	private Game() {
		File file = new File("highscores");
		if(!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		Scanner sc;
		try {
			sc = new Scanner(file);
			String line = sc.nextLine();
			if(line!=null && line.length()>0){
				highscore = Integer.parseInt(line);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	public static Game getInstance() {
		return instance;
	}

	public int getRows() {
		return table.getRows();
	}

	public int getCols() {
		return table.getCols();
	}

	public void loadLevel(String filename) {
		table = new Table(filename);
		totalScore += lvlScore;
		lvlScore = 0;
		win = false;
	}

	public boolean move(Direction direction) {
		int newValueX = 0, newValueY = 0;
		int behindNewValueX = 0, behindNewValueY = 0;
		if (!win) {
			switch (direction) {
			case UP:
				newValueX = playerCol;
				newValueY = playerRow - 1;
				behindNewValueX = playerCol;
				behindNewValueY = newValueY - 1;
				break;
			// return table.move(playerRow, playerCol, playerRow - 1,
			// playerCol);
			case DOWN:
				newValueX = playerCol;
				newValueY = playerRow + 1;
				behindNewValueX = playerCol;
				behindNewValueY = newValueY + 1;
				break;
			// return table.move(playerRow, playerCol, playerRow + 1,
			// playerCol);
			case LEFT:
				newValueX = playerCol - 1;
				newValueY = playerRow;
				behindNewValueX = newValueX - 1;
				behindNewValueY = playerRow;
				break;
			// return table.move(playerRow, playerCol, playerRow, playerCol -
			// 1);
			case RIGHT:
				newValueX = playerCol + 1;
				newValueY = playerRow;
				behindNewValueX = newValueX + 1;
				behindNewValueY = playerRow;
				break;
			// return table.move(playerRow, playerCol, playerRow, playerCol +
			// 1);
			}
			Cell cell = table.getCell(newValueY, newValueX);
			if (cell != null) {
				if (cell.isWall())
					return false;
				if (cell.getCrate() != null) {
					Cell behindCell = table.getCell(behindNewValueY,
							behindNewValueX);
					if (behindCell != null) {
						if (behindCell.isWall())
							return false;
						if (behindCell.getCrate() != null)
							return false;
						table.getCell(playerRow, playerCol).setPlayer(null);
						playerCol = newValueX;
						playerRow = newValueY;
						cell.setPlayer(new Player());
						behindCell.setCrate(cell.getCrate());
						cell.setCrate(null);
						lvlScore++;
						return true;
					}
				} else {
					table.getCell(playerRow, playerCol).setPlayer(null);
					playerCol = newValueX;
					playerRow = newValueY;
					cell.setPlayer(new Player());
					lvlScore++;
					return true;
				}
			}
		}
		return false;
	}

	public boolean checkWin() {
		for (int i = 0; i < table.getRows(); i++)
			for (int j = 0; j < table.getCols(); j++) {
				Cell cell = table.getCell(i, j);
				if (cell.isDestination() && cell.getCrate() == null)
					return false;
			}
		win = true;
		return true;
	}

	public Table getTable() {
		return table;
	}

	public boolean isWin() {
		return win;
	}

	public boolean isEndGame() {
		return endGame;
	}

	public void setEndGame(boolean endGame) {
		this.endGame = endGame;
		if(endGame){
			File file = new File("highscores");
			if(!file.exists())
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			try {
				Scanner sc = new Scanner(file);
				String line = sc.nextLine();
				int highScore = Integer.MAX_VALUE;
				if(line!=null && line.length()>0){
					highScore = Integer.parseInt(line);
				}
				sc.close();
				if(totalScore < highScore){
					PrintWriter writer;
					try {
						writer = new PrintWriter(new FileWriter(file));
						writer.println(totalScore);
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public int getLvlScore() {
		return lvlScore;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public int getHighscore() {
		return highscore;
	}

}
