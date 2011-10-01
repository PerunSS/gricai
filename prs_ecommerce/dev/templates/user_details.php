<?php 
SecurityChecker::sessionStart();
if(!isset($_SESSION['admin'])){
	header("Location: /admin/login.php");
}
$admin = Admin::fromJson($_SESSION['admin']);

$result = $admin->getUserDetails($_REQUEST['user_id']);
$address = false;
foreach($result as $user){
	//pravno lice
	if($user['tip']){
		if($address){
?>
	<div id="adresa_za_dostavu">
		Adresa za dostavu: <?=$user['adresa_za_dostavu']?>
	</div>
<?php 			
		}else{
?>
	<div id="display_user">
		Ime firme: <?=$user['ime_firme']?><br/>
		Adresa: <?=$user['adresa']?><br/>
		PIB: <?=$user['pib']?><br/>
		maticni broj: <?=$user['maticni_broj']?><br/>
		telefon: <?=$user['telefon']?><br/>
	</div>		
	<div id="display_common">
		Username: <?=$user['korisnik']?><br/>
		email: <?=$user['email']?><br/>
		aktivan: <?=($user['aktivan']?'da':'ne')?><br/>
		banovan: <?=($user['banovan']?'da':'ne')?>
	</div>
	<div id="adresa_za_dostavu">
		Adresa za dostavu: <?=$user['adresa_za_dostavu']?>
	</div>
<?php
			$address = true; 		
		}
?>
<?php 		
	}else{
?>
	<div id="display_user">
		Ime: <?=$user['ime']?><br/>
		Prezime: <?=$user['prezime']?><br/>
		JMBG: <?=$user['jmbg']?><br/>
		telefon: <?=$user['telefon']?><br/>
		adresa: <?=$user['adresa']?><br/>
	</div>		
	<div id="display_common">
		Username: <?=$user['korisnik']?><br/>
		email: <?=$user['email']?><br/>
		aktivan: <?=($user['aktivan']?'da':'ne')?><br/>
		banovan: <?=($user['banovan']?'da':'ne')?>
	</div>

<?php 		
	}
}

