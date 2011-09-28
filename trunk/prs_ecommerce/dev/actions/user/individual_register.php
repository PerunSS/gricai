<?php
try{
	$user=new Users();
	$register=$user->individual_register();
	print_r($register);
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