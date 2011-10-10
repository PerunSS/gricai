<?php
try{
	SecurityChecker::sessionStart();
	$user=new Users();
	$login=$user->login();	
	$user_string = json_decode($login);	
	$_SESSION['user'] = json_encode($user_string->user);
}
catch (SecurityExceptions $se){
	$_SESSION['login_error']=$se->getJSON();
}
catch (UsersExceptions $ue){
	$_SESSION['login_error']=$ue->getJSON();
}
catch (Exception $e){
	//TODO log
}
header("Location: kupovina.php");
?>