<?php
try{
	$user=new Users();
	$login=$user->login();
	$user_string = json_decode($login);
	SecurityChecker::sessionStart();
	$_SESSION['user'] = json_encode($user_string->user);	
	header("Location: /user_home.php");

}
catch (SecurityExceptions $se){
	print_r($se->getJSON());
}
catch (UsersExceptions $ue){
	print_r($ue->getJSON());
}
catch (Exception $e){
	//TODO log
}
?>