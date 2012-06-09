package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Table {

	private Cell[][] cells;

	private int rows, cols;

	public Table(String filename) {
		Scanner sc;
		try {
			sc = new Scanner(new File(filename));

			String dimension[] = sc.nextLine().split(";");
			cells = new Cell[Integer.parseInt(dimension[0])][Integer
					.parseInt(dimension[1])];
			rows = Integer.parseInt(dimension[0]);
			cols = Integer.parseInt(dimension[1]);
			int row = 0;
			while (sc.hasNext()) {
				String rowElements[] = sc.nextLine().split(";");
				int column = 0;
				for (String element : rowElements) {
					element = element.trim();
					cells[row][column] = new Cell();
					// prazna celija
					if (element.equals("0"))
						;
					// igrac
					else if (element.startsWith("p")) {
						Game.getInstance().setPlayer(row, column);
						cells[row][column].setPlayer(new Player());
					}
					// sanduk piva s tezinom - npr c2 - sanduk tezine 2
					else if (element.startsWith("c")) {
						cells[row][column].setCrate(new Crate());
					}
					// prepreka
					else if (element.startsWith("x")) {
						cells[row][column].setWall(true);
					}
					// cilj
					else if (element.startsWith("d")) {
						cells[row][column].setDestination(true);
					}
					column++;
				}
				row++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Cell getCell(int row, int col) {
		if (row < 0 || row >= rows)
			return null;
		if (col < 0 || col >= cols)
			return null;
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

	public int getCols() {
		return cols;
	}

	public int getRows() {
		return rows;
	}

}
