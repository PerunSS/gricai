<html>
<head>
<!-- <meta http-equiv="refresh" content="10;url=game.php"> -->
<LINK href="../css/game.css" rel="stylesheet" type="text/css">
<title>game</title>
<script language="JavaScript">
function get_div(event){

	
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
$map = $communicator->getMap();
$json_map = json_decode($map);
$enemies = array();
$map = new Map($json_map->fields);
$players = $json_map->players;
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
<div id="details_player" class="details_player">igrac</div>
<div id="map" class="map"><?php 
foreach($map->fields as $field){
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
					$have_player = true;
				}else{
					$enemies[$i++] = $item;
					$have_enemy = true;
				}
			}else{
				$enemies[$i++] = $item;
				$have_enemy = true;
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
		if(empty($high_light->item)){
			$string.= " avaliable";
		}
	}
	if($have_enemy){
		$string .= " enemy";
	}elseif($have_obstacle){
		$string .= " obstacle";
	}elseif($have_player){
		$string .= " player";
	}

	$string.= '" id="field_'.$field->x.'_'.$field->y.'" ></div>';
	echo $string;
}
?></div>
<div id="details_enemy" class="details_enemy">neprijatelj</div>

</body>



</html>
