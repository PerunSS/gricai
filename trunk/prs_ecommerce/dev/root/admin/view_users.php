<?php
require_once '../../classes/AutoloadClasses.inc';
SecurityChecker::sessionStart();
if(!isset($_SESSION['admin'])){
	header("Location: /admin/login.php");
}
$admin = Admin::fromJson($_SESSION['admin']);
$users = $admin->getUsers();
?>
<form action="/action.php" method="POST" target="results">
Username: <input type="text" name="a_korisnik_un_search" /><br />
Tip:<select name="a_korisnik_tip_search">
	<option value="svi" selected="selected">svi</option>
	<option value="f_lica">Fizicka lica</option>
	<option value="p_lica">Pravna lica</option>
</select><br />
<input type="checkbox" name="a_korisnik_aktivan_search" />
Aktivni korisnici<br />
<input type="checkbox" name="a_korisnik_banovan_search" />
Banovani korisnici<br />
<input type="submit" value="pretraga" /> 
<input type="hidden" name="a_korisnik_search" value="true" />
</form>
<iframe name="results" id="results">
<table class="tabela">
	<thead>
		<tr>
			<th>Username</th>
			<th>email</th>
			<th>tip</th>
			<th>aktivan</th>
			<th>banovan</th>
		</tr>
	</thead>
	<tbody>
	<?php
	$color = true;
	foreach($users as $user){
		$color = !$color;
		$banovan = $user['banovan']?'un':'';
		?>
		<tr class="<?php echo ($color?'sivi':'svetli');?>">
			<td><?=$user['korisnik']; ?></td>
			<td><?=$user['email']; ?></td>
			<td><?=($user['tip']?'pravno lice':'fizicko lice'); ?></td>
			<td><?=($user['aktivan']?'aktivan':''); ?></td>
			<td>
			<form action="/action.php" method="POST"><input type="hidden"
				name="user_id" value="<?=$user['id']?>" /> <input type="hidden"
				name="ban_action" value="<?=$banovan?>ban" /> <input type="submit"
				value="<?=$banovan?>ban" /></form>
			</td>
		</tr>
		<?php 	
}
?>
	</tbody>
</table>
</iframe>
