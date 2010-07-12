package model.monster;

import static org.junit.Assert.assertNotSame;

import org.junit.Test;

import com.gricai.game.model.monster.MonsterFactory;
import com.gricai.game.model.monster.human.HumanFactory;

public class MonsterFactoryTest {

	MonsterFactory factory = MonsterFactory.getInstance();

	@Test
	public void testHuman() {
		HumanFactory humanFactory = factory.getHumanFactory();
		assertNotSame(null, humanFactory);
	}
}
