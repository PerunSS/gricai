<?php

function __autoload($class) {
	$classes = array(
			//ManagerIni
			'ManagerIni' => 'manager/ini/', 
			//Manager
			'Manager' => 'manager/', 
			//Validator
			'Validator' => 'manager/', 
			//Checkers
			'Checkers' => 'utils/', 
			//Modals
			'User' => 'model/', 'Modal' => 'model/', 'Error' => 'model/',
			//Formaters
			'FormatSingleRowObject' => 'manager/formater/',
			'Formater' => 'manager/'
	);
	if (isset($classes[$class])) {
		require_once $classes[$class] . $class . '.class.inc';
	}
}
