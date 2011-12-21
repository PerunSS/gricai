<?php
class Error
		extends Modal {
	const CODE_INVALID_PARAMETERS = 'invalid_parameters';
	const CODE_FORMAT_SINGLE_ROW_OBJECT_EMPTY = 'single_row_is_empty';
	const CODE_FORMAT_SINGLE_ROW_OBJECT_MULTY = 'single_row_is_multy_row';

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
