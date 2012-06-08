package com.cerspri.games.tapit.lite.model;

public class SoundOptions {

	private static SoundOptions instance = new SoundOptions();
	private boolean playSound = true;
	private boolean playMusic = true;

	private SoundOptions() {
	}

	public static SoundOptions getInstance() {
		return instance;
	}

	public boolean isPlaySound() {
		return playSound;
	}

	public void tooglePlaySound() {
		playSound = !playSound;
	}

	public boolean isPlayMusic() {
		return playMusic;
	}

	public void tooglePlayMusic() {
		playMusic = !playMusic;
	}
}
