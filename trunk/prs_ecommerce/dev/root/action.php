<?php
require_once '../classes/AutoloadClasses.inc';
if(isset($_POST['a_login'])){
	include '../actions/admin/admin_login.php';
}
if(isset($_POST['u_login'])){
	include '../actions/user/user_login.php';
}