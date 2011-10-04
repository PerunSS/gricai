<div class="search_items" id="proba">
	<fieldset>
		<table>
			<tr>
				<td><label for="search_name">Proizvod</label></td>
				<td><input
					type="text"
					id="search_name"
					name="search_name"
				></td>
				<td>
					<div id="s_search_div">
						<input
							type="submit"
							id="s_search_items"
							name="s_search_items"
							value="Nadji"
						>
					</div>
				</td>
			</tr>
		</table>
		<table>
			<tr>
				<td><input
					type="submit"
					id="advanced_search"
					name="advanced_search"
					value="Napredna pretraga"
				></td>
			</tr>
		</table>
	</fieldset>
	<div
		class="hidden"
		id="advanced_div"
	>
		<fieldset>
			<table>
				<tr>
					<td><label for="categorry">Kategorija</label></td>
					<td><select
						id="categorry"
						name="categorry"
					>
							<option value="0">Izaberi kategoriju</option>
							<?php
							$db = new DBManager();
							$cat = $db->callProcedure("select_kategorije", array());
							if (is_array($cat)){
								foreach ($cat as $value){?>
							<option value="<?php echo $value['id'];?>">
							<?php echo $value['kategorija'];?>
							</option>
							<?php }
							}
							?>
					</select>				
				</tr>
				<tr>
					<td><label for="sub_categorry">Podkategorija</label></td>
					<td>
						<div id="categorry_options">							
						</div>					
					<td><input
						type="submit"
						id="a_search_items"
						name="a_search_items"
						value="Nadji"
					></td>
				</tr>
			</table>
			<table>
				<tr>
					<td><input
						type="submit"
						id="smaller_search"
						name="smaller_search"
						value="Osnovna pretraga"
					></td>
				</tr>
			</table>
		</fieldset>
	</div>
</div>
