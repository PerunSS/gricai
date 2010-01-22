package utils;

import model.monster.Monster;

public class AttacCalculator {

	private static AttacCalculator instance;

	public static AttacCalculator getInstance() {
		return instance;
	}

	public void attack(Monster attacker, Monster defender) {
		double damage = 0;
		damage = attacker.getMinDamage()
				+ (attacker.getMaxDamage() - attacker.getMinDamage())
				* Math.random();
		damage *= 1 - (defender.getDefence() / 100.0);
		int vitality = defender.getVitality();
		vitality -= (int)Math.round(damage);
		if(vitality>0)
			defender.setVitality(vitality);
		else
			defender.setDead(true);
	}
}
