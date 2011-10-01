<?php
require_once '../../classes/AutoloadClasses.inc';
SecurityChecker::sessionStart();
if(!isset($_SESSION['admin'])){
	header("Location: /admin/login.php");
}
?>
<a href="/admin/admin_panel.php">nazad</a>
<form action="/action.php" target="results">
vreme: <input type="text" name="a_porudzbenica_vreme_search" /><br />
cena<br/>
od: <input type="text" name="a_porudzbenica_cena_min" value="0" /> 
do: <input type="text" name="a_porudzbenica_cena_max"/>
<br />
<input type="submit" value="pretraga" /> 
<input type="hidden" name="a_porudzbenica_search" value="true" />
</form>
<iframe name="results" id="results" width="800px" height="600px" src="/action.php?a_porudzbenica_search=true">
</iframe>
