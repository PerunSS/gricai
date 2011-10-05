<?php
$db = new DBManager();
print_r($_POST);
$update = $db->callProcedure("admin_update_proizvod", array(
0=>$_POST['id'],
1=>$_POST['id_kat'],
2=>$_POST['naziv'],
3=>$_POST['poreklo'],
4=>$_POST['opis'],
5=>$_POST['pakovanje'],
6=>$_POST['cena'],
7=>$_POST['id_pod'],
8=>$_POST['stanje'],
9=>$_POST['minimum']
));
$valid = false;
if (is_array($update)){
	if (count($update)>0 && is_array($update[0])){
		if (isset($update[0]['status']) && $update[0]['status']=='success'){
			$valid=true;
		}
	}
}
if ($valid){
	$message="Editovan proizvod";
}
else {
	$message="Nije editovan proizvod";
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