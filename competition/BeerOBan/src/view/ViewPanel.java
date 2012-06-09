package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import model.Cell;
import model.Game;
import model.Table;

public class ViewPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int CELL_SIZE = 40;

	ImageIcon gurko, crate, dest;

	View view;

	public ViewPanel(View view) {
		this.view = view;
		gurko = new ImageIcon("gurko.png");
		crate = new ImageIcon("crate.png");
		dest = new ImageIcon("destination.png");
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		paintControls(g);
		if (Game.getInstance().isEndGame()) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("verdana", Font.BOLD, 40));
			g.drawString("TOTAL SCORE: " + Game.getInstance().getTotalScore(),
					(getWidth() - 300) / 2, getHeight() / 2);
		} else {

			int rows = Game.getInstance().getRows();
			int cols = Game.getInstance().getCols();

			int startX = (getWidth() - cols * CELL_SIZE) / 2;
			int startY = 30 + (getHeight() - rows * CELL_SIZE) / 2;
			g.setColor(Color.WHITE);
			int x = startX;
			int y = startY;
			for (int i = 0; i < rows + 1; i++) {
				g.drawLine(x, y, x + cols * CELL_SIZE, y);
				y += CELL_SIZE;
			}
			x = startX;
			y = startY;
			for (int i = 0; i < cols + 1; i++) {
				g.drawLine(x, y, x, y + rows * CELL_SIZE);
				x += CELL_SIZE;
			}
			Table table = Game.getInstance().getTable();
			x = startX;
			y = startY;
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					Cell cell = table.getCell(i, j);
					if (cell.isDestination()) {
						g.drawImage(dest.getImage(), x, y, null);
					}
					if (cell.getCrate() != null) {
						g.drawImage(crate.getImage(), x + 2, y + 2, null);
					} else if (cell.getPlayer() != null) {
						g.drawImage(gurko.getImage(), x + 4, y + 4, null);
					} else if (cell.isWall()) {
						g.setColor(Color.GRAY);
						g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
					}
					x += CELL_SIZE;
				}
				x = startX;
				y += CELL_SIZE;
			}

			g.setColor(Color.WHITE);
			g.setFont(new Font("verdana", Font.BOLD, 16));
			g.drawString("Level score: " + Game.getInstance().getLvlScore(),
					(getWidth() - 300) / 2 - 50, 20);
			g.drawString("Level: " + (view.currentLvl + 1),
					(getWidth() - 300) / 2 + 100, 20);
			g.drawString("Total score: " + Game.getInstance().getTotalScore(),
					(getWidth() - 300) / 2 + 200, 20);

			if (Game.getInstance().checkWin()) {
				g.setColor(Color.GREEN);
				g.drawRect((getWidth() - 200) / 2 - 71,
						50 + (getHeight() - 200) / 2 - 51, 342, 102);
				g.setColor(Color.BLACK);
				g.fillRect((getWidth() - 200) / 2 - 70,
						50 + (getHeight() - 200) / 2 - 50, 340, 100);
				g.setColor(Color.GREEN);
				g.setFont(new Font("verdana", Font.BOLD, 40));
				g.drawString("YOU WIN", (getWidth() - 200) / 2,
						50 + (getHeight() - 200) / 2);
				g.setFont(new Font("verdana", Font.BOLD, 20));
				g.drawString("N - next lvl, P - previous lvl",
						(getWidth() - 200) / 2 - 50,
						75 + (getHeight() - 200) / 2);
			}
		}
	}

	private void paintControls(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("verdana", Font.BOLD, 16));
		g.drawString("R - reset, ESC - exit", (getWidth() - 300) / 2 + 30, 50);
		g.drawString("current top score: " + Game.getInstance().getHighscore(),
				(getWidth() - 300) / 2 - 20, 80);
		g.setFont(new Font("verdana", Font.ITALIC, 8));
		g.drawString("(smaller number is better)", (getWidth() - 300) / 2 + 70,
				90);
	}
}
