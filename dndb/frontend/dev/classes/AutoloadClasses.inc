<?php
/**
 *
 * Load all classes.
 * @param String $class_name Name of the class.
 */
function __autoload($class_name) {
	$class_root = array(
		"Field"=>"modals/",
		"Hero"=>"modals/",
		"Map"=>"modals/",
		"Communicator"=>"utils/",
	);

	if (isset($class_root[$class_name])){
		require_once $class_root[$class_name] . $class_name . '.class.inc';
	}
}
?>
