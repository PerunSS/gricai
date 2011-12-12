<?php
class ManagerIni {

	private static $connection_params;
	private static $properties;

	/**
	 * Static function that takes database connection parameters from manager.ini file and return them as array. 
	 */

	final static function get_connection_params() {
		if (empty(self::$connection_params)) {
			$params = self::get_manager_ini();
			self::$connection_params = array(
					'host' => $params['host'],
					'database' => $params['database'],
					'username' => $params['username'],
					'password' => $params['password']
			);
		}
		return self::$connection_params;
	}

	private static function get_manager_ini() {
		if (empty(self::$properties)) {
			self::$properties = parse_ini_file("manager.ini");
		}
		return self::$properties;
	}
}
