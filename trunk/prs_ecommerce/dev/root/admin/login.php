<?php 
require_once '../../classes/AutoloadClasses.inc';
SecurityChecker::sessionStart();
if (isset($_SESSION['admin'])){
	header("Location: admin_panel.php");
}
?>
<form action='../action.php' method = 'post'>
	username: <input type = 'text' id = 'a_username' name = 'a_username'/><br/>
	password: <input type = 'password' id = 'a_password' name = 'a_password'/><br/>
	<input type = 'submit' name = 'a_login' value = 'login'/>
</form>
