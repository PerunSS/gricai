<?php

function __autoload($class) {
	$classes = array(
			'ManagerIni' => 'manager/ini/', 'Manager' => 'manager/',
			'Checkers' => 'utils/', 'Validator' => 'manager/'
	);
	if (isset($classes[$class])) {
		require_once $classes[$class] . $class . '.class.inc';
	}
}
