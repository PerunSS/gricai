<?php
class Checkers {
	const MESSAGE_MISSING = 'missing_';
	const MESSAGE_NULL = 'empty_';
	const MESSAGE_NON_NUMBER = 'non_number_';
	const MESSAGE_NON_INTEGER = 'non_integer_';
	const MESSAGE_NON_EMAIL = 'non_email_';
	const MESSAGE_NON_DATE = 'non_date_';
	const MESSAGE_NON_UNDERAGE = 'non_underage_';
	const MESSAGE_NON_MATCH = 'non_match_';
	const MESSAGE_NON_VALID_LENGTH = 'non_valid_length_';
	const MESSAGE_INVALID_ORDER = 'non_invalid_order_';
	const MESSAGE_INVALID_MINIMUM = 'non_invalid_minimum_';
	const MESSAGE_INVALID_MAXIMUM = 'non_invalid_maximum_';
	/**
	 * Check if some of the parameters is null.
	 * @param array $parameters
	 * @param array $for_check
	 * @param array $errors
	 */

	final static function is_null($parameters, $for_check, &$errors = array()) {
		$e = count($errors);
		foreach ($for_check as $check) {
			if (array_key_exists($check, $parameters)) {
				$helper = trim($parameters[$check]);
				if (!preg_match("/[0-9]/", $helper)
						&& (is_null($helper) || $helper == "")) {
					$errors[$e] = Checkers::MESSAGE_NULL . $check;
					$e++;
				}
			}
		}
	}

	/**
	 * Check if parameter is number. 
	 * If fourth argument is true, then check if parameter is integer.
	 * Else check if parameter is decimal.
	 * @param array $parameters
	 * @param array $for_check
	 * @param array $errors
	 * @param boolean $integer
	 */

	final static function is_number($parameters, $for_check, &$errors = array(),
			$integer = false) {
		$e = count($errors);
		foreach ($for_check as $check) {
			if (array_key_exists($check, $parameters)
					&& self::if_nullable($parameters[$check])) {
				if (!is_numeric($parameters[$check])) {
					$errors[$e] = Checkers::MESSAGE_NON_NUMBER . $check;
					$e++;
				} elseif ($integer) {
					if (strpos($parameters[$check], '.') !== false) {
						$errors[$e] = Checkers::MESSAGE_NON_INTEGER . $check;
						$e++;
					}
				}
			}
		}
	}
	/**
	 * Check if parameter is valid date. 
	 * If fourth argument is true , then check if parameters is valid date and is underage.
	 * Else if it is false, or not exists, check only if parameters is valid date.
	 * @param array $parameters Array of parameters, will contain new date parameters if neccesairy.
	 * @param array $for_check Array of arrays, where inner arrays have parameters with keys:
	 * day, month, year, and optionaly, new_parameter, which will be key for new date parameter,
	 * if it's exists.
	 * @param array $errors
	 * @param boolean $underage
	 */

	final static function is_date(&$parameters, $for_check, &$errors = array(),
			$underage = false) {
		$e = count($errors);
		foreach ($for_check as $check) {
			if (array_key_exists($check['day'], $parameters)
					&& array_key_exists($check['month'], $parameters)
					&& array_key_exists($check['year'], $parameters)
					&& self::if_nullable($parameters[$check['day']],
							$parameters[$check['month']],
							$parameters[$check['year']])) {
				$new = $e;
				self::is_number($parameters,
						array(
							$check['day'], $check['month'], $check['year']
						), $errors, true);
				$e = count($errors);
				if ($new == $e) {
					if (!checkdate($parameters[$check['month']],
							$parameters[$check['day']],
							$parameters[$check['year']])) {
						$errors[$e] = Checkers::MESSAGE_NON_DATE
								. $check['day'];
						$e++;
						$errors[$e] = Checkers::MESSAGE_NON_DATE
								. $check['month'];
						$e++;
						$errors[$e] = Checkers::MESSAGE_NON_DATE
								. $check['year'];
						$e++;
					} elseif ($underage) {
						$helper_year = date("Y") - ManagerIni::get_underage();
						$int_year = intval($parameters[$check['year']]);
						if ($helper_year < $int_year) {
							$errors[$e] = Checkers::MESSAGE_NON_UNDERAGE
									. $check['year'];
							$e++;
						} elseif ($helper_year == $int_year) {
							$helper_month = date("n");
							$int_month = intval($parameters[$check['month']]);
							if ($helper_month < $int_month) {
								$errors[$e] = Checkers::MESSAGE_NON_UNDERAGE
										. $check['month'];
								$e++;
							} elseif ($helper_month == $int_month
									&& date("j")
											< intval($parameters[$check['day']])) {
								$errors[$e] = Checkers::MESSAGE_NON_UNDERAGE
										. $check['day'];
								$e++;
							}
						}
					}
				}
				if (isset($check['new_parameter'])) {
					if ($new == $e)
						$parameters[$check['new_parameter']] = date(
								ManagerIni::get_date_format(),
								mktime(0, 0, 0, $parameters[$check['month']],
										$parameters[$check['day']],
										$parameters[$check['year']]));
					else
						$parameters[$check['new_parameter']] = null;
				}
			}
		}

	}
	/**
	 * Check if parameter is valid password. 
	 * @param array $parameters Array of parameters, will contain new date parameters if neccesairy.
	 * @param array $for_check Array of arrays, where inner arrays have parameters with keys:
	 * password, confirm_password, and optionaly, new_parameter, which will be key for new password parameter,
	 * if it's exists.
	 * @param array $errors
	 */

