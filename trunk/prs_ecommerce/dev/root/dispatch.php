<?php 
require_once '../classes/AutoloadClasses.inc';
if(isset($_REQUEST['dispatch'])){
	include '../templates/'.$_REQUEST['dispatch'].'.php';
}
?>