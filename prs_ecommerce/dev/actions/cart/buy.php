<?php
SecurityChecker::sessionStart();
try{
	SecurityChecker::sessionStart();
	$user=new Users();
	$buy=$user->buy();
	unset($_SESSION['cart']);
}
catch (SecurityExceptions $se){
	$buy=$se->getJSON();
}
catch (Exception $e){
	//TODO log
}
include "../templates/buy.php";
?>