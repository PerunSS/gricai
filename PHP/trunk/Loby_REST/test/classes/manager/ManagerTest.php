<?php
require_once '../../simpletest/autorun.php';
require_once '../../../dev/classes/AutoloadClasses.inc';

class ManagerTest
		extends UnitTestCase {

	/**
	 * Function for testing Manager's function execute
	 */

	public function test_execute() {
		$manager = new Manager();
		$result = $manager->execute('call test();', array());
		$this->assertTrue($result instanceof PDOStatement);
		$result = $manager->execute('call test_dva(?,?);',
						array(
							0 => 1, 1 => 'prvi'
						));
		$this->assertTrue($result instanceof PDOStatement);
		$result = $manager->execute('select test_cetiri(?) as status;',
						array(
							0 => 'prvi'
						));
		$this->assertTrue($result instanceof PDOStatement);
		$result = $manager->execute('insert into za_test (nesto, datum) values(?,?);',
						array(
							0 => 'novi', 1 => date(
									ManagerIni::get_date_format())
						));
		$this->assertTrue($result instanceof PDOStatement);
	}
}
