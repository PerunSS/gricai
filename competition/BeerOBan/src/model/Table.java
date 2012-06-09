package model;

public class Table {

	
	private Cell [][] cells;
	
	public Table(String filename){
		//TODO load level
	}

	
	public Cell getCell(int row, int col){
		return cells[row][col];
	}
	
	public boolean move(int currentRow, int currentCell, int destinationRow, int destinationCell){
		return false;
	}
	
}
