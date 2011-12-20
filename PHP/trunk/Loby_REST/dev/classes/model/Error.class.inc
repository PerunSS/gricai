<?php
class Error
		extends Modal {
	const FORMAT_SINGLE_ROW_OBJECT_EMPTY = 'single_row_is_empty';

	public $code;
	public $codes;

	function to_json() {}
}
