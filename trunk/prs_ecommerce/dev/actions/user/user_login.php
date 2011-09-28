<?php
try{
	$user=new Users();
	$login=$user->login();
	$user_string = json_decode($login);	
	$_SESSION['user'] = json_encode($user_string->user);
	print_r($login);
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