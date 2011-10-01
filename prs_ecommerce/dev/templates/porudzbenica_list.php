<?php
SecurityChecker::sessionStart();
if(!isset($_SESSION['admin'])){
	header("Location: /admin/login.php");
}
$admin = Admin::fromJson($_SESSION['admin']);
$porudzbenice = $admin->getPourdzbenice();
?>
<table class="tabela">
	<thead>
		<tr>
			<th>Username</th>
			<th>datum</th>
			<th>vrednost</th>
			<th>status</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
	<?php
	$color = true;
	foreach($porudzbenice as $por){
		$color = !$color;
		/*$banovan = $user['banovan']?'un':'';
		if($target_banovan && !$user['banovan']){
			continue;
		}
		if($target_username){
			if(strpos($user['korisnik'],$target_username)===false){
				continue;
			}
		}
		if($target_aktivan && !$user['aktivan']){
			continue;
		}
		if($target_tip != 10){
			if($target_tip && !$user['tip']){
				continue;
			}
			if(!$target_tip && $user['tip']){
				continue;
			}
		}*/
		?>
		<tr class="<?php echo ($color?'sivi':'svetli');?>">
			<td><?=$por['korisnik']; ?></td>
			<td><?=$por['vreme']; ?></td>
			<td><?=$por['cena']; ?></td>
			<td><?=$por['status']; ?></td>
			<td>
			<form action="/action.php" method="POST">
				<input type="hidden" name="user_id" value="<?=$por['id']?>" />
				<input type="submit" value="detaljno" />
			</form>
			</td>
		</tr>
		<?php 	
}
?>
	</tbody>
</table>
