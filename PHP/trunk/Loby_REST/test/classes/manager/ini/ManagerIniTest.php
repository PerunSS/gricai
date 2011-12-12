<?php
require_once '../../../simpletest/autorun.php';
require_once '../../../../dev/classes/AutoloadClasses.inc';

class ManagerIniTest
		extends UnitTestCase {

	/**
	 * Function for testing ManagerIni's static function get_connection_params
	 */

	public function test_get_connection_params() {
		$params = ManagerIni::get_connection_params();
		$this->assertTrue(is_array($params));
		$this->assertTrue(isset($params['host']));
		$this->assertTrue(isset($params['database']));
		$this->assertTrue(isset($params['username']));
		$this->assertTrue(isset($params['password']));
	}
}
