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
	}
}
