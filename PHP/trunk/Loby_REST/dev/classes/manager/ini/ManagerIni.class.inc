<?php
final class ManagerIni {

	private static $connection_params;
	private static $properties;
	private static $underage;
	private static $password_length;

	/**
	 * Static function that takes database connection parameters from manager.ini 
	 * file and return them as array.
	 * @return array 
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
	/**
	 * Static function that takes underage parameters from manager.ini 
	 * file and returns it.
	 * @return int 
	 */

	final static function get_underage() {
		if (empty(self::$underage)) {
			$params = self::get_manager_ini();
			self::$underage = $params['underage'];
		}
		return self::$underage;
	}
	/**
	 * Static function that takes password length parameters from manager.ini 
	 * file and returns them in array.
	 * @return array 
	 */

	final static function get_password_length() {
		if (empty(self::$password_length)) {
			$params = self::get_manager_ini();
			self::$password_length = array(
					'min' => $params['password_min'],
					'max' => $params['password_max']
			);
		}
		return self::$password_length;
	}

	private static function get_manager_ini() {
		if (empty(self::$properties)) {
			self::$properties = parse_ini_file("manager.ini");
		}
		return self::$properties;
	}
}
