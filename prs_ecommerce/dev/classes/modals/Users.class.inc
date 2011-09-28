<?php
class Users {

	function __construct() {
	}

	function login() {
		$valid=SecurityChecker::requestPOST("korisnik_login");
		if (count($valid)>0) return $valid;
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
}
?>