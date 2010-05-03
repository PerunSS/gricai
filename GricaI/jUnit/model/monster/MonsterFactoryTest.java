package model.monster;

import static org.junit.Assert.assertNotSame;

import old.game.model.monster.MonsterFactory;
import old.game.model.monster.human.HumanFactory;

import org.junit.Test;

public class MonsterFactoryTest {

	MonsterFactory factory = MonsterFactory.getInstance();

	@Test
	public void testHuman() {
		HumanFactory humanFactory = factory.getHumanFactory();
		assertNotSame(null, humanFactory);
	}
}
