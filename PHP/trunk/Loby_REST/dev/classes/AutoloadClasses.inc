<?php

function __autoload($class) {
	$classes = array(
			'ManagerIni' => 'manager/ini/', 'Manager' => 'manager/',
			'Checkers' => 'utils/'
	);
	if (isset($classes[$class])) {
		require_once $classes[$class] . $class . '.class.inc';
	}
}
