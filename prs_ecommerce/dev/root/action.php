<?php
require_once '../classes/AutoloadClasses.inc';
if(isset($_POST['a_login'])){
	include '../actions/admin/admin_login.php';
}