<select
	id="sub_categorry"
	name="sub_categorry"
>
	<option value="0">Izaberi podkategoriju</option>
	<?php
	require_once '../classes/AutoloadClasses.inc';
	if (isset($_GET['id'])) {
		$db = new DBManager();
		$options = $db->callProcedure("select_podkategorije", array(0=>$_GET['id']));
		if (is_array($options)){
			foreach ($options as $value){?>
	<option value="<?php echo $value['id'];?>">
	<?php echo $value['kategorija'];?>
	</option>
	<?php }
		}
	}
	?>
</select>
