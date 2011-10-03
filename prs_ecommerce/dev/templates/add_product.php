<?php
require_once '../classes/AutoloadClasses.inc';
SecurityChecker::sessionStart();
		if(!isset($_SESSION['admin'])){
			header("Location: /admin/login.php");
		}
		var_dump($_REQUEST);
		$admin = Admin::fromJson($_SESSION['admin']); 
		$result = $admin->addProduct($_REQUEST);
		var_dump($result);
?>