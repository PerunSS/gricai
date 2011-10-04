<table>
	<tr>
		<td>Username:</td>
		<td><input
			type="text"
			name="a_korisnik_un_search"
			id="a_korisnik_un_search"
		/>
		</td>
	</tr>
	<tr>
		<td>Tip:</td>
		<td><select name="a_korisnik_tip_search" id="a_korisnik_tip_search">
				<option
					value="10"
					selected="selected"
				>svi</option>
				<option value="0">Fizicka lica</option>
				<option value="1">Pravna lica</option>
		</select>
		</td>
	</tr>
	<tr>
		<td><input
			type="checkbox"
			name="a_korisnik_aktivan_search"
			id="a_korisnik_aktivan_search"
		/>
		</td>
		<td>Aktivni korisnici</td>
	</tr>
	<tr>
		<td><input
			type="checkbox"
			id="a_korisnik_banovan_search"
			name="a_korisnik_banovan_search"
		/>
		</td>
		<td>Banovani korisnici</td>
	</tr>
	<tr>
		<td><input
			type="submit"
			id="searchUsers"
			value="Pretraga"
		/></td>
	</tr>
</table>
<div id="list_of_user">

</div>
