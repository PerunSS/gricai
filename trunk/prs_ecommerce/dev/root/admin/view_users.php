<?php
require_once '../../classes/AutoloadClasses.inc';
SecurityChecker::sessionStart();
if(!isset($_SESSION['admin'])){
	header("Location: /admin/login.php");
}
?>
<a href="/admin/admin_panel.php">nazad</a>
<form action="/action.php" target="results">
Username: <input type="text" name="a_korisnik_un_search" /><br />
Tip:<select name="a_korisnik_tip_search">
	<option value="10" selected="selected">svi</option>
	<option value="0">Fizicka lica</option>
	<option value="1">Pravna lica</option>
</select><br />
<input type="checkbox" name="a_korisnik_aktivan_search" />
Aktivni korisnici<br />
<input type="checkbox" name="a_korisnik_banovan_search" />
Banovani korisnici<br />
<input type="submit" value="pretraga" /> 
<input type="hidden" name="a_korisnik_search" value="true" />
</form>
<iframe name="results" id="results" width="800px" height="600px" src="/action.php?a_korisnik_search=true">
</iframe>
