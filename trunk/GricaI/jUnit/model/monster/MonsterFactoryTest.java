package model.monster;

import static org.junit.Assert.*;

import org.junit.Test;

import model.monster.human.HumanFactory;

public class MonsterFactoryTest {

	MonsterFactory factory = MonsterFactory.getInstance();

	@Test
	public void testHuman() {
		HumanFactory humanFactory = factory.getHumanFactory();
		assertNotSame(null, humanFactory);
	}
}
