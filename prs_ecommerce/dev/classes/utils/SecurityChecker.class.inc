<?php
class SecurityChecker {

	public static $missing_array= array(
	"korisnik_login"=>array("username", "password")
	);

	public static $empty_array= array(
	"korisnik_login"=>array("username", "password")
	);
	
	public static $alpha_array = array(
	"korisnik_login"=>array()
	);

	public static $numeric_array = array(
	"korisnik_login"=>array()
	);

	public static $date_array = array(
	"korisnik_login"=>array()
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