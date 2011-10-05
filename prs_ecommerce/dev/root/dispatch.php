<?php 
require_once '../classes/AutoloadClasses.inc';
if(isset($_REQUEST['dispatch'])){
	include '..'.Constants::PHP_PATH.'/templates/'.$_REQUEST['dispatch'].'.php';
}
?>