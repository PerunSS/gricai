<?php
require_once "../classes/AutoloadClasses.inc";
$name = $_GET['name'];
$hero = $_GET['class'];

$communicator = new Communicator();
$communicator->addHero($name,$hero);
$list = $communicator->getList();
$json = json_decode($list);

foreach($json->list as $element){
	
}
?>

