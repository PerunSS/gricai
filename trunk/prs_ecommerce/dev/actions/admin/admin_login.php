<?php

$admin = new Admin($_REQUEST['a_username'], $_REQUEST['a_password']);
if($admin->login()){
	session_start();
	$_SESSION['admin']=$admin;
	header("Location: /admin/admin_panel.php");
}else{
	echo "wrong username or password";
}