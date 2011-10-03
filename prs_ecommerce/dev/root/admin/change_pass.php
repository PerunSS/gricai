<?php
require_once '../../classes/AutoloadClasses.inc';
SecurityChecker::sessionStart();
if(!isset($_SESSION['admin'])){
	header("Location: /admin/login.php");
}
?>
<a href="/admin/admin_panel.php">nazad</a>
<?php 
if(isset($_SESSION['admin_ch_pass_error'])){
	?>
	<div class="message">
	<?=$_SESSION['admin_ch_pass_error']?>
	</div>
	<?php 
	
	unset($_SESSION['admin_ch_pass_error']);
}
?>
<form action="/action.php" method="POST">
stara lozinka: <input type="password" name="old_pass" /><br />
nova lozinka: <input type="password" name="new_pass" /><br />
nova lozinka - potvrda: <input type="password" name="new_pass_repeat" />
<br />
<input type="submit" value="promena" /> 
<input type="hidden" name="a_change_pass" value="true" />
</form>
