<html>
<head>
<meta http-equiv="refresh" content="3;url=game.php"> 
<LINK href="../css/game.css" rel="stylesheet" type="text/css">
<title>game</title>
<script language="JavaScript">
function get_div(event){
	pos_x = event.pageX-document.getElementById("map").offsetLeft;
	pos_y = event.pageY-document.getElementById("map").offsetTop;
	x = Math.floor(pos_x/32);
	y = Math.floor(pos_y/32);
	div = document.getElementById("field_"+x+"_"+y);
	if(div.className == "grid")
		alert ("nece da moze");
	else if(div.className == "grid avaliable")
		window.location = "game.php?move=true&x="+x+"&y="+y;
	else if(div.className.indexOf("grid avaliable enemy")!=-1){
		window.location = "game.php?attack=true&x="+x+"&y="+y;
	}
}

</script>
</head>
<body>
<?php
require_once "../classes/AutoloadClasses.inc";
if (!isset($_SESSION)) {
	session_start();
}
$communicator = new Communicator();

if(isset($_GET['move'])){
	//TODO promeniti ime igraca
	$communicator->move($_GET['x'],$_GET['y'],$_SESSION['name']/*$_SESSION['name']*/);
}

if(isset($_GET['end_turn'])){
	//TODO promeniti ime igraca
	$communicator->endTurn($_SESSION['name']);
}

$map = $communicator->getMap();
$json_map = json_decode($map);
$enemies = array();
$fields = array();
foreach($json_map->fields as $field){
	$fields[$field->y][$field->x] = $field;
}
$map = new Map($fields);
$players = $json_map->players;

if(isset($_GET['attack'])){
	$x = $_GET['x'];
	$y = $_GET['y'];
	$target = "";
	foreach($players as $player){
		if($player->x == $x && $player->y == $y){
			$target = $player->player_name;
			break;
		}
	}
	$communicator->attack($_SESSION['name'],$target);
	$map = $communicator->getMap();
	$json_map = json_decode($map);
	$enemies = array();
	$fields = array();
	foreach($json_map->fields as $field){
		$fields[$field->y][$field->x] = $field;
	}
	$map = new Map($fields);
	$players = $json_map->players;
}


$field_current_turn = NULL;
$curr_player = NULL;
foreach($players as $player){
	if($player->player_isTurn){
		$curr_player = $player;
		$field_current_turn = $map->getField($player->x, $player->y);
		break;
	}
}

$highlight = $map->getFieldsAvaliable($field_current_turn, $curr_player->t);
$i = 0;
?>
<div id="details_player" class="details_player">
<form action="game.php">
<input type="submit" value="end turn" <?php if($curr_player->player_name != $_SESSION['name']) echo "disabled=\"disabled\"";?>/>
<input type="hidden" name="end_turn" value="true"/>
</form>
Current player: <?php echo $curr_player->player_name;?><br/>
Last attack: <?php echo $json_map->attack;?>
<br/><br/>
<div class="player_big">
<div class="player_top">
<div class="image_display"><?php echo '<img src="images/'.strtolower($players[0]->name).'.png"/>'?></div>
<div class="basic_data">
<label>Name: <?=$players[0]->player_name?></label><br/>
<label>Class: <?=$players[0]->name?></label><br/>
<label>Level: <?=$players[0]->level?></label><br/>
<label>AC: <?=$players[0]->ac?></label><br/>
<label>HP: <?=$players[0]->hp?></label><br/>
<label>Team: <?=$players[0]->player_team?></label><br/>
</div>
</div>
<label>Dexterity: <?=$players[0]->dexterity?></label><br/>
<label>Intelligence: <?=$players[0]->intelligence?></label><br/>
<label>Wisdom: <?=$players[0]->wisdom?></label><br/>
<label>Charisma: <?=$players[0]->charisma?></label><br/>
<label>Stregth: <?=$players[0]->strength?></label><br/>
<label>Constitution: <?=$players[0]->strength?></label><br/>
<hr/>
<div class="player_big">
<div class="player_top">
<div class="image_display"><?php echo '<img src="images/'.strtolower($players[1]->name).'.png"/>'?></div>
<div class="basic_data">
<label>Name: <?=$players[1]->player_name?></label><br/>
<label>Class: <?=$players[1]->name?></label><br/>
<label>Level: <?=$players[1]->level?></label><br/>
<label>AC: <?=$players[1]->ac?></label><br/>
<label>HP: <?=$players[1]->hp?></label><br/>
<label>Team: <?=$players[1]->player_team?></label><br/>
</div>
</div>
<label>Dexterity: <?=$players[1]->dexterity?></label><br/>
<label>Intelligence: <?=$players[1]->intelligence?></label><br/>
<label>Wisdom: <?=$players[1]->wisdom?></label><br/>
<label>Charisma: <?=$players[1]->charisma?></label><br/>
<label>Stregth: <?=$players[1]->strength?></label><br/>
<label>Constitution: <?=$players[1]->strength?></label><br/>
</div>
</div>

