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

	function update_profile() {
		$valid=SecurityChecker::requestPOST("update_profile");
		if (count($valid)>0) throw new SecurityExceptions(Constants::CODE_MISSING_PARAM, $valid);
		$db = new DBManager();
		$update_profile = $db->callProcedure("update_profile", array(0=>$_POST['id'],1=>$_POST['firstname'],2=>$_POST['lastname']));
		if (!is_array($update_profile)) throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
		else if (count($update_profile)==0) throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
		else if (!is_array($update_profile[0])) throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
		else if (count($update_profile[0])==0) throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
		else if (isset($update_profile[0]['error'])){
			if ($update_profile[0]['error']=='wrong_id') throw new UsersExceptions(Constants::CODE_WRONG_ID);
			else  throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
		}
		else {
			if (!isset($update_profile[0]['ime'])) throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
			else if (!isset($update_profile[0]['id'])) throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
			else if (!isset($update_profile[0]['prezime'])) throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
			else if (!isset($update_profile[0]['nivo'])) throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
			else if (!isset($update_profile[0]['username'])) throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
			else {
				$user = array();
				foreach ($update_profile[0] as $key=>$value){;
				if (!is_int($key)){
					$user[$key]=$value;
				}
				}
				$creator=array("status" => "success", "user" => $user);
				return json_encode($creator);
			}
		}
	}

	function update_password() {
		$valid=SecurityChecker::requestPOST("update_password");
		if (count($valid)>0) throw new SecurityExceptions(Constants::CODE_MISSING_PARAM, $valid);
		if ($_POST['new_password']!=$_POST['confirm_password']) throw new UsersExceptions(Constants::CODE_WRONG_CONFIRM_PASSWORD);
		$db = new DBManager();
		$update_password = $db->callProcedure("update_password", array(0=>$_POST['id'],1=>$_POST['current_password'],2=>$_POST['new_password']));
		if (!is_array($update_password)) throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
		else if (count($update_password)==0) throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
		else if (!is_array($update_password[0])) throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
		else if (count($update_password[0])==0) throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
		else if (isset($update_password[0]['error'])){
			if ($update_password[0]['error']=='wrong_password') throw new UsersExceptions(Constants::CODE_WRONG_PASSWORD);
			else  throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
		}
		else {
			if (!isset($update_password[0]['status'])) throw new SecurityExceptions(Constants::CODE_TIMEOUT, null);
			else {
				$creator=array("status" => "success");
				return json_encode($creator);
			}
		}
	}
}
?>