<?php
require_once '../classes/AutoloadClasses.inc'; 
SecurityChecker::sessionStart();
if(!isset($_SESSION['user'])){
	header("Location: /login.php");
}
?>


<?php 
$user_info = json_decode($_SESSION['user']);

?>
<h1>welcome <?php echo $user_info->ime;?></h1>

