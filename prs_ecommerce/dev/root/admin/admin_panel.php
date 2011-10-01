<?php
require_once '../../classes/AutoloadClasses.inc'; 
SecurityChecker::sessionStart();
if(!isset($_SESSION['admin'])){
	header("Location: /admin/login.php");
}

?>
<h1>Admin panel</h1>
<ul>
<li><a href="/admin/view_users.php">Pregled korisnika</a></li>
<li><a href="/admin/view_products.php">Pregled proizvoda</a></li>
<li><a href="/admin/change_pass.php">Promena lozinke</a></li>
<li><a href="/admin/logout.php">Kraj rada</a></li>
</ul>
