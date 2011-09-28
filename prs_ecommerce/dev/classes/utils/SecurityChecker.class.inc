<?php
class SecurityChecker {

	public static $missing_array= array(
	"user_login"=>array("username", "password"),
	"individual_register"=>array("username", "password","confirm", "email", "firstname", "lastname", "jmbg", "telefon"),
	"entity_register"=>array("username", "password","confirm", "email", "name", "address", "city", "pib","maticni","telefon")
	);

	public static $empty_array= array(
	"user_login"=>array("username", "password"),
	"individual_register"=>array("username", "password","confirm", "email", "firstname", "lastname", "jmbg", "telefon"),
	"entity_register"=>array("username", "password","confirm", "email", "name", "address", "city", "pib","maticni","telefon")
	);

	public static $alpha_array = array(
	"user_login"=>array(),
	"individual_register"=>array("username","firstname", "lastname"),
	"entity_register"=>array("username", "city")
	);

	public static $numeric_array = array(
	"user_login"=>array(),
	"individual_register"=>array("jmbg", "telefon"),
	"entity_register"=>array("pib","maticni","telefon")
	);

	public static $date_array = array(
	"user_login"=>array(),
	"individual_register"=>array(),
	"entity_register"=>array()
	);
	public static $email_array = array(
	"user_login"=>array(),
	"individual_register"=>array("email"),
	"entity_register"=>array("email")
	);

	public static $alphanumeric_array = array(
	"user_login"=>array(),
	"individual_register"=>array(),
	"entity_register"=>array("address")
	);

	static function requestGET($array) {
		$errors=array();
		$i=0;
		foreach (SecurityChecker::$missing_array[$array] as $value){
			if (!isset($_GET[$value])){
				$errors[$i]=array('error'=>'missing_'.$value);
				$i++;
			}
		}
		foreach (SecurityChecker::$empty_array[$array] as $value){
			if (isset($_GET[$value]) && empty($_GET[$value])){
				$errors[$i]=array('error'=>'empty_'.$value);
				$i++;
			}
			elseif(preg_match("/^[ ]+$/", $_GET[$value])) {
				$errors[$i]=array('error'=>'empty_'.$value);
				$i++;
			}
		}
		foreach (SecurityChecker::$alpha_array[$array] as $value){
			if (isset($_GET[$value]) && !empty($_GET[$value])){
				if(!preg_match("/^[a-zA-Z ]+$/", $_GET[$value])) {
					$errors[$i]=array('error'=>'alpha_'.$value);
					$i++;
				}
			}
		}
		foreach (SecurityChecker::$numeric_array[$array] as $value){
			if (isset($_GET[$value]) && !empty($_GET[$value])){
				if(!preg_match("/^[0-9]+$/", $_GET[$value])) {
					$errors[$i]=array('error'=>'numeric_'.$value);
					$i++;
				}
			}
		}
		foreach (SecurityChecker::$email_array[$array] as $value){
			if (isset($_GET[$value]) && !empty($_GET[$value])){
				if(!preg_match("/^([A-Za-z0-9_\-\.+])+\@([A-Za-z0-9_\-\.+])+\.([A-Za-z]{2,4})$/", $_GET[$value])) {
					$errors[$i]=array('error'=>'invalid_'.$value);
					$i++;
				}
			}
		}
		foreach (SecurityChecker::$alphanumeric_array[$array] as $value){
			if (isset($_GET[$value]) && !empty($_GET[$value])){
				if(!preg_match("/^[a-zA-Z0-9 ]+$/", $_GET[$value])) {
					$errors[$i]=array('error'=>'alphanumeric_'.$value);
					$i++;
				}
			}
		}
		foreach (SecurityChecker::$date_array[$array] as $date){
			if (isset($_GET[$date[0]]) && isset($_GET[$date[1]]) && isset($_GET[$date[2]])){
				if (empty($_GET[$date[0]])){
					if (!empty($_GET[$date[1]]) || !empty($_GET[$date[2]])){
						$errors[$i]=array('error'=>'wrong_'.$date[3]);
						$i++;
					}
				}
				elseif (empty($_GET[$date[1]])){
					if (!empty($_GET[$date[0]]) || !empty($_GET[$date[2]])){
						$errors[$i]=array('error'=>'wrong_'.$date[3]);
						$i++;
					}
				}
				elseif (empty($_GET[$date[2]])){
					if (!empty($_GET[$date[1]]) || !empty($_GET[$date[0]])){
						$errors[$i]=array('error'=>'wrong_'.$date[3]);
						$i++;
					}
				}
			}
		}
		return $errors;
	}

	static function requestPOST($array) {
		$errors=array();
		$i=0;
		foreach (SecurityChecker::$missing_array[$array] as $value){
			if (!isset($_POST[$value])){
				$errors[$i]=array('error'=>'missing_'.$value);
				$i++;
			}
		}
		foreach (SecurityChecker::$empty_array[$array] as $value){
			if (isset($_POST[$value]) && empty($_POST[$value])){
				$errors[$i]=array('error'=>'empty_'.$value);
				$i++;
			}
			elseif(preg_match("/^[ ]+$/", $_POST[$value])) {
				$errors[$i]=array('error'=>'empty_'.$value);
				$i++;
			}
		}
		foreach (SecurityChecker::$alpha_array[$array] as $value){
			if (isset($_POST[$value]) && !empty($_POST[$value])){
				if(!preg_match("/^[a-zA-Z ]+$/", $_POST[$value])) {
					$errors[$i]=array('error'=>'alpha_'.$value);
					$i++;
				}
			}
		}
		foreach (SecurityChecker::$numeric_array[$array] as $value){
			if (isset($_POST[$value]) && !empty($_POST[$value])){
				if(!preg_match("/^[0-9]+$/", $_POST[$value])) {
					$errors[$i]=array('error'=>'numeric_'.$value);
					$i++;
				}
			}
		}
		foreach (SecurityChecker::$email_array[$array] as $value){
			if (isset($_POST[$value]) && !empty($_POST[$value])){
				if(!preg_match("/^([A-Za-z0-9_\-\.+])+\@([A-Za-z0-9_\-\.+])+\.([A-Za-z]{2,4})$/", $_POST[$value])) {
					$errors[$i]=array('error'=>'invalid_'.$value);
					$i++;
				}
			}
		}
		foreach (SecurityChecker::$alphanumeric_array[$array] as $value){
			if (isset($_POST[$value]) && !empty($_POST[$value])){
				if(!preg_match("/^[a-zA-Z0-9 ]+$/", $_POST[$value])) {
					$errors[$i]=array('error'=>'alphanumeric_'.$value);
					$i++;
				}
			}
		}
		foreach (SecurityChecker::$date_array[$array] as $date){
			if (isset($_POST[$date[0]]) && isset($_POST[$date[1]]) && isset($_POST[$date[2]])){
				if (empty($_POST[$date[0]])){
					if (!empty($_POST[$date[1]]) || !empty($_POST[$date[2]])){
						$errors[$i]=array('error'=>'wrong_'.$date[3]);
						$i++;
					}
				}
				elseif (empty($_POST[$date[1]])){
					if (!empty($_POST[$date[0]]) || !empty($_POST[$date[2]])){
						$errors[$i]=array('error'=>'wrong_'.$date[3]);
						$i++;
					}
				}
				elseif (empty($_POST[$date[2]])){
					if (!empty($_POST[$date[1]]) || !empty($_POST[$date[0]])){
						$errors[$i]=array('error'=>'wrong_'.$date[3]);
						$i++;
					}
				}
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