<?php if (isset($_POST['addresses']) && count($_POST['addresses'])>0){?>
<table align="center">
<?php
foreach ($_POST['addresses'] as $key=>$value){?>
	<tr>
		<td><label for="adresa_<?php echo $key;?>" class="labela">Adresa: </label></td>
		<td><input
			type="text"
			id="adresa_<?php echo $key;?>"
			name="adresa_<?php echo $key;?>"
			adresa="<?php echo $key;?>"
			class="adrese tekst"
			value="<?php echo $value;?>"
		></td>
		<td><input
			type="submit"
			adresa="<?php echo $key;?>"
			class="ukloni dugmici"
			value="x"
		>
		</td>
		<td>
			<div
				class="hidden warning"
				id="empty_address_<?php echo $key;?>"
			>Nedostaje unos</div>
			<div
				class="hidden warning"
				id="alphanumeric_address_<?php echo $key;?>"
			>Pogresan unos</div>
		</td>
	</tr>
	<?php }
	?>
</table>
	<?php }?>