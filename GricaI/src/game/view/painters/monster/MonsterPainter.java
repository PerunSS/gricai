package game.view.painters.monster;

import java.awt.Graphics2D;
import java.awt.Shape;

public abstract class MonsterPainter {

	protected transient Shape shape;
	
	public abstract void paint(Graphics2D g2d);
}
