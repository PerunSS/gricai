<?php
SecurityChecker::sessionStart();
if(!isset($_SESSION['admin'])){
	header("Location: /admin/login.php");
}
$admin = Admin::fromJson($_SESSION['admin']);
$users = $admin->getUsers();
$data = $_REQUEST;
//print_r($_REQUEST);
$target_username = (empty($data['a_korisnik_un_search'])?0:$data['a_korisnik_un_search']);
$target_tip = 10;
if(isset($data['a_korisnik_tip_search'])){
	$target_tip = $data['a_korisnik_tip_search'];
}
$target_aktivan = (empty($data['a_korisnik_aktivan_search'])?0:1);
$target_banovan = (empty($data['a_korisnik_banovan_search'])?0:1);
//echo "Target tip: $target_tip<br/>";
//echo "Target aktivan: $target_aktivan<br/>";
//echo "Target banovan: $target_banovan<br/>";
//echo "Target username: $target_username<br/>";

?>
<div class="user_list_div">
	<fieldset>
	<br/>
		<table>
			<thead>
				<tr>
					<th width="20%">Username</th>
					<th width="25%">Email</th>
					<th width="15%">Tip</th>
					<th width="15%">Aktivan</th>
					<th width="15%">Banovan</th>
					<th width="10%"></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
			<?php
			$color = true;
			foreach($users as $user){
				$color = !$color;
				$banovan = $user['banovan']?'Unb':'B';
				if($target_banovan && !$user['banovan']){
					continue;
				}
				if($target_username){
					if(strpos($user['korisnik'],$target_username)===false){
						continue;
					}
				}
				if($target_aktivan && !$user['aktivan']){
					continue;
				}
				if($target_tip != 10){
					if($target_tip && !$user['tip']){
						continue;
					}
					if(!$target_tip && $user['tip']){
						continue;
					}
				}
				?>
				<tr class="<?php echo ($color?'sivi':'svetli');?>">
					<td><?php echo $user['korisnik']; ?></td>
					<td><?php echo $user['email']; ?></td>
					<td><?php echo ($user['tip']?'pravno lice':'fizicko lice'); ?></td>
					<td><?php echo ($user['aktivan']?'aktivan':''); ?></td>
					<td><input
						type="submit"
						class="un_ban dugmici"
						user_id="<?php echo $user['id']?>"
						value="<?php echo $banovan?>an"
					/>
					</td>
					<td><input
						type="submit"
						class="detail_user dugmici"
						user_id="<?php echo $user['id']?>"
						value="Detaljno"
					/></td>
				</tr>
				<?php
	}
	?>
			</tbody>
		</table>
	</fieldset>
</div>
