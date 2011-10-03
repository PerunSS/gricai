<?php
SecurityChecker::sessionStart();
if(!isset($_SESSION['admin'])){
	header("Location: /admin/login.php");
}
$admin = Admin::fromJson($_SESSION['admin']);

$result = $admin->getPorudzbenicaDetails($_REQUEST['por_id']);

$sum = true;
$color = true;
foreach($result as $proizvod){
	$color = true;
	if($sum){
?>
	<div id="porudzbenica_sum">
	vreme porudzbenice: <?=$proizvod['vreme']?>, ukupna cena: <?=$proizvod['ukupna_cena']?> RSD, status korpe: <?=$proizvod['status']?>
	</div>
	<table>
		<thead>
			<tr>
				<th>Kategorija</th>
				<th>Tip</th>
				<th>Naziv</th>
				<th>Kolicina</th>
				<th>Cena</th>
				<th>Poreklo</th>
				<th>Opis</th>
				<th>Pakovanje</th>
			</tr>
		</thead>
		<tbody>
<?php
	$sum = false; 
	}
?>
	<tr class="<?php echo ($color?'sivi':'svetli');?>">
		<td><?=$proizvod['kategorija']?></td>
		<td><?=$proizvod['tip']?></td>
		<td><?=$proizvod['naziv']?></td>
		<td><?=$proizvod['kolicina']?></td>
		<td><?=$proizvod['pojedinacna_cena']?></td>
		<td><?=$proizvod['poreklo']?></td>
		<td><?=$proizvod['opis']?></td>
		<td><?=$proizvod['pakovanje']?></td>
	</tr>
<?php 
}
?>
</tbody>
</table>