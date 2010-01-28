package model.monster.human;

import view.painters.monster.human.FootmanPainter;


public class Footman extends Human {

	Footman(){
		setStrength(65);
		setInteligence(38);
		setAgility(51);
		setVitality(100);
		setMinDamage(11);
		setMaxDamage(12);
		setActionPoints(6);
		setDefence(12);
		setRange(1);
		setWaterResistance(-15);
		setFireResistance(-5);
		setEarthResistance(30);
		setAirResistance(25);
		setPainter(new FootmanPainter());
	}
}
