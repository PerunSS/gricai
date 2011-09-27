<?php
class SecurityExceptions extends Exception {

	private $messsage;

	function __construct($code, $params) {
		$creator = array();
		switch ($code) {
			case Constants::CODE_MISSING_PARAM:
				$creator["status"]="error";
				$creator["errors"]=$params;
				$this->message=json_encode($creator);
				break;
			case Constants::CODE_TIMEOUT:
				$creator["status"]="timeout";
				$this->message = json_encode($creator);
				break;
		}
		parent::__construct($this->message, $code, null);
	}

	function getJSON() {
		return $this->message;
	}
}