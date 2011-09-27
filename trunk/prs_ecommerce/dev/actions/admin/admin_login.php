<?php

$admin = new Admin($_REQUEST['a_username'], $_REQUEST['a_password']);
if($admin->login()){
	
}else{
	
}