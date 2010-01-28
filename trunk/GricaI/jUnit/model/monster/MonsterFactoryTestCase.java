package model.monster;

import model.monster.human.HumanFactory;
import junit.framework.TestCase;

public class MonsterFactoryTestCase extends TestCase {

	MonsterFactory factory;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		factory = MonsterFactory.getInstance();
	}
	
	public void testHuman(){
		HumanFactory humanFactory = factory.getHumanFactory();
		assertNotSame(null, humanFactory);
	}
	
}
