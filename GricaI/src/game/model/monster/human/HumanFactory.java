package game.model.monster.human;

/**
 * Factory for creating Human monster types
 * @author aleksandarvaricak
 *
 */
public class HumanFactory {
	
	private HumanFactory(){
		
	}
	
	/**
	 * creates Footman
	 * @return new Footman
	 */
	public Footman createFootman(){
		return new Footman();
	}

}
