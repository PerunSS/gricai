package model.monster;

import java.lang.reflect.Constructor;

import utils.log.Log;

import model.monster.human.HumanFactory;

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
			e.printStackTrace();
			Log.logException(e);
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
