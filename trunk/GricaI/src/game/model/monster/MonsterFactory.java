package game.model.monster;

import game.model.monster.human.HumanFactory;

import java.lang.reflect.Constructor;

import utils.log.LogChooser;


/**
 * Class is used for monster generation
 * 
 * @author aleksandarvaricak
 * 
 */
public class MonsterFactory {

	private static MonsterFactory instance = new MonsterFactory();
	private HumanFactory humanFactory;

	private MonsterFactory() {
		try {
			Constructor<HumanFactory> constructor = HumanFactory.class
					.getDeclaredConstructor();
			constructor.setAccessible(true);
			humanFactory = constructor.newInstance();
		} catch (Exception e) {
			LogChooser.getLog("monsterFactory").logException(e);
		}
	}

	public static MonsterFactory getInstance() {
		return instance;
	}

	/**
	 * return Human Factory
	 * 
	 * @return HumanFactory
	 */
	public HumanFactory getHumanFactory() {
		return humanFactory;
	}

}
