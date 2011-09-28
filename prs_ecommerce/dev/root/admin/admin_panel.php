<?php
require_once '../../classes/AutoloadClasses.inc'; 
SecurityChecker::sessionStart();
if(!isset($_SESSION['admin'])){
	header("Location: /admin/login.php");
}

?>
<h1>Admin panel</h1>

<?php var_dump($_SESSION['admin'])?>