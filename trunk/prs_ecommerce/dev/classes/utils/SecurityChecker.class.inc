<?php
class SecurityChecker {

	public static $missing_array= array(
	"korisnik_login"=>array("username", "password")
	);

	public static $empty_array= array(
	"korisnik_login"=>array("username", "password")
	);

	static function requestGET($array) {
		$errors=array();
		$i=0;
		foreach (SecurityChecker::$missing_array[$array] as $value){
			if (!isset($_GET[$value])){
				$errors[$i]=array('missing'=>$value);
				$i++;
			}
		}
		foreach (SecurityChecker::$empty_array[$array] as $value){
			if (isset($_GET[$value]) && empty($_GET[$value])){
				$errors[$i]=array('empty'=>$value);
				$i++;
			}
		}
		return $errors;
	}

	static function requestPOST($array) {
		$errors=array();
		$i=0;
		foreach (SecurityChecker::$missing_array[$array] as $value){
			if (!isset($_POST[$value])){
				$errors[$i]=array('missing'=>$value);
				$i++;
			}
		}
		foreach (SecurityChecker::$empty_array[$array] as $value){
			if (isset($_POST[$value]) && empty($_POST[$value])){
				$errors[$i]=array('empty'=>$value);
				$i++;
			}
		}
		return $errors;
	}

	static function sessionStart(){
		if (!isset($_SESSION)){
			session_start();
		}
	}

	static function isLogged() {
		if (isset($_SESSION['user'])) return true;
		return false;
	}
}
?>