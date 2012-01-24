<?php
class Error
		extends Modal {
	const CODE_INVALID_PARAMETERS = 'invalid_parameters';

	public $code;
	public $codes;

	final static function get_error($code, $codes = array()) {
		$error = new Error();
		$error->code = $code;
		$error->codes = $codes;
		return $error;
	}

	public function to_json() {}
}
