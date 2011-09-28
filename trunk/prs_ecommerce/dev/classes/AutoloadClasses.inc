<?php
/**
 *
 * Load all classes.
 * @param String $class_name Name of the class.
 */
function __autoload($class_name)
{
	$class_root = array(
	//utils
	"DBManager"=>"utils/", "SecurityChecker"=>"utils/",
	//modals
	"Users"=>"modals/",
	//Admin
	"Admin"=>"admin/",
	//constants
	"Constants"=>"constants/",
	//exceptions
	"SecurityExceptions"=>"exceptions/","UsersExceptions"=>"exceptions/"
	);

	if (isset($class_root[$class_name])){
		include($class_root[$class_name] . $class_name . '.class.inc');
	}
}
?>