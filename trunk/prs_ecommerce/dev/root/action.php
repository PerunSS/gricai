<?php
require_once '../classes/AutoloadClasses.inc';
if(isset($_POST['a_login'])){
	include '..'.Constants::PHP_PATH.'/actions/admin/admin_login.php';
}
if(isset($_REQUEST['a_korisnik_search'])){
	include '..'.Constants::PHP_PATH.'/templates/user_list.php';
}
if(isset($_POST['ban_action'])){
	include '..'.Constants::PHP_PATH.'/actions/admin/toogle_ban.php';
}
if(isset($_REQUEST['admin_korisnik_details'])){
	include '..'.Constants::PHP_PATH.'/templates/user_details.php';
}
if(isset($_REQUEST['a_porudzbenica_search'])){
	include '..'.Constants::PHP_PATH.'/templates/porudzbenica_list.php';
}
if(isset($_REQUEST['admin_porudzbenica_details'])){
	include '..'.Constants::PHP_PATH.'/templates/porudzbenica_details.php';
}
if(isset($_POST['a_change_pass'])){
	include '..'.Constants::PHP_PATH.'/actions/admin/change_pass.php';
}
if(isset($_POST['u_login'])){
	include '..'.Constants::PHP_PATH.'/actions/user/user_login.php';
}
if(isset($_POST['register_fl'])){
	include '..'.Constants::PHP_PATH.'/actions/user/individual_register.php';
}
if(isset($_POST['register_pl'])){
	include '..'.Constants::PHP_PATH.'/actions/user/entity_register.php';
}
if(isset($_POST['u_logout'])){
	include '..'.Constants::PHP_PATH.'/actions/user/user_logout.php';
}
if(isset($_POST['add_to_cart'])){
	include '..'.Constants::PHP_PATH.'/actions/cart/add.php';
}
if(isset($_POST['forgot_password'])){
	include '..'.Constants::PHP_PATH.'/actions/utils/send_new_password.php';
}
if(isset($_POST['remove_from_cart'])){
	include '..'.Constants::PHP_PATH.'/actions/cart/remove.php';
}
if(isset($_POST['empty_cart'])){
	include '..'.Constants::PHP_PATH.'/actions/cart/empty.php';
}
if(isset($_POST['buy_cart'])){
	include '..'.Constants::PHP_PATH.'/actions/cart/buy.php';
}
if(isset($_REQUEST['a_add_product'])){
	include '..'.Constants::PHP_PATH.'/templates/add_product.php';
}
if(isset($_REQUEST['delete_proizvod'])){
	include '../actions/admin/delete_proizvod.php';
}
if(isset($_REQUEST['update_proizvod'])){
	include '../actions/admin/update_proizvod.php';
}
if(isset($_REQUEST['add_proizvod'])){
	include '../actions/admin/add_proizvod.php';
}