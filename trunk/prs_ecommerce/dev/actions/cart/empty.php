<?php
require_once '../classes/AutoloadClasses.inc';
SecurityChecker::sessionStart();
if (!isset($_SESSION['cart'])){
	$_SESSION['cart']=array();
}
unset($_SESSION['cart']);
?>