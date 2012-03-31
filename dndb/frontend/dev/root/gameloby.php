<html>
<head>
<meta http-equiv="refresh" content="10;url=gameloby.php">
</head>
<body>
<?php
require_once "../classes/AutoloadClasses.inc";
if (!isset($_SESSION)) {
	session_start();
}
$communicator = new Communicator();
if(isset($_GET['ready'])){
	$_SESSION['ready'] = $_GET['ready'];
	$result = $communicator->ready($_SESSION['name']);
}
if(isset($_GET['change_team'])){
	$_SESSION['team'] = $_GET['team'];
	$result = $communicator->changeTeam($_SESSION['name'], $_SESSION['team']);
}
$name = "";
if(isset($_GET['name'])){
	session_destroy();
	session_start();
	$name = $_GET['name'];
	$_SESSION['name'] = $name;
}
if(isset($_GET['class'])){
	$hero = $_GET['class'];
	$_SESSION['hero'] = $hero;
}


if($name!="" && $hero!=""){
	$result = $communicator->addHero($name,$hero);
	$json = json_decode($result);
	if($json->result == "notok"){
		?>
		<label>Player: <?=$name?> allready exists</label><br/>
		<a href="index.php">&lt;&lt;back</a>
		<?php
		exit(); 
	}
	if($json->result == "full"){
		?>
		<label>Game full :P</label><br/>
		<a href="index.php">&lt;&lt;back</a>
		<?php
		exit(); 
	}
}
$name = $_SESSION['name'];
$hero = $_SESSION['hero'];
$team = 1;
if(isset($_SESSION['team'])){
	$team = $_SESSION['team'];
}
$list = $communicator->getList();
$json = json_decode($list);

if(isset($json->result)){
	if($json->result == "game_start"){
		header("Location: game.php");
	}
}

?>
<h2>Players</h2>
<?php
foreach($json->players as $element){
	echo "<hr/>";
	if($element->name == $name){
		?>
		<label>Name: <?=$name ?> </label><br/>
		<label>Team: </label>
		<form action="gameloby.php" >
			<select name="team" <?php if(isset($_SESSION['ready'])) echo 'disabled="disabled"'?>>
			<option value="1" <?php if($team == 1) echo 'selected="selected"';?>>team 1</option>
			<option value="2" <?php if($team == 2) echo 'selected="selected"';?>>team 2</option>
			<option value="3" <?php if($team == 3) echo 'selected="selected"';?>>team 3</option>
			<option value="4" <?php if($team == 4) echo 'selected="selected"';?>>team 4</option>
			</select>
			<input type="submit" value="update" <?php if(isset($_SESSION['ready'])) echo 'disabled="disabled"'?>/>
			<input type="hidden" name="change_team" value="true"/>	
		</form>
		<label>Class: <?=$hero?></label>
		<form action="gameloby.php">
			<input type="hidden" name="ready" value="true" />	
			<input type="submit" value="ready" <?php if(isset($_SESSION['ready'])) echo 'disabled="disabled"'?>/>
		</form>
		<?php 
	}else{
		?>
		<label>Name: <?=$element->name?></label><br/>
		<label>Team: <?=$element->team?></label><br/>
		<label>Class: <?=$element->hero?></label><br/>
		<?php 	
	}
}
?>
</body>
</html>
