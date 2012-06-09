package model;

public class Cell {
	
	private Player player;
	private Crate crate;
	private boolean destination;
	private boolean wall;
	
	public Cell(){
		
	}
	


	public Player getPlayer() {
		return player;
	}


	public void setPlayer(Player player) {
		this.player = player;
	}


	public Crate getCrate() {
		return crate;
	}


	public void setCrate(Crate crate) {
		this.crate = crate;
	}



	public boolean isDestination() {
		return destination;
	}



	public void setDestination(boolean destination) {
		this.destination = destination;
	}



	public boolean isWall() {
		return wall;
	}



	public void setWall(boolean wall) {
		this.wall = wall;
	}
}
