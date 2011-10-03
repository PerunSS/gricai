<?php
SecurityChecker::sessionStart();
if(!isset($_SESSION['admin'])){
	header("Location: /admin/login.php");
}
$admin = Admin::fromJson($_SESSION['admin']);
$porudzbenice = $admin->getPourdzbenice();

$min_price = 0;
$max_price = 0;
$datum = 0;
if(isset($_REQUEST['a_porudzbenica_cena_min'])) $min_price = $_REQUEST['a_porudzbenica_cena_min'];
if(isset($_REQUEST['a_porudzbenica_cena_max'])) $max_price = $_REQUEST['a_porudzbenica_cena_max'];
if(isset($_REQUEST['a_porudzbenica_vreme_search'])) $datum = $_REQUEST['a_porudzbenica_vreme_search']; 
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
		if($min_price && $por['cena']<$min_price) continue;
		if($max_price && $por['cena']>$max_price) continue;
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
				<input type="hidden" name="por_id" value="<?=$por['id']?>" />
				<input type="hidden" name="admin_porudzbenica_details" value="ok"/>	
				<input type="submit" value="detaljno" />
			</form>
			</td>
		</tr>
		<?php 	
}
?>
	</tbody>
</table>
