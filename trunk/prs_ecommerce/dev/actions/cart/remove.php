<?php
require_once '../classes/AutoloadClasses.inc';
SecurityChecker::sessionStart();
if (isset($_POST['item_id'])){
	if (!isset($_SESSION['cart'])){
		$_SESSION['cart']=array();
	}
	$cart = array();
	foreach ($_SESSION['cart'] as $key=>$value){
		if ($key!=$_POST['item_id']){
			$cart[$key]=$value;
		}
		elseif ($value['value']>1){
			$value['value']--;
			$cart[$key]=$value;
		}
	}
	if (count($cart)>0){
		$_SESSION['cart']=$cart;
	}
	else {
		unset($_SESSION['cart']);
	}
}
?>