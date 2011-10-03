<?php
require_once '../classes/AutoloadClasses.inc';
if(isset($_POST['a_login'])){
	include '../actions/admin/admin_login.php';
}
if(isset($_REQUEST['a_korisnik_search'])){
	include '../templates/user_list.php';
}
if(isset($_POST['ban_action'])){
	include '../actions/admin/toogle_ban.php';
}
if(isset($_REQUEST['admin_korisnik_details'])){
	include '../templates/user_details.php';
}
if(isset($_REQUEST['a_porudzbenica_search'])){
	include '../templates/porudzbenica_list.php';
}
if(isset($_POST['u_login'])){
	include '../actions/user/user_login.php';
}
if(isset($_POST['register_fl'])){
	include '../actions/user/individual_register.php';
}
if(isset($_POST['register_pl'])){
	include '../actions/user/entity_register.php';
}
if(isset($_POST['u_logout'])){
	include '../actions/user/user_logout.php';
}
if(isset($_POST['add_to_cart'])){
	include '../actions/cart/add.php';
}
if(isset($_POST['remove_from_cart'])){
	include '../actions/cart/remove.php';
}
if(isset($_POST['empty_cart'])){
	include '../actions/cart/empty.php';
}
if(isset($_POST['buy_cart'])){
	include '../actions/cart/buy.php';
}
if(isset($_REQUEST['a_add_product'])){
	include '../templates/add_product.php';
}