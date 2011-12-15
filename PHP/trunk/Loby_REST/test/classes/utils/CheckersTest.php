<?php
require_once '../../simpletest/autorun.php';
require_once '../../../dev/classes/AutoloadClasses.inc';

class CheckersTest
		extends UnitTestCase {

	/**
	 * Function for testing Checkers' function is_null
	 */

	public function test_is_null() {
		$this->assertTrue(Checkers::is_null(null, $error));
		$this->assertTrue(Checkers::is_null("", $error));
		$this->assertTrue(Checkers::is_null("        ", $error));
		$this->assertTrue(Checkers::is_null("\t", $error));
		$this->assertTrue(Checkers::is_null("\n", $error));
		$this->assertTrue(!Checkers::is_null(0, $error));
		$this->assertTrue(!Checkers::is_null(23, $error));
		$this->assertTrue(!Checkers::is_null("sdas", $error));
		$this->assertTrue(!Checkers::is_null("0", $error));
		$this->assertTrue(!Checkers::is_null("3434", $error));
		$this->assertTrue(!Checkers::is_null(24.5454, $error));
	}

	/**
	 * Function for testing Checkers' function is_number
	 */

	public function test_is_integer() {
		$this->assertTrue(!Checkers::is_number(null));
		$this->assertTrue(!Checkers::is_number(""));
		$this->assertTrue(!Checkers::is_number("a"));
		$this->assertTrue(!Checkers::is_number("43fdsfd"));
		$this->assertTrue(Checkers::is_number("04342"));
		$this->assertTrue(Checkers::is_number(043543));
		$this->assertTrue(!Checkers::is_number(23.43));
		$this->assertTrue(!Checkers::is_number(0.434));
		$this->assertTrue(!Checkers::is_number(.434));
		$this->assertTrue(!Checkers::is_number(".434"));
		$this->assertTrue(Checkers::is_number(0));
		$this->assertTrue(Checkers::is_number(21));
		$this->assertTrue(Checkers::is_number(23.43, $error, false));
		$this->assertTrue(Checkers::is_number(0.434, $error, false));
		$this->assertTrue(Checkers::is_number(.434, $error, false));
		$this->assertTrue(Checkers::is_number(".434", $error, false));
		$this->assertTrue(Checkers::is_number("00.434", $error, false));
	}

	/**
	 * Function for testing Checkers' function is_date
	 */

	public function test_is_date() {
		$this->assertTrue(!Checkers::is_date("a", 2, 3));
		$this->assertTrue(!Checkers::is_date(NULL, 2, 3));
		$this->assertTrue(!Checkers::is_date(4, "adsd", "sasa"));
		$this->assertTrue(!Checkers::is_date(3, 0, 3));
		$this->assertTrue(Checkers::is_date(1, 2, 3));
		$this->assertTrue(!Checkers::is_date(31, 2, 3));
		$this->assertTrue(Checkers::is_date("1", "    2", "2"));
		$year = date("Y");
		$month = date("n");
		$day = date("j");
		$this->assertTrue(!Checkers::is_date($day, $month, $year, $error, true));
		$check_day = date("j",
				mktime(0, 0, 0, $month, $day,
						$year - ManagerIni::get_underage()));
		$check_month = date("n",
				mktime(0, 0, 0, $month, $day,
						$year - ManagerIni::get_underage()));
		$check_year = date("Y",
				mktime(0, 0, 0, $month, $day,
						$year - ManagerIni::get_underage()));
		$this->assertTrue(
						Checkers::is_date($check_day, $check_month,
								$check_year, $error, true));
		$check_day = date("j",
				mktime(0, 0, 0, $month, $day + 1,
						$year - ManagerIni::get_underage()));
		$check_month = date("n",
				mktime(0, 0, 0, $month, $day + 1,
						$year - ManagerIni::get_underage()));
		$check_year = date("Y",
				mktime(0, 0, 0, $month, $day,
						$year - ManagerIni::get_underage()));
		$this->assertTrue(
						!Checkers::is_date($check_day, $check_month,
								$check_year, $error, true));
		$check_day = date("j",
				mktime(0, 0, 0, $month, $day - 1,
						$year - ManagerIni::get_underage()));
		$check_month = date("n",
				mktime(0, 0, 0, $month, $day - 1,
						$year - ManagerIni::get_underage()));
		$check_year = date("Y",
				mktime(0, 0, 0, $month, $day,
						$year - ManagerIni::get_underage()));
		$this->assertTrue(
						Checkers::is_date($check_day, $check_month,
								$check_year, $error, true));
	}
	/**
	 * Function for testing Checkers' function is_password
	 */

	public function test_is_password() {
		$this->assertTrue(!Checkers::is_password("dsadsada", "gdgrrgdfx"));
		$this->assertTrue(Checkers::is_password("123456", "123456"));
	}
	/**
	 * Function for testing Checkers' function is_email
	 */

	public function test_is_email() {
		$this->assertTrue(!Checkers::is_email("dsadsada"));
		$this->assertTrue(!Checkers::is_email("dojchiloyahoo.com"));
		$this->assertTrue(!Checkers::is_email("dojchilo@yahoocom"));
		$this->assertTrue(Checkers::is_email("dojchilo@yahoo.com"));
		$this->assertTrue(Checkers::is_email("dojchilo@yahoo.co.rs"));
	}
	/**
	 * Function for testing Checkers' function is_valid_length
	 */

	public function test_is_valid_length() {
		$this->assertTrue(Checkers::is_valid_length("dsadsada"));
		$this->assertTrue(Checkers::is_valid_length("dsadsada",4));
		$this->assertTrue(Checkers::is_valid_length("dsadsada",NULL,10));
		$this->assertTrue(Checkers::is_valid_length("dsadsada",6,8));
		$this->assertTrue(!Checkers::is_valid_length("dsadsada",10));
		$this->assertTrue(!Checkers::is_valid_length("dsadsada",null,4));
		$this->assertTrue(!Checkers::is_valid_length("dsadsada",4,5));
		$this->assertTrue(!Checkers::is_valid_length("dsadsada",14,15));
	}
}
