<?php
require_once '../../classes/AutoloadClasses.inc'; 
session_start();
print_r($_SESSION);
if(!isset($_SESSION['admin'])){
//	header("Location: /admin/login.php");
}

?>
<h1>Admin panel</h1>