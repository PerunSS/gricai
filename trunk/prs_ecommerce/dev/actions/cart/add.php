<div
	class="message"
	id="message"
>
	<table>
		<tr>
			<td><?php
			require_once '../classes/AutoloadClasses.inc';
			SecurityChecker::sessionStart();
			if (isset($_POST['item_name']) && isset($_POST['item_id']) && isset($_POST['item_currency'])){
				if (!isset($_SESSION['cart'])){
					$_SESSION['cart']=array();
				}
				$value = 1;
				if (isset($_SESSION['cart'][$_POST['item_id']])){
					$value=$_SESSION['cart'][$_POST['item_id']]['value']+1;
				}
				$_SESSION['cart'][$_POST['item_id']]=array('value'=>$value,'name'=>$_POST['item_name'],'currency'=>$_POST['item_currency']);
				?> <label>Dodatto u korpu</label> <?php
			}
			else {
				?> <label>Nije dodato u korpu</label> <?php
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
