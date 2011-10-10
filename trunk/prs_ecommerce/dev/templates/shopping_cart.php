<?php
require_once '../classes/AutoloadClasses.inc';
SecurityChecker::sessionStart();
?>

<div
	id="show_cart"
>
<?php
if (isset($_SESSION['cart']) && count($_SESSION['cart'])>0){?>
	<fieldset class="shopping_cart">
		<div align="right">
			<input
				type="submit"
				id="close"
				name="close"
				value="X"
				class="dugmici"
				align="right"
			>
		</div>
		<br />
		<table>
			<thead>
				<tr>
					<th id="proba"><label></label></th>
					<th><label>Proizvod</label></th>
					<th><label>Kolicina</label></th>
					<th><label>Kolicina</label></th>
				</tr>
			</thead>
			<tbody>
			<?php
			$total=0;
			foreach ($_SESSION['cart'] as $key=>$value){?>
				<tr>
					<td><input
						type="submit"
						class="remove dugmici"
						item_id="<?php echo $key?>"
						value="x"
					></td>
					<td><label><?php echo $value['name']?> </label></td>
					<td><label><?php echo $value['value']?> </label></td>
					<td><label><?php echo ($value['currency']*$value['value'])." RSD"?> </label>
					</td>
				</tr>
				<?php
				$total+=$value['currency']*$value['value'];
			}
			?>
			</tbody>
			<tfoot>
				<tr class="last_row">
					<td></td>
					<td></td>
					<td><?php echo "Ukupno:"?></td>
					<td><label><?php echo $total." RSD"?> </label></td>
				</tr>
				<tr>
					<td><input
						type="submit"
						id="empty"
						name="empty"
						value="Isprazni"
						class="dugmici"
					>
					</td>
					<td></td>
					<td></td>
					<td><input
						type="submit"
						id="buy"
						name="buy"
						value="Kupi"
						class="dugmici"
					></td>
				</tr>
			</tfoot>
		</table>
	</fieldset>
	<?php
}
else {?>
	<fieldset class="empty_cart">
		<div align="right">
			<input
				type="submit"
				id="close"
				name="close"
				value="X"
				class="dugmici"
				align="right"
			>
		</div>
		<table align="center">
			<tr>
				<td><label class="labela error_tekst" >Nema proizvoda u korpi</label>
				</td>
			</tr>
		</table>
	</fieldset>
	<?php
}
?>
</div>
