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
	 * creates Footman, Peasant
	 * @return new Footman, Peasant
	 */
	
	public Footman createFootman(){
		return new Footman();
	}
	
	public Peasant createPeasant(){
		return new Peasant();
	}

}
