<?php
require_once '../../classes/AutoloadClasses.inc';
SecurityChecker::sessionStart();
if(!isset($_SESSION['admin'])){
	header("Location: login.php");
}

?>
<html>
<head>
<link
	rel="stylesheet"
	type="text/css"
	href="../css/admin_panel.css"
	media="all"
/>
<script
	type="text/javascript"
	src="../js/jquery.js"
></script>
<script
	type="text/javascript"
	src="../js/blockUI.js"
></script>
<script
	type="text/javascript"
	src="../js/admin_panel.js"
></script>
<script
	type="text/javascript"
	src="../js/product.js"
></script>
</head>
<body>
<?php
$admin = json_decode($_SESSION['admin']);
?>
	<div class="admin_detail">
		<fieldset>
			<table>
				<tr>
					<td>Admin: <?php echo $admin->username;?></td>
				</tr>
			</table>
		</fieldset>
	</div>
	<div class="admin_panel">
		<fieldset>
			<legend>Admin panel</legend>
			<ul>
				<li><label id="user_list">Pregled korisnika</label></li>
				<li><label id="product_list">Pregled proizvoda</label></li>
				<li><label>Dodavanje proizvoda</label></li>
				<li><label id="shopping_list">Pregled porudzbenica</label></li>
				<li><label>Promena lozinke</label></li>
				<li><a href="logout.php">Kraj rada</a></li>
			</ul>
		</fieldset>
	</div>
	<div class="admin_works">
	<fieldset>
	<div id="details"></div>
	</fieldset>
	</div>
	<div id="load_html" class="hidden"></div>
</body>
</html>
