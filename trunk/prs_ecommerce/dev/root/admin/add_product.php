<?php
require_once '../../classes/AutoloadClasses.inc';
SecurityChecker::sessionStart();
if(!isset($_SESSION['admin'])){
	header("Location: /admin/login.php");
}
?>
<a href="/admin/admin_panel.php">nazad</a>
<form action="/action.php" target="results">
naziv: <input type="text" name="naziv" /><br />
poreklo: <input type="text" name="poreklo" /><br />
opis: <input type="text" name="opis" /><br /> 
tip: <input type="text" name="tip"/><br />
pakovanje: <input type="text" name="pakovanje"/><br /> 
cena: <input type="text" name="cena"/><br />
podkategorija: <select name="id_podkategorija">
	<?php
		$admin = Admin::fromJson($_SESSION['admin']); 
		$podkategorije = $admin->getPodkategorije();
		foreach($podkategorije as $pod){ ?>
			<option value="<?php echo $pod['id'];?>"><?php echo $pod['kategorija'];?></option>
	<?php }?>
</select><br />
<input type="submit" value="dodaj" /> 
<input type="hidden" name="a_add_product" value="true" />
</form>
<iframe name="results" id="results" width="800px" height="600px" src="">
</iframe>
