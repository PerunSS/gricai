<?php
SecurityChecker::sessionStart();
if(!isset($_SESSION['admin'])){
	header("Location: /admin/login.php");
}
$admin = Admin::fromJson($_SESSION['admin']);
if(!empty($_POST['old_pass']) && !empty($_POST['new_pass']) && !empty($_POST['new_pass_repeat'])){
	$old_pass = $_POST['old_pass'];
	$new_pass = $_POST['new_pass'];
	$new_pass_rep = $_POST['new_pass_repeat'];
	
	if($new_pass != $new_pass_rep){
		$_SESSION['admin_ch_pass_error'] = 'potvrda nove lozinke nije ista kao nova lozinka';
		header("Location: /admin/change_pass.php");
		exit();		
	}
	$result = $admin->changePass($old_pass,$new_pass,$new_pass_rep);
	if($result[0]){
		$_SESSION['admin_ch_pass_error'] = 'sifra uspesno zamenjena';
	}else{
		$_SESSION['admin_ch_pass_error'] = 'pogresna sifra';
	}
	header("Location: /admin/change_pass.php");
	exit();
}
$_SESSION['admin_ch_pass_error'] = 'nisu postavljeni svi parametri';
header("Location: /admin/change_pass.php");
exit();		
