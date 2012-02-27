package com.cerspri.games.tapit;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class TapITMusicManager {

	private MediaPlayer displayScore;
	private SoundPool soundPool;
	private int clickPos, clickNeg;
	private MediaPlayer gameMusic;
	
	private Context context;
	private static TapITMusicManager instance = new TapITMusicManager();
	private TapITMusicManager(){}
	
	public static TapITMusicManager getInstance(){
		return instance;
	}
	
	public void setContext(Context context){
		this.context = context;
	}
	
	public void loadMusic(){
		displayScore = MediaPlayer.create(context, R.raw.display_score_end);
		displayScore.setLooping(true);
		soundPool = new SoundPool(20,AudioManager.STREAM_MUSIC,100);
		clickPos = soundPool.load(context, R.raw.click,0);
		clickNeg = soundPool.load(context, R.raw.click_n, 0);
		gameMusic = MediaPlayer.create(context, R.raw.game_play);
		gameMusic.setLooping(true);
	}
	
	public void pauseGameMusic(){
		gameMusic.pause();
	}
	
	public void startGameMusic(){
		gameMusic.start();
	}
	
	public void stopGameMusic(){
		if(gameMusic.isPlaying()){
			gameMusic.stop();
		}
	}
	
	public void releaseGameMusic(){
		gameMusic.release();
	}
	
	public void startGameScoreMusic(){
		displayScore.start();
	}
	
	public void stopGameScoreMusic(){
		displayScore.stop();
	}

	public void releaseGameScoreMusic(){
		displayScore.release();
	}
	
	public void playPositive(){
		soundPool.play(clickPos, 1, 1, 0, 0, 1);
	}
	
	public void playNegative(){
		soundPool.play(clickNeg, 1, 1, 0, 0, 1);
	}
}
