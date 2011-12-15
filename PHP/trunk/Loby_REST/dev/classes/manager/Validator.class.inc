<?php
class Validator {
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

	private $parameters = array();
	private $non_missing = array();
	private $non_null = array();
	private $is_integer = array();
	private $is_decimal = array();
	private $is_email = array();
	private $is_date = array();
	private $is_underage = array();
	private $is_password = array();
	private $is_valid_length = array();
	private $parameters_order = array();
	private $errors = array();

	public function __construct($parameters) {
		$this->parameters = $parameters;
	}

	public function validate(&$result = array()) {
		$result = array();
		$this->errors = array();
		$this->validate_non_missing();
		$this->validate_is_integer();
		$this->validate_is_decimal();
		$this->validate_is_date();
		$this->validate_is_email();
		$this->validate_is_password();
		$this->validate_is_underage();
		$this->validate_is_valid_length();
		$this->validate_non_null();
		if (count($this->errors)) {
			$result = $this->errors;
			return false;
		} else {
			if ($this->set_order($order)) {
				$result = $order;
				return true;
			} else {
				$result = $this->errors;
				return false;
			}
		}
	}

	private function validate_non_missing() {
		$e = count($this->errors);
		foreach ($this->non_missing as $value) {
			if (!array_key_exists($value, $this->parameters)) {
				$this->errors[$e] = Validator::MESSAGE_MISSING . $value;
				$e++;
			}
		}
	}

	private function validate_non_null() {
		$e = count($this->errors);
		foreach ($this->non_null as $value) {
			if (array_key_exists($value,$this->parameters)
					&& Checkers::is_null($this->parameters[$value])) {
				$this->errors[$e] = Validator::MESSAGE_NULL . $value;
				$e++;
			}
		}
	}

	private function validate_is_integer() {
		$e = count($this->errors);
		foreach ($this->is_integer as $value) {
			if ($this->if_nullable($this->parameters[$value])
					&& !Checkers::is_number($this->parameters[$value], $error)) {
				if ($error == Checkers::ERROR_CODE_NON_NUMBER)
					$this->errors[$e] = Validator::MESSAGE_NON_NUMBER . $value;
				else
					$this->errors[$e] = Validator::MESSAGE_NON_INTEGER . $value;
				$e++;
			}
		}
	}

	private function validate_is_decimal() {
		$e = count($this->errors);
		foreach ($this->is_decimal as $value) {
			if ($this->if_nullable($this->parameters[$value])
					&& !Checkers::is_number($this->parameters[$value], $error,
							false)) {
				$this->errors[$e] = Validator::MESSAGE_NON_NUMBER . $value;
				$e++;
			}
		}
	}

	private function validate_is_email() {
		$e = count($this->errors);
		foreach ($this->is_email as $value) {
			if ($this->if_nullable($this->parameters[$value])
					&& !Checkers::is_email($this->parameters[$value])) {
				$this->errors[$e] = Validator::MESSAGE_NON_EMAIL . $value;
				$e++;
			}
		}
	}

	private function validate_is_date() {
		$e = count($this->errors);
		foreach ($this->is_date as $value) {
			if ($this->if_nullable($this->parameters[$value['day']],
							$this->parameters[$value['month']],
							$this->parameters[$value['year']])) {
				if (!Checkers::is_date($this->parameters[$value['day']],
						$this->parameters[$value['month']],
						$this->parameters[$value['year']])) {
					$this->errors[$e] = Validator::MESSAGE_NON_DATE
							. $value['day'];
					$e++;
					$this->errors[$e] = Validator::MESSAGE_NON_DATE
							. $value['month'];
					$e++;
					$this->errors[$e] = Validator::MESSAGE_NON_DATE
							. $value['year'];
					$e++;
				} elseif (isset($value['new_parameter']))
					$this->parameters[$value['new_parameter']] = date(
							ManagerIni::get_date_format(),
							mktime(0, 0, 0, $this->parameters[$value['month']],
									$this->parameters[$value['day']],
									$this->parameters[$value['year']]));
			} elseif (isset($value['new_parameter'])) {
				$this->parameters[$value['new_parameter']] = null;
			}
		}
	}

	private function validate_is_underage() {
		$e = count($this->errors);
		foreach ($this->is_underage as $value) {
			if ($this->if_nullable($this->parameters[$value['day']],
							$this->parameters[$value['month']],
							$this->parameters[$value['year']])) {
				if (!Checkers::is_date($this->parameters[$value['day']],
						$this->parameters[$value['month']],
						$this->parameters[$value['year']], $error, true)) {
					if ($error == Checkers::ERROR_CODE_UNDERAGE) {
						$this->errors[$e] = Validator::MESSAGE_NON_UNDERAGE
								. $value['day'];
						$e++;
						$this->errors[$e] = Validator::MESSAGE_NON_UNDERAGE
								. $value['month'];
						$e++;
						$this->errors[$e] = Validator::MESSAGE_NON_UNDERAGE
								. $value['year'];
						$e++;
					} else {
						$this->errors[$e] = Validator::MESSAGE_NON_DATE
								. $value['day'];
						$e++;
						$this->errors[$e] = Validator::MESSAGE_NON_DATE
								. $value['month'];
						$e++;
						$this->errors[$e] = Validator::MESSAGE_NON_DATE
								. $value['year'];
						$e++;
					}
				} elseif (isset($value['new_parameter']))
					$this->parameters[$value['new_parameter']] = date(
							ManagerIni::get_date_format(),
							mktime(0, 0, 0, $this->parameters[$value['month']],
									$this->parameters[$value['day']],
									$this->parameters[$value['year']]));
			} elseif (isset($value['new_parameter'])) {
				$this->parameters[$value['new_parameter']] = null;
			}
		}
	}

