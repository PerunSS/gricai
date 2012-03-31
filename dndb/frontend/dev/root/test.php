<?php
require_once "../classes/AutoloadClasses.inc";

	$fields=array();
	$hero=null;
	$obstacle=null;
	$counter = 0;
	for($i=1; $i<=10; $i++)
		for($j=1; $j<=10; $j++){
			$field=new Field($j, $i);
			$fields[$counter++] =$field;
		}		
	$map = new Map($fields);
	
	$field = $fields[0];
	print_r($field->x);
	print_r($field->y);
	print_r ($map->getFieldsAvaliable($field, 2));
		
		
	
	
		 