<?php
class Users {

	function __construct() {
	}

	function login() {
		$valid=SecurityChecker::requestPOST("korisnik_login");
		if (count($valid)>0) return $valid;
		$db = new DBManager();
		$login= $db->callProcedure("korisnik_login", array(0=>$_POST['username'],1=>$_POST['password']));
		return $login;
	}
}
?>