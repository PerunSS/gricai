<?php
SecurityChecker::sessionStart();
if (SecurityChecker::isLogged()){
	session_destroy();
}
header("Location: shop.php");
?>