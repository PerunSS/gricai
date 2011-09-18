<?php
/**
 * 
 * Load all classes.
 * @param String $class_name Name of the class.
 */
function __autoload($class_name)
{
	$path="";
	if ($class_name=="GetJSON")
		$path="callapi/";
	if ($class_name!="Memcache"/* && $class_name!="DOMDocument"*/)
		include($path . $class_name . '.class.inc');
}
?>