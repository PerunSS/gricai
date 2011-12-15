<?php
require_once '../../simpletest/autorun.php';
require_once '../../../dev/classes/AutoloadClasses.inc';

class ValidatorTest
		extends UnitTestCase {

	/**
	 * Function for testing Manager's function execute_procedure
	 */

	public function test_validate() {
		$parameters = array(
				'a' => 'dsdasd', 'b' => 'dsdasd', 'c' => 'dsdasd@dsad.com',
				'd' => '23', 'e' => '45.34', 'f' => '123456', 'g' => '123456',
				'h' => 'dsadas', 'i' => '23', 'j' => '11', 'l' => '1987',
				'm' => '30', 'n' => '1', 'p' => '1999'
		);
		$v = new Validator($parameters);
		$v->add_non_missing_parameters('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
						'i', 'j', 'l', 'm', 'n', 'p');
		$v->add_non_null_parameters('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
						'i', 'j', 'l', 'm', 'n', 'p');
		$v->add_is_underage_parameters('i', 'j', 'l', 'under');
		$v->add_is_date_parameters('m', 'n', 'p', 'date');
		$v->add_is_email_parameters('c');
		$v->add_is_decimal_parameters('e');
		$v->add_is_integer_parameters('d');
		$v->add_is_password_parameters('f', 'g', 'password');
		$v->add_is_valid_length_parameters('f', 4, 8);
		$v->add_order_parameters('a', 'b', 'c', 'd', 'e', 'password', 'h',
						'date', 'under');
		$this->assertTrue($v->validate($result));		
		$v->clear_is_date_parameters();
		$v->add_is_underage_parameters('m', 'n', 'p', 'date');
		$this->assertTrue(!$v->validate($result));
		$v->clear_is_underage_parameters();
		$v->add_is_date_parameters('m', 'n', 'p', 'date');
		$v->add_is_underage_parameters('i', 'j', 'l', 'under');
		$v->clear_is_decimal_parameters();
		$v->add_is_integer_parameters('e');
		$this->assertTrue(!$v->validate($result));
		$v->add_is_decimal_parameters('e');
		$v->clear_is_integer_parameters();
		$v->add_is_integer_parameters('d');
		$v->add_is_email_parameters('b');
		$this->assertTrue(!$v->validate($result));
		$v->clear_is_email_parameters();
		$v->add_is_email_parameters('c');
		$v->add_is_valid_length_parameters('a', 10, null);
		$this->assertTrue(!$v->validate($result));
		$v->clear_is_valid_length_parameters();
		$v->add_is_valid_length_parameters('f', 4, 8);
		$v->add_is_valid_length_parameters('a', null, 2);
		$this->assertTrue(!$v->validate($result));
		$v->clear_is_valid_length_parameters();
		$v->add_is_valid_length_parameters('f', 4, 8);
		$v->clear_is_password_parameters();
		$v->add_is_password_parameters('f', 'a', 'password');
		$this->assertTrue(!$v->validate($result));
		$v->clear_is_password_parameters();
		$v->add_is_password_parameters('f', 'g', 'password');
		$v->clear_non_missing_parameters();
		$v->add_non_missing_parameters('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
						'i', 'j', 'l', 'm', 'n', 'p', 'dodatak');
		$this->assertTrue(!$v->validate($result));
		$parameters = array(
				'a' => 'dsdasd', 'b' => 'dsdasd', 'c' => 'dsdasd@dsad.com',
				'd' => '23', 'e' => '45.34', 'f' => '123456', 'g' => '123456',
				'h' => 'dsadas', 'i' => null, 'j' => null, 'l' => null,
				'm' => '30', 'n' => '1', 'p' => '1999'
		);
		$v = new Validator($parameters);
		$v->add_non_missing_parameters('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
						'i', 'j', 'l', 'm', 'n', 'p');
		$v->add_non_null_parameters('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
						'm', 'n', 'p');
		$v->add_is_underage_parameters('i', 'j', 'l', 'under');
		$v->add_is_date_parameters('m', 'n', 'p', 'date');
		$v->add_is_email_parameters('c');
		$v->add_is_decimal_parameters('e');
		$v->add_is_integer_parameters('d');
		$v->add_is_password_parameters('f', 'g', 'password');
		$v->add_is_valid_length_parameters('f', 4, 8);
		$v->add_order_parameters('a', 'b', 'c', 'd', 'e', 'password', 'h',
						'date', 'under');
		$this->assertTrue($v->validate($result));
		$parameters = array(
				'a' => 'dsdasd', 'b' => 'dsdasd', 'c' => 'dsdasd@dsad.com',
				'd' => '23', 'e' => '45.34', 'f' => '123456', 'g' => '123456',
				'h' => 'dsadas', 'i' => '23', 'j' => '11', 'l' => '1987',
				'm' => '30', 'n' => '1', 'p' => '1999'
		);
		$v = new Validator($parameters);
		$v->add_non_missing_parameters('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
						'i', 'j', 'l', 'm', 'n', 'p');
		$v->add_non_null_parameters('a', 'b', 'c', 'd', 'f', 'g', 'h',
						'm', 'n', 'p');
		$v->add_is_underage_parameters('i', 'j', 'l', 'under');
		$v->add_is_date_parameters('m', 'n', 'p', 'date');
		$v->add_is_email_parameters('c');
		$v->add_is_decimal_parameters('e');
		$v->add_is_integer_parameters('d');
		$v->add_is_password_parameters('f', 'g', 'password');
		$v->add_is_valid_length_parameters('f', 4, 8);
		$v->add_order_parameters('a', 'b', 'c', 'd', 'e', 'password', 'h',
						'date', 'under');
		$this->assertTrue($v->validate($result));
		$parameters = array(
				'a' => 'dsdasd', 'b' => 'dsdasd', 'c' => 'dsdasd@dsad.com',
				'd' => '23', 'e' => '45.34', 'f' => "dsdanl", 'g' => null,
				'h' => 'dsadas', 'i' => '23', 'j' => '11', 'l' => '1987',
				'm' => '30', 'n' => '1', 'p' => '1999'
		);
		$v = new Validator($parameters);
		$v->add_non_missing_parameters('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
						'i', 'j', 'l', 'm', 'n', 'p');
		$v->add_non_null_parameters('a', 'b', 'c', 'd', 'h',
						'm', 'n', 'p','password');
		$v->add_is_underage_parameters('i', 'j', 'l', 'under');
		$v->add_is_date_parameters('m', 'n', 'p', 'date');
		$v->add_is_email_parameters('c');
		$v->add_is_decimal_parameters('e');
		$v->add_is_integer_parameters('d');
		$v->add_is_password_parameters('f', 'g', 'password');
		$v->add_is_valid_length_parameters('f', 4, 8);
		$v->add_order_parameters('a', 'b', 'c', 'd', 'e', 'password', 'h',
						'date', 'under');
		$this->assertTrue(!$v->validate($result));
		$parameters = array(
				'a' => 'dsdasd', 'b' => 'dsdasd', 'c' => 'dsdasd@dsad.com',
				'd' => '23', 'e' => '45.34', 'f' => '123456', 'g' => '123456',
				'h' => 'dsadas', 'i' => '23', 'j' => '11', 'l' => '1987',
				'm' => '30', 'n' => '1', 'p' => '1999'
		);
		$v = new Validator($parameters);
		$v->add_non_missing_parameters('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
						'i', 'j', 'l', 'm', 'n', 'p');
		$v->add_non_null_parameters('a', 'b', 'c', 'd', 'h',
						'm', 'n', 'p');
		$v->add_is_underage_parameters('i', 'j', 'l', 'under');
		$v->add_is_date_parameters('m', 'n', 'p', 'date');
		$v->add_is_email_parameters('c');
		$v->add_is_decimal_parameters('e');
		$v->add_is_integer_parameters('d');
		$v->add_is_password_parameters('f', 'g', 'password');
		$v->add_is_valid_length_parameters('f', 4, 8);
		$v->add_order_parameters('a', 'b', 'c', 'd', 'e', 'password', 'h',
						'date', 'under');
		$this->assertTrue($v->validate($result));
		print_r($result);
	}
}
