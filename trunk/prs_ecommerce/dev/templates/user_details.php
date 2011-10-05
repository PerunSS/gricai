<?php
SecurityChecker::sessionStart();
?>
<div id="message">
<?php
$admin = Admin::fromJson($_SESSION['admin']);

$result = $admin->getUserDetails($_REQUEST['user_id']);
$address = false;
foreach($result as $user){
	//pravno lice
	if($user['tip']){
		if($address){
			?>
	<div id="adresa_za_dostavu">
		Adresa za dostavu:
		<?php echo $user['adresa_za_dostavu']?>
	</div>
	<?php
		}else{
			?>
	<div id="display_user">
		<table align="center">
			<tr>
				<td>Ime firme:</td>
				<td><?php echo $user['ime_firme']?></td>
			</tr>
			<tr>
				<td>Adresa:</td>
				<td><?php echo $user['adresa']?></td>
			</tr>
			<tr>
				<td>PIB:</td>
				<td><?php echo $user['pib']?></td>
			</tr>
			<tr>
				<td>Maticni broj:</td>
				<td><?php echo $user['maticni_broj']?></td>
			</tr>
			<tr>
				<td>Telefon:</td>
				<td><?php echo $user['telefon']?></td>
			</tr>
			<tr>
				<td>Username:</td>
				<td><?php echo $user['korisnik']?></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><?php echo $user['email']?></td>
			</tr>
			<tr>
				<td>Aktivan:</td>
				<td><?php echo ($user['aktivan']?'da':'ne')?></td>
			</tr>
			<tr>
				<td>Banovan:</td>
				<td><?php echo ($user['banovan']?'da':'ne')?>
				</td>
			</tr>
			<tr>
				<td><input
					type="submit"
					value="Ok"
					id="close"
				></td>
			</tr>
		</table>
	</div>
	<div id="adresa_za_dostavu">
		Adresa za dostavu:
		<?php echo $user['adresa_za_dostavu']?>
	</div>
	<?php
	$address = true;
		}
		?>
		<?php
	}else{
		?>

	<div id="display_user">
		<table align="center">
			<tr>
				<td>Ime:</td>
				<td><?php echo $user['ime']?></td>
			</tr>
			<tr>
				<td>Prezime:</td>
				<td><?php echo $user['prezime']?></td>
			</tr>
			<tr>
				<td>JMBG:</td>
				<td><?php echo $user['jmbg']?></td>
			</tr>
			<tr>
				<td>Telefon:</td>
				<td><?php echo $user['telefon']?></td>
			</tr>
			<tr>
				<td>Adresa:</td>
				<td><?php echo $user['adresa']?></td>
			</tr>
			<tr>
				<td>Username:</td>
				<td><?php echo $user['korisnik']?></td>
			</tr>
			<tr>
				<td>Amail:</td>
				<td><?php echo $user['email']?></td>
			</tr>
			<tr>
				<td>Aktivan:</td>
				<td><?php echo ($user['aktivan']?'da':'ne')?></td>
			</tr>
			<tr>
				<td>Banovan:</td>
				<td><?php echo ($user['banovan']?'da':'ne')?></td>
			</tr>
			<tr>
				<td><input
					type="submit"
					value="Ok"
					id="close"
				></td>
			</tr>
		</table>
	</div>


	<?php
	}
}?>
</div>

