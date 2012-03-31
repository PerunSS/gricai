<html>
	<head>
		<LINK href="css/index.css" rel="stylesheet" type="text/css">
		<title>Login</title>
	</head>
<body>
	<?php 
	require_once "../classes/AutoloadClasses.inc";
	$communicator = new Communicator();
		
	$result = $communicator->getClasses();
	$json = json_decode($result);
	?>

	<form class="login" action="gameloby.php">
	<div class="name">
	<label>Name: </label>
	<input type="text" name="name"/>
	</div>
	<div class="name">
	<label>Hero: </label>	
	<select name="class">  
	</div>

	<?php 
		foreach($json->heroes as $name){
			echo "<option value=\"".$name."\"/>".$name."</option>";
		}
	?>
	</select>
	<br/>
	<input class="button" type="submit" value="next" style="
    display: block;   float:left;   padding-left:5px;   width: 150px;   height: 30px;   background-color: black;   color: white;   font-size: 25px;
"/>
	
	</form>

</body>
</html>
