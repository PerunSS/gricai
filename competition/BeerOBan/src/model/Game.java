package model;

public class Game {

	private static Game instance = new Game ();
	private Table table;
	
	private int playerRow, playerCol;
	
	public enum Direction {
		UP, DOWN, LEFT, RIGHT
	}
	
	private Game (){}
	
	public static Game getInstance(){
		return instance;
	}
	
	public void loadLevel(String filename){
		table = new Table(filename);
	}
	
	public boolean move(Direction direction){
		switch(direction){
		case UP:
			return table.move(playerRow, playerCol, playerRow - 1, playerCol);
		case DOWN:
			return table.move(playerRow, playerCol, playerRow + 1, playerCol);
		case LEFT:
			return table.move(playerRow, playerCol, playerRow, playerCol - 1);
		case RIGHT:
			return table.move(playerRow, playerCol, playerRow, playerCol + 1);
		}
		return false;
	}
	
	public static void main(String[] args) {
	}
	
}