	final static function is_password(&$parameters, $for_check,
			&$errors = array()) {
		$e = count($errors);
		foreach ($for_check as $check) {
			if (array_key_exists($check['password'], $parameters)
					&& array_key_exists($check['confirm_password'], $parameters)
					&& self::if_nullable($parameters[$check['password']],
							$parameters[$check['confirm_password']])) {
				$new = $e;
				if ($parameters[$check['password']]
						!= $parameters[$check['confirm_password']]) {
					$errors[$e] = Checkers::MESSAGE_NON_MATCH
							. $check['confirm_password'];
					$e++;
				}
			}
			if (isset($check['new_parameter'])) {
				if ($new == $e)
					$parameters[$check['new_parameter']] = $parameters[$check['password']];
				else
					$parameters[$check['new_parameter']] = null;
			}
		}
	}
	/**
	 * Check if parameter is valid email. 
	 * @param array $parameters
	 * @param array $for_check
	 * @param array $errors
	 */

	final static function is_email($parameters, $for_check, &$errors = array()) {
		$e = count($errors);
		foreach ($for_check as $check) {
			if (array_key_exists($check, $parameters)
					&& self::if_nullable($parameters[$check])) {
				if (!preg_match(
						"/^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/",
						$parameters[$check])) {
					$errors[$e] = Checkers::MESSAGE_NON_EMAIL . $check;
					$e++;
				}
			}
		}
	}

	/**
	 * Check if parameter is valid length. 
	 * @param array $parameters Array of parameters, will contain maximum or minimum parameters if neccesairy.
	 * @param array $for_check Array of arrays, where inner arrays have parameters with keys:
	 * parameter, and optionaly minimum and maximum.
	 * @param array $errors
	 */

	final static function is_valid_length(&$parameters, $for_check,
			&$errors = array()) {
		$e = count($errors);
		foreach ($for_check as $check) {
			if (array_key_exists($check['parameter'], $parameters)
					&& self::if_nullable($parameters[$check['parameter']])) {
				if (isset($check['minimum'])
						&& strlen($parameters[$check['parameter']])
								< $check['minimum']) {
					$errors[$e] = Checkers::MESSAGE_INVALID_MINIMUM
							. $check['parameter'];
					$e++;
				}
				if (isset($check['maximum'])
						&& strlen($parameters[$check['parameter']])
								> $check['maximum']) {
					$errors[$e] = Checkers::MESSAGE_INVALID_MAXIMUM
							. $check['parameter'];
					$e++;
				}
			}
		}
	}
	/**
	 * Set parameters order. 
	 * @param array $parameters Array of parameters that need to be reordered.
	 * @param array $orders Array with orders.
	 * @param array $new_order Reordered array.
	 * @param array $errors
	 */

	final static function set_order(&$parameters, $orders, &$new_order,
			&$errors = array()) {
		$new_order = array();
		$e = count($errors);
		$i = 0;
		foreach ($orders as $order) {
			if (array_key_exists($order, $parameters)) {
				$new_order[$i] = $parameters[$order];
				$i++;
			} else {
				$errors[$e] = Checkers::MESSAGE_INVALID_ORDER . $order;
				$e++;
			}
		}
	}

	private static function if_nullable() {
		foreach (func_get_args() as $value) {
			if (!isset($value) || empty($value))
				return false;
		}
		return true;
	}
}
