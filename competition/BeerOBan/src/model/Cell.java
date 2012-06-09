package model;

public class Cell {
	
	private Player player;
	private Crate crate;
	
	
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
}
