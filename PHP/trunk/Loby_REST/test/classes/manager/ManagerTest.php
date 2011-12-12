<?php
require_once '../../simpletest/autorun.php';
require_once '../../../dev/classes/AutoloadClasses.inc';

class ManagerTest
		extends UnitTestCase {

	/**
	 * Function for testing Manager's function execute_procedure
	 */

	public function test_execute_procedure() {
		$manager = new Manager();
		$result = $manager->execute_procedure('test', array());
		$this->assertTrue($result instanceof PDOStatement);
		$result = $manager->execute_procedure('test_dva', array(
					0 => 1, 1 => 'prvi'
				));
		$this->assertTrue($result instanceof PDOStatement);
	}
}
