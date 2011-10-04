<?php
require_once '../classes/AutoloadClasses.inc';
$db=new DBManager();
$cat="%";
if ($_GET['cat']!="0"){
	$cat=$_GET['cat'];
}
$sub="%";
if ($_GET['sub']!="0"){
	$sub=$_GET['sub'];
}
$name="%";
if (strlen($_GET['name'])>0){
	$name="%".$_GET['name']."%";
}
$items=$db->callProcedure("select_proizvodi", array(0=>$name,1=>$cat,2=>$sub));
if (is_array($items) && count($items)>0){?>
<fieldset>
	<table>
		<thead>
			<tr>
				<th><label>Kategorija</label></th>
				<th><label>Podkategorija</label></th>
				<th><label>Naziv</label></th>
				<th><label>Opis</label></th>
				<th><label>Cena</label></th>
			</tr>
		</thead>
		<tbody>
		<?php
		foreach ($items as $value){?>
			<tr>
				<td><label><?php echo $value['kategorija']?> </label></td>
				<td><label><?php echo $value['podkategorija']?> </label></td>
				<td><label><?php echo $value['naziv']?> </label></td>
				<td><label><?php echo $value['opis']?> </label></td>
				<td><label><?php echo $value['cena']." RSD"?> </label></td>
			</tr>
			<?php
		}
		?>
		</tbody>
	</table>
</fieldset>
		<?php
}
else {?>
<fieldset>
	<table>
		<tr>
			<td><label>Nema takvih proizvoda</label>
			</td>
		</tr>
	</table>
</fieldset>
<?php
}
?>