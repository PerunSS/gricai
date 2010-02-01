package model.monster;

import static org.junit.Assert.assertNotSame;
import game.model.monster.MonsterFactory;
import game.model.monster.human.HumanFactory;

import org.junit.Test;

public class MonsterFactoryTest {

	MonsterFactory factory = MonsterFactory.getInstance();

	@Test
	public void testHuman() {
		HumanFactory humanFactory = factory.getHumanFactory();
		assertNotSame(null, humanFactory);
	}
}
