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
	public static function getStoredProcedure($name) {
		return new StoredProcedure($name);
	}

	public static function getSQLStatement() {
		return new SQLStatement();
	}

	public function setValidator($validator) {
		$this->validator = $validator;
	}

	public function setFormater($formater) {
		$this->formater = $formater;
	}
	
	public function setManager($manager){
		$this->manager = $manager;
	}

	public function getValidator() {
		return $this->validator;
	}

	public function getFormater() {
		return $this->formater;
	}
	
	public function getManager(){
		return $this->manager;
	}
}