	private function validate_is_password() {
		$e = count($this->errors);
		foreach ($this->is_password as $value) {
			if ($this->if_nullable($this->parameters[$value['password']],
							$this->parameters[$value['confirm_password']])) {
				if (!Checkers::is_password(
						$this->parameters[$value['password']],
						$this->parameters[$value['confirm_password']])) {
					$this->errors[$e] = Validator::MESSAGE_NON_MATCH
							. $value['confirm_password'];
					$e++;
				} elseif (isset($value['new_parameter']))
					$this->parameters[$value['new_parameter']] = $this->parameters[$value['password']];
			} elseif (isset($value['new_parameter'])) {
				$this->parameters[$value['new_parameter']] = null;
			}
		}
	}

	private function validate_is_valid_length() {
		$e = count($this->errors);
		foreach ($this->is_valid_length as $value) {
			if ($this->if_nullable($this->parameters[$value['parameter']])
					&& !Checkers::is_valid_length(
							$this->parameters[$value['parameter']],
							$value['minimum'], $value['maximum'])) {
				$this->errors[$e] = Validator::MESSAGE_NON_VALID_LENGTH
						. $value['parameter'];
				$e++;
			}
		}
	}

	private function set_order(&$order) {
		$order = array();
		$i = 0;
		$e = count($this->errors);
		$valid = true;
		foreach ($this->parameters_order as $value) {
			if (array_key_exists($value, $this->parameters)) {
				$order[$i] = $this->parameters[$value];
				$i++;
			} else {
				$this->errors[$e] = Validator::MESSAGE_INVALID_ORDER . $value;
				$e++;
				$valid = false;
			}
		}
		return $valid;
	}

	private function if_nullable() {
		foreach (func_get_args() as $value) {
			if (!isset($value) || empty($value))
				return false;
		}
		return true;
	}

	public function add_non_missing_parameters() {
		foreach (func_get_args() as $value) {
			$this->non_missing[count($this->non_missing)] = $value;
		}
	}

	public function clear_non_missing_parameters() {
		$this->non_missing = array();
	}

	public function add_non_null_parameters() {
		foreach (func_get_args() as $value) {
			$this->non_null[count($this->non_null)] = $value;
		}
	}

	public function clear_non_null_parameters() {
		$this->non_null = array();
	}

	public function add_is_integer_parameters() {
		foreach (func_get_args() as $value) {
			$this->is_integer[count($this->is_integer)] = $value;
		}
	}

	public function clear_is_integer_parameters() {
		$this->is_integer = array();
	}

	public function add_is_decimal_parameters() {
		foreach (func_get_args() as $value) {
			$this->is_decimal[count($this->is_decimal)] = $value;
		}
	}

	public function clear_is_decimal_parameters() {
		$this->is_decimal = array();
	}

	public function add_is_email_parameters() {
		foreach (func_get_args() as $value) {
			$this->is_email[count($this->is_email)] = $value;
		}
	}

	public function clear_is_email_parameters() {
		$this->is_email = array();
	}

	public function add_is_date_parameters($day, $month, $year,
			$new_parameter = NULL) {
		$d = count($this->is_date);
		$this->is_date[$d] = array(
			'day' => $day, 'month' => $month, 'year' => $year
		);
		if (!Checkers::is_null($new_parameter))
			$this->is_date[$d]['new_parameter'] = $new_parameter;
	}

	public function clear_is_date_parameters() {
		$this->is_date = array();
	}

	public function add_is_underage_parameters($day, $month, $year,
			$new_parameter = NULL) {
		$u = count($this->is_underage);
		$this->is_underage[$u] = array(
			'day' => $day, 'month' => $month, 'year' => $year
		);
		if (!Checkers::is_null($new_parameter))
			$this->is_underage[$u]['new_parameter'] = $new_parameter;

	}

	public function clear_is_underage_parameters() {
		$this->is_underage = array();
	}

	public function add_is_password_parameters($password, $confirm_password,
			$new_parameter = NULL) {
		$p = count($this->is_password);
		$this->is_password[$p] = array(
			'password' => $password, 'confirm_password' => $confirm_password
		);
		if (!Checkers::is_null($new_parameter))
			$this->is_password[$p]['new_parameter'] = $new_parameter;
	}

	public function clear_is_password_parameters() {
		$this->is_password = array();
	}

	public function add_is_valid_length_parameters($parameter, $minimum = NULL,
			$maximum = NULL) {
		$this->is_valid_length[count($this->is_valid_length)] = array(
				'parameter' => $parameter, 'minimum' => $minimum,
				'maximum' => $maximum
		);
	}

	public function clear_is_valid_length_parameters() {
		$this->is_valid_length = array();
	}

	public function add_order_parameters() {
		foreach (func_get_args() as $value) {
			$this->parameters_order[count($this->parameters_order)] = $value;
		}
	}

	public function clear_order_parameters() {
		$this->parameters_order = array();
	}
}
