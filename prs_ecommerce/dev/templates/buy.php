<?php
require_once '../classes/AutoloadClasses.inc';
SecurityChecker::sessionStart();
?>
<div id="message">
	<fieldset class="message">
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
		<table>
			<tr>
				<td><?php
				$message = json_decode($buy);
				if ($message->status=="success"){
					?> <label class="labela">Kupljeno</label> <?php
				}
				else {
					?> <label class="labela">Greska u procesu</label> <?php
				}
				?>
				</td>
			</tr>
		</table>
	</fieldset>
</div>
