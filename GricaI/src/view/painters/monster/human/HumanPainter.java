package view.painters.monster.human;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import view.painters.monster.MonsterPainter;

public class HumanPainter extends MonsterPainter {

	
	public HumanPainter(){
		shape = new Rectangle(20,50);
	}
	
	@Override
	public void paint(Graphics2D g2d) {
		g2d.draw(shape);
	}

}
