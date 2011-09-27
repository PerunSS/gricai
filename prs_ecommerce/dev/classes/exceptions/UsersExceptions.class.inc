<?php
class UsersExceptions extends Exception {
	private $messsage;

	function __construct($code) {
		$creator = array();
		switch ($code) {
			case Constants::CODE_WRONG_USERNAME:
				$creator["status"]="wrong";
				$this->message=json_encode($creator);
				break;
			case Constants::CODE_EXIST_USERNAME:
				$creator["status"]="wrong";
				$this->message=json_encode($creator);
				break;
		}
		parent::__construct($this->message, $code, null);
	}

	function getJSON() {
		return $this->message;
	}
}