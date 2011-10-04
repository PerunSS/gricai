<?php
	SecurityChecker::sessionStart();
	if(!isset($_SESSION['admin'])){
		header("Location: /admin/login.php");
	}
	$admin = Admin::fromJson($_SESSION['admin']);

	if(isset($_REQUEST['ban_action'])){
		if($_REQUEST['ban_action']=='Ban'){
			$admin->banUser($_REQUEST['user_id'],'test');
		}else{
			$admin->unbanUser($_REQUEST['user_id']);
		}
		
	}
?>