<?php
SecurityChecker::sessionStart();
?>
<div id="message">
<?php
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
	vreme porudzbenice:
	<?php echo $proizvod['vreme']?>
	<br/>ukupna cena:
	<?php echo $proizvod['ukupna_cena']?>
	RSD<br/>status korpe:
	<?php echo $proizvod['status']?><br/>
</div>
<table>
	<thead>
		<tr>
			<th width="10%">Kategorija</th>
			<th width="10%">Tip</th>
			<th width="15%">Naziv</th>
			<th width="10%">Kolicina</th>
			<th width="10%">Cena</th>
			<th width="15%">Poreklo</th>
			<th width="15%">Opis</th>
			<th width="15%">Pakovanje</th>
		</tr>
	</thead>
	<tbody>
	<?php
	$sum = false;
	}
	?>
		<tr class="<?php echo ($color?'sivi':'svetli');?>">
			<td><?php echo $proizvod['kategorija']?></td>
			<td><?php echo $proizvod['tip']?></td>
			<td><?php echo $proizvod['naziv']?></td>
			<td><?php echo $proizvod['kolicina']?></td>
			<td><?php echo $proizvod['pojedinacna_cena']?></td>
			<td><?php echo $proizvod['poreklo']?></td>
			<td><?php echo $proizvod['opis']?></td>
			<td><?php echo $proizvod['pakovanje']?></td>
		</tr>

		<?php
}
?>
		<tr>
			<td><input
				type="submit"
				value="Ok"
				id="close"
			></td>
		</tr>
	</tbody>
</table>
</div>
