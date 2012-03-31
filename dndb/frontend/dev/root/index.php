<?php 
require_once "../classes/AutoloadClasses.inc";
$communicator = new Communicator();
	
$result = $communicator->getClasses();
$json = json_decode($result);
?>

<form action="gameloby.php">
<label>Name: </label>
<input type="text" name="name"/><br/>
<label>Hero: </label>
<select name="class">  
<?php 
	foreach($json->heroes as $name){
		echo "<option value=\"".$name."\"/>".$name."</option>";
	}
?>
</select>
<br/>
<input type="submit" value="next"/>
</form>