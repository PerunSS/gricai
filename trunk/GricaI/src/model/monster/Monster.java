package model.monster;

import java.util.ArrayList;
import java.util.List;

import utils.AttacCalculator;
import view.painters.monster.MonsterPainter;

import model.board.Board;
import model.board.Field;
import model.effect.Effect;

public abstract class Monster {

	private int strength;
	private int inteligence;
	private int agility;
	private int vitality;
	private int minDamage;
	private int maxDamage;
	private int defence;
	private int actionPoints;
	private int range;
	private int earthResistance;
	private int fireResistance;
	private int waterResistance;
	private int airResistance;
	private List<Effect> effects = new ArrayList<Effect>();
	private boolean dead;
	private MonsterPainter painter;

	public void move(Field field) {
		Board.getInstance().removeMonster(this);
		field.setMonster(this);
	}

	public void attack(Field field) {
		AttacCalculator.getInstance().attack(this, field.getMonster());
	}

	public void endTurn() {
		Board.getInstance().endCurrentTurn();
	}
	
	public void addEffect(Effect effect){
		effects.add(effect);
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getInteligence() {
		return inteligence;
	}

	public void setInteligence(int inteligence) {
		this.inteligence = inteligence;
	}

	public int getAgility() {
		return agility;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public int getVitality() {
		return vitality;
	}

	public void setVitality(int vitality) {
		this.vitality = vitality;
	}

	public int getMinDamage() {
		return minDamage;
	}

	public void setMinDamage(int minDamage) {
		this.minDamage = minDamage;
	}

	public int getMaxDamage() {
		return maxDamage;
	}

	public void setMaxDamage(int maxDamage) {
		this.maxDamage = maxDamage;
	}

	public int getDefence() {
		return defence;
	}

	public void setDefence(int defence) {
		this.defence = defence;
	}

	public int getActionPoints() {
		return actionPoints;
	}

	public void setActionPoints(int actionPoints) {
		this.actionPoints = actionPoints;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public int getEarthResistance() {
		return earthResistance;
	}

	public void setEarthResistance(int earthResistance) {
		this.earthResistance = earthResistance;
	}

	public int getFireResistance() {
		return fireResistance;
	}

	public void setFireResistance(int fireResistance) {
		this.fireResistance = fireResistance;
	}

	public int getWaterResistance() {
		return waterResistance;
	}

	public void setWaterResistance(int waterResistance) {
		this.waterResistance = waterResistance;
	}

	public int getAirResistance() {
		return airResistance;
	}

	public void setAirResistance(int airResistance) {
		this.airResistance = airResistance;
	}

	public List<Effect> getEffects() {
		return effects;
	}

	public void setEffects(List<Effect> effects) {
		this.effects = effects;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public boolean isDead() {
		return dead;
	}

	public void setPainter(MonsterPainter painter) {
		this.painter = painter;
	}

	public MonsterPainter getPainter() {
		return painter;
	}

}
