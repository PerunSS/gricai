<?php
class Users {

	function __construct() {
	}

	function login() {
		$valid=SecurityChecker::requestPOST("user_login");
		if (count($valid)>0) throw new SecurityExceptions(Constants::CODE_MISSING_PARAM, $valid);
		$db = new DBManager();
		$login= $db->callProcedure("korisnik_login", array(0=>$_POST['username'],1=>$_POST['password']));
		if (!is_array($login)) throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
		else if (count($login)==0) throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
		else if (!is_array($login[0])) throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
		else if (count($login[0])==0) throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
		else if (isset($login[0]['error'])){
			if ($login[0]['error']=='wrong') throw new UsersExceptions(Constants::CODE_WRONG_USERNAME);
			elseif ($login[0]['error']=='not_activated') throw new UsersExceptions(Constants::CODE_NOT_ACTIVATED);
			elseif ($login[0]['error']=='banned') throw new UsersExceptions(Constants::CODE_BANNED);
			else  throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
		}
		else {
			if (!isset($login[0]['ime']) && !isset($login[0]['ime_firme'])) throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
			else {
				$user = array();
				foreach ($login[0] as $key=>$value){;
				if (!is_int($key)){
					$user[$key]=$value;
				}
				}
				$creator=array("status" => "success", "user" => $user);
				return json_encode($creator);
			}
		}
	}

	function individual_register() {
		$valid=SecurityChecker::requestPOST("individual_register");
		if (count($valid)>0)  throw new SecurityExceptions(Constants::CODE_MISSING_PARAM, $valid);
		if ($_POST['password']!=$_POST['confirm']) throw new UsersExceptions(Constants::CODE_WRONG_CONFIRM_PASSWORD);
		$db = new DBManager();
		$register= $db->callProcedure("fizicko_lice_register", array(
		0=>$_POST['username'],1=>$_POST['password'],2=>$_POST['email'],
		3=>$_POST['firstname'],4=>$_POST['lastname'],5=>$_POST['jmbg'],6=>$_POST['telefon'])
		);
		if (!is_array($register)) throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
		else if (count($register)==0) throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
		else if (!is_array($register[0])) throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
		else if (count($register[0])==0) throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
		else if (isset($register[0]['error'])){
			if ($register[0]['error']=='exists') throw new UsersExceptions(Constants::CODE_EXIST_USER);
			else  throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
		}
		else {
			if (!isset($register[0]['status']) || !isset($register[0]['code']) || !isset($register[0]['email']) || !isset($register[0]['id']))
			throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
			else {
				$creator=array("status" => "success", "code" => $register[0]['code'], "email" => $register[0]['email'], "id" => $register[0]['id']);
				return json_encode($creator);
			}
		}
	}

	function entity_register() {
		$valid=SecurityChecker::requestPOST("entity_register");
		if (count($valid)>0)  throw new SecurityExceptions(Constants::CODE_MISSING_PARAM, $valid);
		if ($_POST['password']!=$_POST['confirm']) throw new UsersExceptions(Constants::CODE_WRONG_CONFIRM_PASSWORD);
		$db = new DBManager();
		$register= $db->callProcedure("pravno_lice_register", array(
		0=>$_POST['username'],1=>$_POST['password'],2=>$_POST['email'],
		3=>$_POST['name'],4=>$_POST['address'],5=>$_POST['city'],6=>$_POST['pib'],
		7=>$_POST['maticni'],8=>$_POST['telefon'])
		);
		if (!is_array($register)) throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
		else if (count($register)==0) throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
		else if (!is_array($register[0])) throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
		else if (count($register[0])==0) throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
		else if (isset($register[0]['error'])){
			if ($register[0]['error']=='exists') throw new UsersExceptions(Constants::CODE_EXIST_USER);
			else  throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
		}
		else {
			if (!isset($register[0]['status']) || !isset($register[0]['code']) || !isset($register[0]['email']) || !isset($register[0]['id']))
			throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
			else {
				$creator=array("status" => "success", "code" => $register[0]['code'], "email" => $register[0]['email'], "id" => $register[0]['id']);
				return json_encode($creator);
			}
		}
	}

	function buy() {
		if (!isset($_SESSION['cart'])) throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
		$cart="";
		$total=0;
		foreach ($_SESSION['cart'] as $key=>$value){
			$pom = $value['value'] * $value['currency'];
			$cart=$cart . $key .";" . $pom . ";" . $value['value'] . ";";
			$total+=$pom;
		}
		if (!isset($_SESSION['user'])) throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
		$user = json_decode($_SESSION['user']);
		$db = new DBManager();
		$buy= $db->callProcedure("kupi", array(0=>$user->id, 1=>$total, 2=>$cart));
		if (!is_array($buy)) throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
		else if (count($buy)==0) throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
		else if (!is_array($buy[0])) throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
		else if (count($buy[0])==0) throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
		else {
			if (!isset($buy[0]['status']))	throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
			if ($buy[0]['status']!="success")	throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
			else {
				$creator=array("status" => "success");
				return json_encode($creator);
			}
		}
	}
}
?>