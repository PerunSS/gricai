<?php
abstract class DBCall {

	/**
	 * @var Validator
	 */

	private $validator;
	/**
	 * @var Formater
	 */
	private $formater;
	/**
	 * @var Manager
	 */
	private $manager;

	public abstract function execute();

	/**
	 * @param String $name
	 * @return StoredProcedure
	 */

	public static function get_stored_procedure($name) {
		return new StoredProcedure($name);
	}

	public static function get_sql_statement() {
		return new SQLStatement();
	}

	public function set_validator($validator) {
		$this->validator = $validator;
	}

	public function set_formater($formater) {
		$this->formater = $formater;
	}

	public function set_manager($manager) {
		$this->manager = $manager;
	}

	public function get_validator() {
		return $this->validator;
	}

	public function get_formater() {
		return $this->formater;
	}

	public function get_manager() {
		return $this->manager;
	}
}
