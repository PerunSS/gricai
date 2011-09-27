<?php
class Users {

	function __construct() {
	}

	function login() {
		$valid=SecurityChecker::requestPOST("login");
		if (count($valid)>0) throw new SecurityExceptions(Constants::CODE_MISSING_PARAM, $valid);
		$db = new DBManager();
		$login= $db->callProcedure("login", array(0=>$_POST['username'],1=>$_POST['password']));
		if (!is_array($login)) throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
		else if (count($login)==0) throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
		else if (!is_array($login[0])) throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
		else if (count($login[0])==0) throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
		else if (isset($login[0]['error'])){
			if ($login[0]['error']=='wrong_username') throw new UsersExceptions(Constants::CODE_WRONG_USERNAME);
			else  throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
		}
		else {
			if (!isset($login[0]['ime'])) throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
			else if (!isset($login[0]['id'])) throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
			else if (!isset($login[0]['prezime'])) throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
			else if (!isset($login[0]['nivo'])) throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
			else if (!isset($login[0]['username'])) throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
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