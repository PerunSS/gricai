package com.cerspri.games.tod.model;

import java.util.Set;

public class Player {
	private String name;
	private String gender;
	private String affinity;
	private int mask;
	private int targetMask;
	private Set<Integer> playerTargets;
	
	public Player(String name, String gender, String affinity){
		this.name = name;
		this.gender = gender;
		this.affinity = affinity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAffinity() {
		return affinity;
	}

	public void setAffinity(String affinity) {
		this.affinity = affinity;
	}

	public int getMask() {
		return mask;
	}

	public int getTargetMask() {
		return targetMask;
	}

	public void setPlayerTargets(Set<Integer> playerTargets) {
		this.playerTargets = playerTargets;
	}
	
	public int getRandomPlayerTarget(){
		return (int) (Math.random() * this.playerTargets.size());
	}
}
