<?php
$db = new DBManager();
$delete = $db->callProcedure("admin_delete_proizvod", array(0=>$_POST['id']));
$valid = false;
if (is_array($delete)){
	if (count($delete)>0 && is_array($delete[0])){
		if (isset($delete[0]['status']) && $delete[0]['status']=='success'){
			$valid=true;
		}
	}
}
if ($valid){
	$message="Obrisan proizvod";
}
else {
	$message="Nije obrisan proizvod";
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
