package old.game.model.monster.human;

import old.game.view.painters.monster.human.PeasantPainter;

public class Peasant extends Human{
	Peasant(){
		setStrength(5);
		setInteligence(1);
		setAgility(1);
		setVitality(10);
		setMinDamage(1);
		setMaxDamage(3);
		setActionPoints(4);
		setDefence(1);
		setRange(1);
		setWaterResistance(0);
		setFireResistance(0);
		setEarthResistance(0);
		setAirResistance(0);
		setPainter(new PeasantPainter());
	}
}
