<?php
require_once '../../simpletest/autorun.php';
require_once '../../../dev/classes/AutoloadClasses.inc';
require_once '../model/TestModal.class.inc';

class FormatersTest
		extends UnitTestCase {

	/**
	 * Function for testing Formaters' function format_single_row_into_object
	 */

	public function test_single_row_into_object() {
		$manager = new Manager();
		$result = $manager->execute('call test_dva(?, ?)',
						array(
							0 => 1, 1 => 'prvi'
						));
		Formaters::format_single_row_into_object('TestModal', $result, $object);
		$this->assertTrue($object instanceof TestModal);

		$result = $manager->execute('call test_dva(?, ?)',
						array(
							0 => 1, 1 => 'tr'
						));
		Formaters::format_single_row_into_object('TestModal', $result, $object);
		$this->assertTrue($object instanceof Error);

		$result = $manager->execute('call test_pet(?, ?)',
						array(
							0 => 1, 1 => 'tr'
						));
		Formaters::format_single_row_into_object('TestModal', $result, $object);
		$this->assertTrue($object instanceof Error);

		$manager = new Manager();
		$result = $manager->execute('call test()', array());
		Formaters::format_single_row_into_object('TestModal', $result, $object);
		$this->assertTrue($object instanceof Error);
	}
}