</div>
<div id="map" class="map" <?php if($_SESSION['name']==$curr_player->player_name) {?>onclick="get_div(event)"<?php } ?>><?php  
foreach($map->fields as $row)
foreach($row as $field){
	$have_enemy = false;
	$have_obstacle = false;
	$have_player = false;
	$high_light = NULL;
	if(!empty($field->item)){
		$item = $field->item;
		if(isset($item->player_name)){
			if(isset($_SESSION['name'])){
				if($item->player_name == $_SESSION['name']){
					$_SESSION['player'] = $item;
					$have_player = $item->name;
				}else{
					$enemies[$i++] = $item;
					$have_enemy = strtolower($item->name);
				}
			}else{
				$enemies[$i++] = $item;
				$have_enemy = strtolower($item->name);
			}

		}else{
			$have_obstacle = true;
		}
	}
	foreach($highlight as $h_field){
		if($h_field->x == $field->x && $h_field->y == $field->y){
			$high_light = $h_field;
			break;
		}
	}
	$string = '<div class="grid';
	if($high_light!=NULL){
		
			$string.= " avaliable";
		
	}
	if($have_enemy){
		$string .= " enemy $have_enemy";
	}elseif($have_obstacle){
		$string .= " obstacle";
	}elseif($have_player){
		$string .= " player $have_player";
	}

	$string.= '" id="field_'.$field->x.'_'.$field->y.'" ></div>';
	echo $string;
}
?></div>
<div id="details_enemy" class="details_enemy">
<br/><br/><br/><br/><br/>
<div class="player_big">
<div class="player_top">
<div class="image_display"><?php echo '<img src="images/'.strtolower($players[2]->name).'.png"/>'?></div>
<div class="basic_data">
<label>Name: <?=$players[2]->player_name?></label><br/>
<label>Class: <?=$players[2]->name?></label><br/>
<label>Level: <?=$players[2]->level?></label><br/>
<label>AC: <?=$players[2]->ac?></label><br/>
<label>HP: <?=$players[2]->hp?></label><br/>
<label>Team: <?=$players[2]->player_team?></label><br/>
</div>
</div>
<label>Dexterity: <?=$players[2]->dexterity?></label><br/>
<label>Intelligence: <?=$players[2]->intelligence?></label><br/>
<label>Wisdom: <?=$players[2]->wisdom?></label><br/>
<label>Charisma: <?=$players[2]->charisma?></label><br/>
<label>Stregth: <?=$players[2]->strength?></label><br/>
<label>Constitution: <?=$players[2]->strength?></label><br/>
<hr/>
<div class="player_big">
<div class="player_top">
<div class="image_display"><?php echo '<img src="images/'.strtolower($players[3]->name).'.png"/>'?></div>
<div class="basic_data">
<label>Name: <?=$players[3]->player_name?></label><br/>
<label>Class: <?=$players[3]->name?></label><br/>
<label>Level: <?=$players[3]->level?></label><br/>
<label>AC: <?=$players[3]->ac?></label><br/>
<label>HP: <?=$players[3]->hp?></label><br/>
<label>Team: <?=$players[3]->player_team?></label><br/>
</div>
</div>
<label>Dexterity: <?=$players[3]->dexterity?></label><br/>
<label>Intelligence: <?=$players[3]->intelligence?></label><br/>
<label>Wisdom: <?=$players[3]->wisdom?></label><br/>
<label>Charisma: <?=$players[3]->charisma?></label><br/>
<label>Stregth: <?=$players[3]->strength?></label><br/>
<label>Constitution: <?=$players[3]->strength?></label><br/>
</div>
</div>
</div>
</body>



</html>
