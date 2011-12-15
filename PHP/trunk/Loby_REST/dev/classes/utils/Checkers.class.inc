<?php
class Checkers {
	const ERROR_CODE_NULL = 1000;
	const ERROR_CODE_NON_NUMBER = 1001;
	const ERROR_CODE_NON_INTEGER = 1002;
	const ERROR_CODE_INVALID_DATE = 1003;
	const ERROR_CODE_UNDERAGE = 1004;
	const ERROR_CODE_NOT_MATCH = 1005;
	const ERROR_CODE_WRONG_LENGTH = 1006;
	const ERROR_CODE_INVALID_EMAIL = 1007;
	const ERROR_CODE_INVALID_MAXIMUM = 1008;
	const ERROR_CODE_INVALID_MINIMUM = 1009;
	/**
	 * Check if parameter is null.
	 * @param Object $param
	 * @param int $error_code
	 */

	final static function is_null($param, &$error_code = 0) {
		$helper = trim($param);
		if (!preg_match("/[0-9]/", $helper)) {
			if (is_null($helper) || $helper == "") {
				$error_code = Checkers::ERROR_CODE_NULL;
				return true;
			}
		}
		return false;
	}
	/**
	 * Check if parameter is number. 
	 * If second argument is true or not exists, then check if parameter is integer.
	 * Else check if parameter is decimal.
	 * @param Object $param
	 * @param int $error_code
	 * @param boolean $integer
	 */

	final static function is_number($param, &$error_code = 0, $integer = true) {
		if (!is_numeric($param)) {
			$error_code = Checkers::ERROR_CODE_NON_NUMBER;
			return false;
		}
		if ($integer) {
			if (strpos($param, '.') !== false) {
				$error_code = Checkers::ERROR_CODE_NON_INTEGER;
				return false;
			}
		}
		return true;

	}
	/**
	 * Check if parameter is valid date. 
	 * If fourth argument is true , then check if parameters is valid date and is underage.
	 * Else if it is false, or not exists, check only if parameters is valid date.
	 * @param int $day
	 * @param int $month
	 * @param int $year
	 * @param int $error_code
	 * @param boolean $underage
	 */

	final static function is_date($day, $month, $year, &$error_code = 0,
			$underage = false) {
		if (!self::is_number($day) || !self::is_number($month)
				|| !self::is_number($year)) {
			$error_code = Checkers::ERROR_CODE_NON_INTEGER;
			return false;
		}
		if (!checkdate($month, $day, $year)) {
			$error_code = Checkers::ERROR_CODE_INVALID_DATE;
			return false;
		}
		if ($underage) {
			$helper_year = date("Y") - ManagerIni::get_underage();
			$int_year = intval($year);
			if ($helper_year < $int_year) {
				$error_code = Checkers::ERROR_CODE_UNDERAGE;
				return false;
			} elseif ($helper_year == $int_year) {
				$helper_month = date("n");
				$int_month = intval($month);
				if ($helper_month < $int_month) {
					$error_code = Checkers::ERROR_CODE_UNDERAGE;
					return false;
				} elseif ($helper_month == $int_month
						&& date("j") < intval($day)) {
					$error_code = Checkers::ERROR_CODE_UNDERAGE;
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * Check if parameters is valid password. 
	 * @param string $password
	 * @param string $confirm_password
	 * @param int $error_code
	 */

	final static function is_password($password, $confirm_password,
			&$error_code = 0) {
		if ($password != $confirm_password) {
			$error_code = Checkers::ERROR_CODE_NOT_MATCH;
			return false;
		}
		return true;
	}
	/**
	 * Check if parameter is valid email address. 
	 * @param string $email
	 * @param int $error_code
	 */

	final static function is_email($email, &$error_code = 0) {
		if (!preg_match("/^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/",
				$email)) {
			$error_code = Checkers::ERROR_CODE_INVALID_EMAIL;
			return false;
		}
		return true;
	}

	/**
	 * Check if parameter has valid length defined by maximum and minimum. 
	 * If maximum or minimum is NULL, minimum or maximum will not be validated.
	 * @param string $param
	 * @param int $minimum
	 * @param int $maximum
	 * @param int $error_code
	 */

	final static function is_valid_length($param, $minimum = NULL,
			$maximum = NULL, &$error_code = 0) {
		if (!Checkers::is_null($maximum) && strlen($param) > $maximum) {
			$error_code = Checkers::ERROR_CODE_INVALID_MAXIMUM;
			return false;
		}
		if (!Checkers::is_null($minimum) && strlen($param) < $minimum) {
			$error_code = Checkers::ERROR_CODE_INVALID_MINIMUM;
			return false;
		}
		return true;
	}
}
