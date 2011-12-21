<?php
require_once '../../../simpletest/autorun.php';
require_once '../../../../dev/classes/AutoloadClasses.inc';
require_once '../../model/TestModal.class.inc';

class FormatSingleRowObjectTest
		extends UnitTestCase {
	/**
	 * Function for testing FormatSingleRowObject's function format
	 */

	public function test_format() {
		$manager = new Manager();
		$result = $manager->execute_procedure('test_dva',
						array(
							0 => 1, 1 => 'prvi'
						));
		$formater = new FormatSingleRowObject('TestModal');
		$this->assertTrue($formater->format($result) instanceof TestModal);
		$result = $manager->execute_procedure('test_dva',
						array(
							0 => 1, 1 => 'tr'
						));
		$formater = new FormatSingleRowObject('TestModal');
		$this->assertTrue($formater->format($result) instanceof Error);
		$result = $manager->execute_procedure('test_pet',
						array(
							0 => 1, 1 => 'tr'
						));
		$formater = new FormatSingleRowObject('TestModal');
		$this->assertTrue($formater->format($result) instanceof Error);
		$manager = new Manager();
		$result = $manager->execute_procedure('test', array());
		$formater = new FormatSingleRowObject('TestModal');
		$this->assertTrue($formater->format($result) instanceof Error);
	}
}
