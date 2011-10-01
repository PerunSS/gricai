<?php 
SecurityChecker::sessionStart();
if(!isset($_SESSION['admin'])){
	header("Location: /admin/login.php");
}
$admin = Admin::fromJson($_SESSION['admin']);
$users = $admin->getUsers();
$data = $_REQUEST;
//print_r($_REQUEST);
$target_username = (empty($data['a_korisnik_un_search'])?0:$data['a_korisnik_un_search']);
$target_tip = 10;
if(isset($data['a_korisnik_tip_search'])){
	$target_tip = $data['a_korisnik_tip_search'];
}	
$target_aktivan = (empty($data['a_korisnik_aktivan_search'])?0:1);
$target_banovan = (empty($data['a_korisnik_banovan_search'])?0:1);
//echo "Target tip: $target_tip<br/>";
//echo "Target aktivan: $target_aktivan<br/>";
//echo "Target banovan: $target_banovan<br/>";
//echo "Target username: $target_username<br/>";

?>
<table class="tabela">
	<thead>
		<tr>
			<th>Username</th>
			<th>email</th>
			<th>tip</th>
			<th>aktivan</th>
			<th>banovan</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
	<?php
	$color = true;
	foreach($users as $user){
		$color = !$color;
		$banovan = $user['banovan']?'un':'';
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
		}
		?>
		<tr class="<?php echo ($color?'sivi':'svetli');?>">
			<td><?=$user['korisnik']; ?></td>
			<td><?=$user['email']; ?></td>
			<td><?=($user['tip']?'pravno lice':'fizicko lice'); ?></td>
			<td><?=($user['aktivan']?'aktivan':''); ?></td>
			<td>
			<form action="/action.php" method="POST">
				<input type="hidden" name="user_id" value="<?=$user['id']?>" />
				<input type="hidden" name="ban_action" value="<?=$banovan?>ban" />
				<input type="submit" value="<?=$banovan?>ban" />
			</form>
			</td>
			<td><form action="/action.php" method="POST">
				<input type="hidden" name="user_id" value="<?=$user['id']?>" />
				<input type="hidden" name="admin_korisnik_details" value="ok"/>
				<input type="submit" value="detaljno"/>
			</form></td>
		</tr>
		<?php 	
}
?>
	</tbody>
</table>
