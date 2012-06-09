package model;

public class Table {

	private Cell[][] cells;

	private int rows, cols;

	public Table(String filename) {
		// TODO load level
	}

	public Cell getCell(int row, int col) {
		return cells[row][col];
	}

	public boolean move(int currentRow, int currentCol, int destinationRow,
			int destinationCol) {
		if (destinationRow < 0 || destinationRow >= rows)
			return false;
		if (destinationCol < 0 || destinationCol >= cols) {
			return false;
		}
		return false;
	}

}
