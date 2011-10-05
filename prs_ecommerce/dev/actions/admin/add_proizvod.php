<?php
$db = new DBManager();
print_r($_POST);
$update = $db->callProcedure("admin_add_proizvod", array(
0=>$_POST['id_kat'],
1=>$_POST['naziv'],
2=>$_POST['poreklo'],
3=>$_POST['opis'],
4=>$_POST['pakovanje'],
5=>$_POST['cena'],
6=>$_POST['id_pod'],
7=>$_POST['stanje'],
8=>$_POST['minimum']
));
$valid = false;
if (is_array($update)){
	if (count($update)>0 && is_array($update[0])){
		$valid=true;
	}
}
if ($valid){
	$message="Snimljen proizvod";
}
else {
	$message="Nije snimljen proizvod";
}
?>
<div id="message">
	<table>
		<tr>
			<td><?php echo $message;?></td>
			<td><input
				type="submit"
				id="close"
				name="close"
				value="OK"
			></td>
		</tr>
	</table>
</div>
