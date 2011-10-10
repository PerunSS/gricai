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
				if (isset($_POST['item_name']) && isset($_POST['item_id']) && isset($_POST['item_currency'])){
					if (!isset($_SESSION['cart'])){
						$_SESSION['cart']=array();
					}
					$value = 1;
					if (isset($_SESSION['cart'][$_POST['item_id']])){
						$value=$_SESSION['cart'][$_POST['item_id']]['value']+1;
					}
					$_SESSION['cart'][$_POST['item_id']]=array('value'=>$value,'name'=>$_POST['item_name'],'currency'=>$_POST['item_currency']);
					?> <label class="labela">Dodato u korpu</label> <?php
				}
				else {
					?> <label class="labela">Nije dodato u korpu</label> <?php
				}
				?>
				</td>
			</tr>
		</table>
	</fieldset>
</div>
