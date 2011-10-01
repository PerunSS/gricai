<?php
require_once '../classes/AutoloadClasses.inc';
if(isset($_POST['a_login'])){
	include '../actions/admin/admin_login.php';
}
if(isset($_POST['a_korisnik_search'])){
	include '../actions/admin/admin_user_search.php';
}

if(isset($_POST['ban_action'])){
	include '../actions/admin/toogle_ban.php';
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