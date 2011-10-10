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
$items=$db->callProcedure("select_proizvodi", array(0=>$name,1=>$cat,2=>$sub, 3=>$_GET['page']));
if (is_array($items) && count($items)>0){
	$db = new DBManager();
	$cat = $db->callProcedure("select_kategorije", array());
	$options = $db->callProcedure("select_podkategorije", array(0=>'%'));
	?>
<fieldset>
	<div align="left">
	<?php $broj =  $items[0]['count'];
	if ($broj > 20){
		$stranica = ceil($broj/20);?>
		<label class="labela">Strana:&nbsp;&nbsp;</label>
		<?php
		for ($i = 1; $i <= $stranica; $i++) {

			?>
		<label
			class="labela<?php if($i!=$_GET['page']) echo ' stranica';?>"
			strana="<?php echo $i;?>"
		>&nbsp;<?php echo $i;?> </label>
		<?php
		if ($i<$stranica) echo '<label class="labela">&nbsp;|&nbsp;</label>';
		}
	}?>
	</div>
	<br />
	<table>
		<thead>
			<tr>
				<th><label>Kategorija</label></th>
				<th><label>Podkategorija</label></th>
				<th><label>Naziv</label></th>
				<th><label>Opis</label></th>
				<th><label>Poreklo</label></th>
				<th><label>Pakovanje</label></th>
				<th><label>Stanje</label></th>
				<th><label>Minimum</label></th>
				<th><label>Cena</label></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
		<?php
		foreach ($items as $value){
			$var = 0;
			?>
			<tr>
				<td><select
					id="kat_<?php echo $value['id']?>"
					class="non_editable"
					disabled="disabled"
					proiz_id="<?php echo $value['id']?>"
					style="width: 80px"
				>
						<option value="0">Izaberi kategoriju</option>
						<?php
						if (is_array($cat)){
							foreach ($cat as $cat_value){?>
						<option
							value="<?php echo $cat_value['id'];?>"
							<?php if ($value['kategorija']==$cat_value['kategorija']){
								echo 'selected="selected"';
								$var = $cat_value['id'];
							}?>
						>
						<?php echo $cat_value['kategorija'];?>
						</option>
						<?php }
						}
						?>
				</select>
				</td>
				<td><div id="category_<?php echo $value['id']?>">
						<select
							id="pod_<?php echo $value['id']?>"
							class="non_editable"
							disabled="disabled"
							style="width: 80px"
						>
							<option value="0">Izaberi podkategoriju</option>
							<?php
							if (is_array($options)){
								foreach ($options as $opt_value){
									if ($opt_value['id_kategorija']==$var){
										?>
							<option
								value="<?php echo $opt_value['id'];?>"
								<?php echo ($value['podkategorija']==$opt_value['kategorija']?'selected="selected"':'')?>
							>
							<?php echo $opt_value['kategorija'];?>
							</option>
							<?php }
								}
							}
							?>
						</select>
					</div></td>
				<td><input
					type="text"
					disabled="disabled"
					id="naz_<?php echo $value['id']?>"
					value="<?php echo $value['naziv']?>"
					class="non_editable"
					size="7"
				/></td>
				<td><input
					type="text"
					disabled="disabled"
					id="opi_<?php echo $value['id']?>"
					value="<?php echo $value['opis']?>"
					class="non_editable"
					size="30"
				/></td>
				<td><input
					type="text"
					disabled="disabled"
					id="por_<?php echo $value['id']?>"
					value="<?php echo $value['poreklo']?>"
					class="non_editable"
					size="7"
				/></td>
				<td><input
					type="text"
					disabled="disabled"
					id="pak_<?php echo $value['id']?>"
					value="<?php echo $value['pakovanje']?>"
					class="non_editable"
					size="7"
				/></td>
				<td><input
					type="text"
					disabled="disabled"
					id="sta_<?php echo $value['id']?>"
					value="<?php echo $value['stanje']?>"
					class="non_editable"
					size="3"
				/></td>
				<td><input
					type="text"
					disabled="disabled"
					id="min_<?php echo $value['id']?>"
					value="<?php echo $value['minimum']?>"
					class="non_editable"
					size="3"
				/></td>
				<td><input
					type="text"
					disabled="disabled"
					id="cen_<?php echo $value['id']?>"
					value="<?php echo $value['cena']?>"
					class="non_editable"
					size="4"
				/></td>
				<td><input
					type="submit"
					class="product_change dugmici"
					proiz_id="<?php echo $value['id']?>"
					id="edit_potvrdi_<?php echo $value['id']?>"
					value="Edit"
					size="3"
				/><input
					type="submit"
					class="remove_cancel dugmici"
					id="prekini_ukloni_<?php echo $value['id']?>"
					proiz_id="<?php echo $value['id']?>"
					value="Obrisi"
					size="3"
				/></td>
			</tr>
			<?php
		}
		?>
			<tr>
				<td>Dodaj novi:</td>
			</tr>
			<tr>
				<td><select
					id="kat_0"
					class="non_editable"
					disabled="disabled"
					style="width: 80px"
					proiz_id="0"
				>
						<option value="0">Izaberi kategoriju</option>
						<?php
						if (is_array($cat)){
							foreach ($cat as $cat_value){?>
						<option value="<?php echo $cat_value['id'];?>">
						<?php echo $cat_value['kategorija'];?>
						</option>
						<?php }
						}
						?>
				</select>
				</td>
				<td><div id="category_0">
						<select
							id="pod_0"
							class="non_editable"
							disabled="disabled"
							style="width: 80px"
						>
							<option value="0">Izaberi podkategoriju</option>
						</select>
					</div></td>
				<td><input
					type="text"
					disabled="disabled"
					id="naz_0"
					class="non_editable"
					size="7"
				/></td>
				<td><input
					type="text"
					disabled="disabled"
					id="opi_0"
					class="non_editable"
					size="30"
				/></td>
				<td><input
					type="text"
					disabled="disabled"
					id="por_0"
					class="non_editable"
					size="7"
				/></td>
				<td><input
					type="text"
					disabled="disabled"
					id="pak_0"
					class="non_editable"
					size="7"
				/></td>
				<td><input
					type="text"
					disabled="disabled"
					id="sta_0"
					class="non_editable"
					size="3"
				/></td>
				<td><input
					type="text"
					disabled="disabled"
					id="min_0"
					class="non_editable"
					size="3"
				/></td>
				<td><input
					type="text"
					disabled="disabled"
					id="cen_0"
					class="non_editable"
					size="4"
				/></td>
				<td><input
					type="submit"
					id="add_new"
					value="Dodaj"
					size="3"
					class="dugmici"
				/>
					<div
						class="hidden"
						id="div_cancel_new"
					>
						<input
							type="submit"
							id="cancel_new"
							value="Odustani"
							size="3"
							class="dugmici"
						/>
					</div>
				</td>
			</tr>
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