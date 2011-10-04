<?php
SecurityChecker::sessionStart();
$admin = Admin::fromJson($_SESSION['admin']);
$porudzbenice = $admin->getPourdzbenice();

$min_price = 0;
$max_price = 0;
$datum = 0;
if(isset($_REQUEST['a_porudzbenica_cena_min'])) $min_price = $_REQUEST['a_porudzbenica_cena_min'];
if(isset($_REQUEST['a_porudzbenica_cena_max'])) $max_price = $_REQUEST['a_porudzbenica_cena_max'];
if(isset($_REQUEST['a_porudzbenica_vreme_search'])) $datum = $_REQUEST['a_porudzbenica_vreme_search'];
?>
<table>
	<thead>
		<tr>
			<th width="20%">Username</th>
			<th width="20%">Datum</th>
			<th width="20%">Vrednost</th>
			<th width="20%">Status</th>
			<th width="20%"></th>
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
			<td><?php echo $por['korisnik']; ?></td>
			<td><?php echo $por['vreme']; ?></td>
			<td><?php echo $por['cena']; ?></td>
			<td><?php echo $por['status']; ?></td>
			<td><input
				type="submit"
				value="Detaljno"
				por_id="<?php echo $por['id']?>"
				class="detail_por"
			/>
			</td>
		</tr>
		<?php
	}
	?>
	</tbody>
</table>
