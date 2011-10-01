<?php
	SecurityChecker::sessionStart();
	if(!isset($_SESSION['admin'])){
		header("Location: /admin/login.php");
	}
	$admin = Admin::fromJson($_SESSION['admin']);

	if(isset($_REQUEST['ban_action'])){
		if($_REQUEST['ban_action']=='ban'){
			$admin->banUser($_REQUEST['user_id'],'test');
		}else{
			$admin->unbanUser($_REQUEST['user_id']);
		}
		
	}
	echo '<form action="/admin/view_users.php" id="autoSubmit" method="GET" style="display:none" target="_top">
		</form>
		<script type="text/javascript">
			document.getElementById(\'autoSubmit\').submit();
		</script>';
?>