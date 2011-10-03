<div
	class="message"
	id="message"
>
	<table>
		<tr>
			<td><?php
			require_once '../classes/AutoloadClasses.inc';
			SecurityChecker::sessionStart();
			$message = json_decode($buy);
			if ($message->status=="success"){				
				?> <label>Kupljeno</label> <?php
			}
			else {
				?> <label>Greska u procesu</label> <?php
			}
			?></td>
			<td><input
				type="submit"
				id="close"
				name="close"
				value="OK"
			></td>
		</tr>
	</table>
</div>