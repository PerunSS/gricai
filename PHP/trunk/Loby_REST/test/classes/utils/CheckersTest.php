<?php
require_once '../../simpletest/autorun.php';
require_once '../../../dev/classes/AutoloadClasses.inc';

class CheckersTest
		extends UnitTestCase {

	/**
	 * Function for testing Checkers' function is_null
	 */

	public function test_is_null() {
		$error = array();
		$array = array(
			'null' => null
		);
		$checks = array(
			'null'
		);
		Checkers::is_null($array, $checks, $error);
		$this->assertTrue(is_array($error));
		$this->assertTrue(count($error));
		$this->assertTrue($error[0] == Checkers::MESSAGE_NULL . "null");
		$error = array();
		$array = array(
			'null' => ""
		);
		$checks = array(
			'null'
		);
		Checkers::is_null($array, $checks, $error);
		$this->assertTrue(is_array($error));
		$this->assertTrue(count($error));
		$this->assertTrue($error[0] == Checkers::MESSAGE_NULL . "null");
		$error = array();
		$array = array(
			'null' => "      "
		);
		$checks = array(
			'null'
		);
		Checkers::is_null($array, $checks, $error);
		$this->assertTrue(is_array($error));
		$this->assertTrue(count($error));
		$this->assertTrue($error[0] == Checkers::MESSAGE_NULL . "null");
		$error = array();
		$array = array(
			'null' => "\t"
		);
		$checks = array(
			'null'
		);
		Checkers::is_null($array, $checks, $error);
		$this->assertTrue(is_array($error));
		$this->assertTrue(count($error));
		$this->assertTrue($error[0] == Checkers::MESSAGE_NULL . "null");
		$error = array();
		$array = array(
			'null' => "\n"
		);
		$checks = array(
			'null'
		);
		Checkers::is_null($array, $checks, $error);
		$this->assertTrue(is_array($error));
		$this->assertTrue(count($error));
		$this->assertTrue($error[0] == Checkers::MESSAGE_NULL . "null");
		$error = array();
		$array = array(
				'p1' => 0, 'p2' => 23, 'p3' => "sdas", 'p4' => "343",
				'p5' => "24.545"
		);
		$checks = array(
			'p1', 'p2', 'p3', 'p4', 'p5'
		);
		Checkers::is_null($array, $checks, $error);
		$this->assertTrue(is_array($error));
		$this->assertFalse(count($error));
	}

	/**
	 * Function for testing Checkers' function is_number
	 */

	public function test_is_number() {
		$error = array();
		$array = array(
			'none' => "a"
		);
		$checks = array(
			'none'
		);
		Checkers::is_number($array, $checks, $error);
		$this->assertTrue(is_array($error));
		$this->assertTrue(count($error));
		$this->assertTrue($error[0] == Checkers::MESSAGE_NON_NUMBER . "none");
		$error = array();
		$array = array(
			'none' => "43fds"
		);
		$checks = array(
			'none'
		);
		Checkers::is_number($array, $checks, $error);
		$this->assertTrue(is_array($error));
		$this->assertTrue(count($error));
		$this->assertTrue($error[0] == Checkers::MESSAGE_NON_NUMBER . "none");
		$error = array();
		$array = array(
			'none' => 23.43
		);
		$checks = array(
			'none'
		);
		Checkers::is_number($array, $checks, $error, true);
		$this->assertTrue(is_array($error));
		$this->assertTrue(count($error));
		$this->assertTrue($error[0] == Checkers::MESSAGE_NON_INTEGER . "none");
		$error = array();
		$array = array(
			'none' => .54
		);
		$checks = array(
			'none'
		);
		Checkers::is_number($array, $checks, $error, true);
		$this->assertTrue(is_array($error));
		$this->assertTrue(count($error));
		$this->assertTrue($error[0] == Checkers::MESSAGE_NON_INTEGER . "none");
		$error = array();
		$array = array(
			'none' => ".65"
		);
		$checks = array(
			'none'
		);
		Checkers::is_number($array, $checks, $error, true);
		$this->assertTrue(is_array($error));
		$this->assertTrue(count($error));
		$this->assertTrue($error[0] == Checkers::MESSAGE_NON_INTEGER . "none");
		$error = array();
		$array = array(
				'p1' => 0, 'p2' => 23.4, 'p3' => "043", 'p4' => "0",
				'p5' => ".545", 'p6' => .45, 'p7' => 0675, 'p8' => "00.43"
		);
		$checks = array(
			'p1', 'p2', 'p3', 'p4', 'p5', 'p6', 'p7', 'p8'
		);
		Checkers::is_null($array, $checks, $error);
		$this->assertTrue(is_array($error));
		$this->assertFalse(count($error));
		$error = array();
		$array = array(
			'p1' => 0, 'p2' => 23, 'p3' => "043", 'p4' => "0", 'p5' => "545"
		);
		$checks = array(
			'p1', 'p2', 'p3', 'p4', 'p5'
		);
		Checkers::is_null($array, $checks, $error, true);
		$this->assertTrue(is_array($error));
		$this->assertFalse(count($error));
	}

	/**
	 * Function for testing Checkers' function is_date
	 */

	public function test_is_date() {
		$error = array();
		$array = array(
			"day_b" => "A", "month_b" => 12, "year_b" => 1912
		);
		$checks = array(
				array(
					"day" => "day_b", "month" => "month_b", "year" => "year_b"
				)
		);
		Checkers::is_date($array, $checks, $error);
		$this->assertTrue(is_array($error));
		$this->assertTrue(count($error));
		$this->assertTrue($error[0] == Checkers::MESSAGE_NON_NUMBER . "day_b");
		$error = array();
		$array = array(
			"day_b" => "13", "month_b" => "2.3", "year_b" => 1912
		);
		$checks = array(
				array(
					"day" => "day_b", "month" => "month_b", "year" => "year_b"
				)
		);
		Checkers::is_date($array, $checks, $error);
		$this->assertTrue(is_array($error));
		$this->assertTrue(count($error));
		$this->assertTrue(
						$error[0] == Checkers::MESSAGE_NON_INTEGER . "month_b");
		$error = array();
		$array = array(
			"day_b" => "13", "month_b" => "2", "year_b" => ".3"
		);
		$checks = array(
				array(
					"day" => "day_b", "month" => "month_b", "year" => "year_b"
				)
		);
		Checkers::is_date($array, $checks, $error);
		$this->assertTrue(is_array($error));
		$this->assertTrue(count($error));
		$this->assertTrue($error[0] == Checkers::MESSAGE_NON_INTEGER . "year_b");
		$error = array();
		$array = array(
			"day_b" => "31", "month_b" => "2", "year_b" => "1994"
		);
		$checks = array(
				array(
					"day" => "day_b", "month" => "month_b", "year" => "year_b"
				)
		);
		Checkers::is_date($array, $checks, $error);
		$this->assertTrue(is_array($error));
		$this->assertTrue(count($error));
		$this->assertTrue(
						$error[0] == Checkers::MESSAGE_NON_DATE . "day_b"
								&& $error[1]
										== Checkers::MESSAGE_NON_DATE
												. "month_b"
								&& $error[2]
										== Checkers::MESSAGE_NON_DATE
												. "year_b");
		$year = date("Y");
		$month = date("n");
		$day = date("j");
		$check_day = date("j",
				mktime(0, 0, 0, $month, $day + 1,
						$year - ManagerIni::get_underage()));
		$check_month = date("n",
				mktime(0, 0, 0, $month, $day + 1,
						$year - ManagerIni::get_underage()));
		$check_year = date("Y",
				mktime(0, 0, 0, $month, $day + 1,
						$year - ManagerIni::get_underage()));
		$error = array();
		$array = array(
				"day_b" => $check_day, "month_b" => $check_month,
				"year_b" => $check_year
		);
		$checks = array(
				array(
					"day" => "day_b", "month" => "month_b", "year" => "year_b"
				)
		);
		Checkers::is_date($array, $checks, $error, true);
		$this->assertTrue(is_array($error));
		$this->assertTrue(count($error));
		$this->assertTrue(
						$error[0] == Checkers::MESSAGE_NON_UNDERAGE . "year_b"
								|| $error[0]
										== Checkers::MESSAGE_NON_UNDERAGE
												. "month_b"
								|| $error[0]
										== Checkers::MESSAGE_NON_UNDERAGE
												. "day_b");
		$error = array();
		$array = array(
			"day_b" => "28", "month_b" => "2", "year_b" => "1994"
		);
		$checks = array(
				array(
						"day" => "day_b", "month" => "month_b",
						"year" => "year_b", "new_parameter" => "date"
				)
		);
		Checkers::is_date($array, $checks, $error);
		$this->assertTrue(is_array($error));
		$this->assertFalse(count($error));
		$this->assertTrue(isset($array['date']));
		$check_day = date("j",
				mktime(0, 0, 0, $month, $day - 1,
						$year - ManagerIni::get_underage()));
		$check_month = date("n",
				mktime(0, 0, 0, $month, $day - 1,
						$year - ManagerIni::get_underage()));
		$check_year = date("Y",
				mktime(0, 0, 0, $month, $day - 1,
						$year - ManagerIni::get_underage()));
		$error = array();
		$array = array(
				"day_b" => $check_day, "month_b" => $check_month,
				"year_b" => $check_year
		);
		$checks = array(
				array(
						"day" => "day_b", "month" => "month_b",
						"year" => "year_b", "new_parameter" => "date"
				)
		);
		Checkers::is_date($array, $checks, $error, true);
		$this->assertTrue(is_array($error));
		$this->assertFalse(count($error));
		$this->assertTrue(isset($array['date']));
	}
	/**
	 * Function for testing Checkers' function is_password
	 */

	public function test_is_password() {
		$error = array();
		$array = array(
			"p" => "As", "c" => "1"
		);
		$checks = array(
			array(
				"password" => "p", "confirm_password" => "c"
			)
		);
		Checkers::is_password($array, $checks, $error);
		$this->assertTrue(is_array($error));
		$this->assertTrue(count($error));
		$this->assertTrue($error[0] == Checkers::MESSAGE_NON_MATCH . "c");

		$error = array();
		$array = array(
			"p" => "As", "c" => "as"
		);
		$checks = array(
			array(
				"password" => "p", "confirm_password" => "c"
			)
		);
		Checkers::is_password($array, $checks, $error);
		$this->assertTrue(is_array($error));
		$this->assertTrue(count($error));
		$this->assertTrue($error[0] == Checkers::MESSAGE_NON_MATCH . "c");

		$error = array();
		$array = array(
			"p" => "As", "c" => "As"
		);
		$checks = array(
			array(
				"password" => "p", "confirm_password" => "c"
			)
		);
		Checkers::is_password($array, $checks, $error);
		$this->assertTrue(is_array($error));
		$this->assertFalse(count($error));

		$error = array();
		$array = array(
			"p" => "As", "c" => "As"
		);
		$checks = array(
				array(
						"password" => "p", "confirm_password" => "c",
						"new_parameter" => "sifra"
				)
		);
		Checkers::is_password($array, $checks, $error);
		$this->assertTrue(is_array($error));
		$this->assertFalse(count($error));
		$this->assertTrue(isset($array['sifra']));
	}
	/**
	 * Function for testing Checkers' function is_email
	 */

	public function test_is_email() {
		$error = array();
		$array = array(
			'none' => "a"
		);
		$checks = array(
			'none'
		);
		Checkers::is_email($array, $checks, $error);
		$this->assertTrue(is_array($error));
		$this->assertTrue(count($error));
		$this->assertTrue($error[0] == Checkers::MESSAGE_NON_EMAIL . "none");

		$error = array();
		$array = array(
			'none' => "a@s"
		);
		$checks = array(
			'none'
		);
		Checkers::is_email($array, $checks, $error);
		$this->assertTrue(is_array($error));
		$this->assertTrue(count($error));
		$this->assertTrue($error[0] == Checkers::MESSAGE_NON_EMAIL . "none");

		$error = array();
		$array = array(
			'none' => "a.com"
		);
		$checks = array(
			'none'
		);
		Checkers::is_email($array, $checks, $error);
		$this->assertTrue(is_array($error));
		$this->assertTrue(count($error));
		$this->assertTrue($error[0] == Checkers::MESSAGE_NON_EMAIL . "none");

		$error = array();
		$array = array(
			'none' => "a@.com"
		);
		$checks = array(
			'none'
		);
		Checkers::is_email($array, $checks, $error);
		$this->assertTrue(is_array($error));
		$this->assertTrue(count($error));
		$this->assertTrue($error[0] == Checkers::MESSAGE_NON_EMAIL . "none");

		$error = array();
		$array = array(
			'none' => "@a.com"
		);
		$checks = array(
			'none'
		);
		Checkers::is_email($array, $checks, $error);
		$this->assertTrue(is_array($error));
		$this->assertTrue(count($error));
		$this->assertTrue($error[0] == Checkers::MESSAGE_NON_EMAIL . "none");

		$error = array();
		$array = array(
			'none' => "a@a.com"
		);
		$checks = array(
			'none'
		);
		Checkers::is_email($array, $checks, $error);
		$this->assertTrue(is_array($error));
		$this->assertFalse(count($error));
	}
	/**
	 * Function for testing Checkers' function is_valid_length
	 */

	public function test_is_valid_length() {
		$error = array();
		$array = array(
			"c" => "As",
		);
		$checks = array(
			array(
				"parameter" => "c", "minimum" => 3
			)
		);
		Checkers::is_valid_length($array, $checks, $error);
		$this->assertTrue(is_array($error));
		$this->assertTrue(count($error));
		$this->assertTrue($error[0] == Checkers::MESSAGE_INVALID_MINIMUM . "c");

		$error = array();
		$array = array(
			"c" => "As",
		);
		$checks = array(
			array(
				"parameter" => "c", "maximum" => 1
			)
		);
		Checkers::is_valid_length($array, $checks, $error);
		$this->assertTrue(is_array($error));
		$this->assertTrue(count($error));
		$this->assertTrue($error[0] == Checkers::MESSAGE_INVALID_MAXIMUM . "c");

		$error = array();
		$array = array(
			"c" => "As",
		);
		$checks = array(
				array(
					"parameter" => "c", "minimum" => 3, "maximum" => 1
				)
		);
		Checkers::is_valid_length($array, $checks, $error);
		$this->assertTrue(is_array($error));
		$this->assertTrue(count($error));
		$this->assertTrue($error[0] == Checkers::MESSAGE_INVALID_MINIMUM . "c");
		$this->assertTrue($error[1] == Checkers::MESSAGE_INVALID_MAXIMUM . "c");

		$error = array();
		$array = array(
			"c" => "As",
		);
		$checks = array(
				array(
					"parameter" => "c", "minimum" => 1, "maximum" => 3
				)
		);
		Checkers::is_valid_length($array, $checks, $error);
		$this->assertTrue(is_array($error));
		$this->assertFalse(count($error));
	}

	/**
	 * Function for testing Checkers' function set_order
	 */

	public function test_set_order() {
		$error = array();
		$array = array(
			"b" => "B", "c" => "C"
		);
		$checks = array(
			0 => "c", 1 => "e"
		);
		Checkers::set_order($array, $checks, $order, $error);
		$this->assertTrue(is_array($error));
		$this->assertTrue(count($error));
		$this->assertTrue($error[0] == Checkers::MESSAGE_INVALID_ORDER . "e");
		
		$error = array();
		$array = array(
			"b" => "B", "c" => "C"
		);
		$checks = array(
			0 => "c", 1 => "b"
		);
		Checkers::set_order($array, $checks, $order, $error);
		$this->assertTrue(is_array($error));
		$this->assertFalse(count($error));
		$this->assertTrue(count($order));
		$this->assertTrue($order[0] == "C");
		$this->assertTrue($order[1] == "B");
	}
}
