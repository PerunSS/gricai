<?php

$admin = new Admin($_REQUEST['a_username'], $_REQUEST['a_password']);
if($admin->login()){
	SecurityChecker::sessionStart();
	$_SESSION['admin']=$admin->toJson();
	header("Location: /admin/admin_panel.php");
}else{
	echo "wrong username or password";
}