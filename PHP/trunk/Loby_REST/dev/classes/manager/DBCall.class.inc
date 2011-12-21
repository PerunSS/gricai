<?php
abstract class DBCall {

	/**
	 * @var Validator
	 */

	protected $validator;
	/**
	 * @var Formater
	 */
	protected $formater;
	/**
	 * @var Manager
	 */
	protected $manager;
	/**
	 * @var array
	 */
	protected $parameters;

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

	public function set_formater($formater) {
		$this->formater = $formater;
	}

	public function get_validator() {
		if ($this->validator == null) {
			$this->validator = new Validator($this->parameters);
		}
		return $this->validator;
	}

	public function get_formater() {
		return $this->formater;
	}

	public function set_parameters($parameters) {
		$this->parameters = $parameters;
	}

	public function get_manager() {
		if ($this->manager == null) {
			$this->manager = new Manager();
		}
		return $this->manager;
	}
}
