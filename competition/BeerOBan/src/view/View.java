package view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import model.Game;
import model.Game.Direction;

public class View extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ViewPanel panel;
	Controler controler;
	
	String lvls[] = {"lvl0","lvl1","lvl2","lvl3","lvl4","lvl5","lvl6","lvl7","lvl8","lvl9","lvl10"};
	int currentLvl = 0;
	
	public View (){
		panel = new ViewPanel(this);
		controler = new Controler();
		setContentPane(panel);
		addKeyListener(controler);
		setSize(1000, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	class Controler implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				Game.getInstance().move(Direction.DOWN);
				panel.repaint();
			} else if (e.getKeyCode() == KeyEvent.VK_UP) {
				Game.getInstance().move(Direction.UP);
				panel.repaint();
			} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				Game.getInstance().move(Direction.LEFT);
				panel.repaint();
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				Game.getInstance().move(Direction.RIGHT);
				panel.repaint();
			} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				System.exit(0);
			} else if(e.getKeyCode() == KeyEvent.VK_R){
				Game.getInstance().loadLevel(lvls[currentLvl]);
				panel.repaint();
			} else if(e.getKeyCode() == KeyEvent.VK_N){
				if(Game.getInstance().isWin()){
					currentLvl++;
					if(currentLvl>=lvls.length){
						Game.getInstance().setEndGame(true);
						panel.repaint();
					}else{
						Game.getInstance().loadLevel(lvls[currentLvl]);
						panel.repaint();
					}
				}
			} else if(e.getKeyCode() == KeyEvent.VK_P){
				if(Game.getInstance().isWin()){
					currentLvl--;
					if(currentLvl<0){
						currentLvl = 0;
					}else{
						Game.getInstance().loadLevel(lvls[currentLvl]);
						panel.repaint();
					}
				}
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}

	}
	
	public static void main(String[] args) {
		Game.getInstance().loadLevel("lvl0");
		new View();
	}
}
